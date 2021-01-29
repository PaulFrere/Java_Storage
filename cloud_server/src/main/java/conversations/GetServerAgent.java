package conversations;

import org.jetbrains.annotations.NotNull;
import cloud_core.conversations.Expects;
import cloud_core.conversations.FileTransferSendingAgent;
import cloud_core.conversations.RespondsTo;
import cloud_core.events.EFileTransferFailed;
import cloud_core.messages.IMessage;
import cloud_core.messages.transfer.FileDataAcceptedResponse;
import cloud_core.messages.transfer.FileTransferError;
import cloud_core.messages.transfer.GetRequest;

import jakarta.enterprise.event.Event;
import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Path;

@RespondsTo(GetRequest.class)
@Expects({FileTransferError.class, FileDataAcceptedResponse.class})
public class GetServerAgent extends ServerAgent
{
    @Inject
    private FileTransferSendingAgent sendingAgent;

    @Inject
    private Event<EFileTransferFailed> fileTransferFailedBus;

    @Override
    protected synchronized void beforeFinish()
    {
        sendingAgent.stop();
    }

    @Override
    public void processMessageFromPeer(@NotNull IMessage message)
    {
        if (message instanceof GetRequest)
        {
            final GetRequest request = (GetRequest) message;
            final Path filePath = getCurrentDirectory().resolve(request.getFileName());
            try
            {
                sendingAgent.start(filePath, this);
                sendingAgent.sendNextDataRequest();
            }
            catch (IOException e)
            {
                final EFileTransferFailed event = new EFileTransferFailed(this);
                event.setRemote(false);
                event.setReason(e.getMessage());
                fileTransferFailedBus.fireAsync(event);
                sendMessageToPeer(new FileTransferError(e.getMessage()));
            }
        }
        else
        {
            sendingAgent.processMessage(message);
        }
    }
}
