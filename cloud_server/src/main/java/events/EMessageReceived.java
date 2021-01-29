package events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import cloud_core.messages.IMessage;
import conversations.ServerConversationManager;

@Getter
@Setter
@AllArgsConstructor
public class EMessageReceived
{
    private ServerConversationManager conversationManager;

    private IMessage message;
}
