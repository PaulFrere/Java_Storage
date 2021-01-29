package events;

import lombok.Getter;
import lombok.Setter;
import conversations.IConversation;

public class EConversationFailed extends EConversationEvent
{
    @Getter
    @Setter
    private String reason;

    @Getter
    @Setter
    private boolean remote;

    public EConversationFailed(final IConversation conversation)
    {
        super(conversation);
    }

    public EConversationFailed(final IConversation conversation,
                               final String reason)
    {
        super(conversation);
        this.reason = reason;
    }
}
