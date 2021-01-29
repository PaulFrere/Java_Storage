package events;

import conversations.IConversation;

public class EConversationComplete extends EConversationEvent
{
    public EConversationComplete(IConversation conversation)
    {
        super(conversation);
    }
}
