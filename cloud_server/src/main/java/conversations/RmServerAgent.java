package conversations;

import org.jetbrains.annotations.NotNull;
import cloud_core.conversations.RespondsTo;
import cloud_core.messages.IMessage;
import cloud_core.messages.ServerErrorResponse;
import cloud_core.messages.ServerOkResponse;
import cloud_core.messages.rm.RmRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

@RespondsTo(RmRequest.class)
public class RmServerAgent extends ServerAgent
{
    @Override
    public void processMessageFromPeer(@NotNull IMessage message)
    {
        final RmRequest request = (RmRequest) message;
        final Path pathToRemove = getCurrentDirectory().resolve(request.getTargetPath());
        try
        {
            if (Files.isDirectory(pathToRemove))
            {
                Files.walk(pathToRemove)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            }
            else
            {
                Files.delete(pathToRemove);
            }
        }
        catch (IOException e)
        {
            sendMessageToPeer(new ServerErrorResponse(e.getMessage()));
            return;
        }

        sendMessageToPeer(new ServerOkResponse());
    }
}
