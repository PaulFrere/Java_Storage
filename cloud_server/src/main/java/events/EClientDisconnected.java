package events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import conversations.ServerConversationManager;

@AllArgsConstructor
public class EClientDisconnected
{
    @Getter
    @Setter
    private ServerConversationManager conversationManager;
}
