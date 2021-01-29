package conversations;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import events.EFileTransferComplete;
import events.EFileTransferFailed;
import events.EFileTransferProgress;
import filesystem.IFileWriter;
import messages.IMessage;
import messages.transfer.FileDataAcceptedResponse;
import messages.transfer.FileDataRequest;
import messages.transfer.FileTransferError;

import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import java.io.IOException;
import java.nio.file.Path;

public class FileTransferReceivingAgent
{
    @Inject
    private IFileWriter fileWriter;

    @Getter
    @Setter
    private Path filePath;

    @Inject
    private Event<EFileTransferFailed> fileTransferFailedBus;

    @Inject
    private Event<EFileTransferProgress> fileTransferProgressBus;

    @Inject
    private Event<EFileTransferComplete> fileTransferCompleteBus;

    private IConversation conversation;

    public void start(final @NotNull Path filePath,
                      final @NotNull IConversation conversation) throws IOException
    {
        this.conversation = conversation;
        fileWriter.open(filePath);
        this.filePath = filePath;
    }

    public void stop()
    {
        try
        {
            fileWriter.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void processMessage(final @NotNull IMessage message)
    {
        try
        {
            if (message instanceof FileTransferError)
            {
                final FileTransferError errorMessage = (FileTransferError) message;
                final EFileTransferFailed event = new EFileTransferFailed(conversation);
                event.setRemote(false);
                event.setReason(errorMessage.getReason());
                fileTransferFailedBus.fireAsync(event);
                return;
            }

            final FileDataRequest request = (FileDataRequest) message;
            fileWriter.write(request.getData());

            final FileDataAcceptedResponse response = new FileDataAcceptedResponse();
            response.setCseq(request.getCseq());
            conversation.sendMessageToPeer(response);
            if (!request.isLast())
            {
                fileTransferProgressBus.fireAsync(new EFileTransferProgress(request.getPercentComplete(), filePath.getFileName().toString()));
                conversation.continueConversation();
            }
            else
            {
                fileTransferCompleteBus.fireAsync(new EFileTransferComplete(filePath.getFileName().toString()));
            }
        }
        catch (IOException e)
        {
            conversation.sendMessageToPeer(new FileTransferError(e.getMessage()));
            final EFileTransferFailed event = new EFileTransferFailed(conversation);
            event.setRemote(false);
            event.setReason(e.getMessage());
            fileTransferFailedBus.fireAsync(event);
        }
    }
}
