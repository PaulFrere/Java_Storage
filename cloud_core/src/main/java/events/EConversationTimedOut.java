package events;

import conversations.IConversation;

public class EConversationTimedOut extends EConversationFailed
{
    public EConversationTimedOut(final IConversation conversation)
    {
        super(conversation);
    }
}
