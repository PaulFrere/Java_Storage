package events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import cloud_core.conversations.IConversationManager;

@NoArgsConstructor
@AllArgsConstructor
public class EAuthSuccess
{
    @Getter
    @Setter
    private IConversationManager conversationManager;
}
