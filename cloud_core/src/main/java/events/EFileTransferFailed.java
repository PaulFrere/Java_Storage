package events;

import lombok.Getter;
import lombok.Setter;
import conversations.IConversation;

public class EFileTransferFailed extends EConversationFailed
{
    @Getter
    @Setter
    private String fileName;

    public EFileTransferFailed(IConversation conversation)
    {
        super(conversation);
    }
}
