package conversations;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import cloud_core.conversations.*;
import cloud_core.messages.IMessage;
import cloud_core.messages.transfer.FileDataAcceptedResponse;
import cloud_core.messages.transfer.FileTransferError;
import cloud_core.messages.transfer.FileTransferReady;
import cloud_core.messages.transfer.PutRequest;

import jakarta.inject.Inject;
import java.nio.file.Path;

@ActiveAgent
@StartsWith(PutRequest.class)
@Expects({FileDataAcceptedResponse.class, FileTransferReady.class, FileTransferError.class})
public class PutClientAgent extends AbstractConversation
{
    @Getter
    @Setter
    private Path relativeFilePath;

    @Getter
    @Setter
    private Path localRoot;

    @Inject
    private FileTransferSendingAgent sendingAgent;

    @Override
    @SneakyThrows
    protected synchronized void beforeStart(@NotNull IMessage initialMessage)
    {
        final PutRequest putRequest = (PutRequest) initialMessage;
        putRequest.setFileName(relativeFilePath.toString());
        sendingAgent.start(localRoot.resolve(relativeFilePath), this);
    }

    @Override
    protected synchronized void beforeFinish()
    {
        sendingAgent.stop();
    }

    @Override
    public synchronized void processMessageFromPeer(final @NotNull IMessage message)
    {
        sendingAgent.processMessage(message);
    }
}
