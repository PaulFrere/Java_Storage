package conversations;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import cloud_core.conversations.AbstractConversation;
import cloud_core.conversations.ActiveAgent;
import cloud_core.conversations.Expects;
import cloud_core.conversations.StartsWith;
import cloud_core.messages.IMessage;
import cloud_core.messages.ServerOkResponse;
import cloud_core.messages.mkdir.MkdirRequest;

@ActiveAgent
@StartsWith(MkdirRequest.class)
@Expects(ServerOkResponse.class)
public class MkdirClientAgent extends AbstractConversation
{
    @Getter
    @Setter
    private String directoryPath;

    @Override
    protected void beforeStart(@NotNull IMessage initialMessage)
    {
        final MkdirRequest request = (MkdirRequest) initialMessage;
        request.setDirectoryPath(directoryPath);
    }

    @Override
    public void processMessageFromPeer(@NotNull IMessage message)
    {

    }
}
