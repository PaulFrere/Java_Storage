package conversations;

import org.jetbrains.annotations.NotNull;
import cloud_core.conversations.RespondsTo;
import cloud_core.messages.IMessage;
import cloud_core.messages.ping.KeepAliveMessage;

import javax.inject.Inject;

@RespondsTo(KeepAliveMessage.class)
public class KeepAliveServerAgent extends ServerAgent
{
    @Inject
    private KeepAliveMessage response;

    @Override
    public synchronized void processMessageFromPeer(final @NotNull IMessage message)
    {
        sendMessageToPeer(response);
    }
}
