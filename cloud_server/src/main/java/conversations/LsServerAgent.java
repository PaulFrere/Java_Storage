package conversations;

import org.jetbrains.annotations.NotNull;
import cloud_core.conversations.RespondsTo;
import cloud_core.messages.IMessage;
import cloud_core.messages.ServerErrorResponse;
import cloud_core.messages.ls.LsRequest;
import cloud_core.messages.ls.LsResponse;

import javax.inject.Inject;
import java.nio.file.Files;

@RespondsTo(LsRequest.class)
public class LsServerAgent extends ServerAgent
{
    @Inject
    private LsResponse response;

    @Override
    public void processMessageFromPeer(@NotNull IMessage message)
    {
        try
        {
            Files.newDirectoryStream(getCurrentDirectory()).forEach(element ->
                    response.getElements().add(new LsResponse.FilesystemElement(element)));
            sendMessageToPeer(response);
        }
        catch (Exception e)
        {
            sendMessageToPeer(new ServerErrorResponse());
        }
    }
}
