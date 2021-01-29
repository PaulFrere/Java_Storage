package conversations;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import cloud_core.conversations.Expects;
import cloud_core.conversations.FileTransferReceivingAgent;
import cloud_core.conversations.RespondsTo;
import cloud_core.events.EFileTransferFailed;
import cloud_core.messages.IMessage;
import cloud_core.messages.transfer.FileDataRequest;
import cloud_core.messages.transfer.FileTransferError;
import cloud_core.messages.transfer.FileTransferReady;
import cloud_core.messages.transfer.PutRequest;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.io.IOException;

@RespondsTo(PutRequest.class)
@Expects({FileDataRequest.class, FileTransferError.class})
public class PutServerAgent extends ServerAgent
{
    @Inject
    private FileTransferReceivingAgent receivingAgent;

    @Inject
    private Event<EFileTransferFailed> fileTransferFailedBus;

    @Override
    protected void beforeFinish()
    {
        receivingAgent.stop();
    }

    @Override
    @SneakyThrows
    public void processMessageFromPeer(@NotNull IMessage message)
    {
        if (message instanceof PutRequest)
        {
            final PutRequest request = (PutRequest) message;
            try
            {
                receivingAgent.start(getCurrentDirectory().resolve(request.getFileName()), this);
                sendMessageToPeer(new FileTransferReady());
                continueConversation();
            }
            catch (IOException e)
            {
                sendMessageToPeer(new FileTransferError(e.getMessage()));
                final EFileTransferFailed event = new EFileTransferFailed(this);
                event.setRemote(true);
                event.setReason(e.getMessage());
                fileTransferFailedBus.fireAsync(event);
            }
        }
        else
        {
            receivingAgent.processMessage(message);
        }
    }
}
