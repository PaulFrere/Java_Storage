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
import cloud_core.messages.rm.RmRequest;

@ActiveAgent
@StartsWith(RmRequest.class)
@Expects(ServerOkResponse.class)
public class RmClientAgent extends AbstractConversation
{
    @Getter
    @Setter
    private String targetPath;

    @Override
    protected void beforeStart(@NotNull IMessage initialMessage)
    {
        final RmRequest request = (RmRequest) initialMessage;
        request.setTargetPath(targetPath);
    }

    @Override
    public void processMessageFromPeer(@NotNull IMessage message)
    {

    }
}
