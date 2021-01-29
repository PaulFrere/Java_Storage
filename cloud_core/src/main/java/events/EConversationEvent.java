package events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import conversations.IConversation;

@AllArgsConstructor
public abstract class EConversationEvent
{
    @Getter
    @Setter
    private IConversation conversation;
}
