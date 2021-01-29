package conversations;

import org.jetbrains.annotations.NotNull;
import cloud_core.conversations.AbstractConversation;
import cloud_core.conversations.ActiveAgent;
import cloud_core.conversations.Expects;
import cloud_core.conversations.StartsWith;
import cloud_core.messages.IMessage;
import cloud_core.messages.ping.KeepAliveMessage;

@ActiveAgent
@StartsWith(KeepAliveMessage.class)
@Expects(KeepAliveMessage.class)
public class KeepAliveClientAgent extends AbstractConversation
{
    private long startTime;

    @Override
    public synchronized void processMessageFromPeer(final @NotNull IMessage message)
    {
        final long delta = System.currentTimeMillis() - startTime;
        System.out.println();
        System.out.println("Response from " +
                getConversationManager().getTransportChannel().getRemoteAddress() +
                ". rtt=" +
                delta +
                "ms");
    }

    @Override
    protected void beforeStart(@NotNull IMessage initialMessage)
    {
        startTime = System.currentTimeMillis();
    }
}
