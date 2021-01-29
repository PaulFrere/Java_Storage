package conversations;

import org.jetbrains.annotations.NotNull;
import messages.IMessage;
import transport.ITransportChannel;

public interface IConversationManager
{
    @NotNull
    ITransportChannel getTransportChannel();

    void setTransportChannel(@NotNull ITransportChannel transportChannel);

    void dispatchMessage(@NotNull IMessage message);

    void stopConversation(@NotNull IConversation conversation);

    void startConversation(@NotNull IConversation conversation);
}
