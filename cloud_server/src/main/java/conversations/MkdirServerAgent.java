package conversations;

import org.jetbrains.annotations.NotNull;
import cloud_core.conversations.RespondsTo;
import cloud_core.messages.IMessage;
import cloud_core.messages.ServerErrorResponse;
import cloud_core.messages.ServerOkResponse;
import cloud_core.messages.mkdir.MkdirRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RespondsTo(MkdirRequest.class)
public class MkdirServerAgent extends ServerAgent
{
    @Override
    public void processMessageFromPeer(@NotNull IMessage message)
    {
        final MkdirRequest request = (MkdirRequest) message;
        final Path newPath = getCurrentDirectory().resolve(request.getDirectoryPath());
        try
        {
            Files.createDirectories(newPath);
        }
        catch (IOException e)
        {
            sendMessageToPeer(new ServerErrorResponse(e.getMessage()));
            return;
        }

        sendMessageToPeer(new ServerOkResponse());
    }
}
