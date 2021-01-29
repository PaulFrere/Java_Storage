package command;

import conversations.ClientConversationManager;
import conversations.PwdClientAgent;

import jakarta.inject.Inject;

@Importance(2)
@Keyword(Const.RPWD_COMMAND_KEYWORD)
@Description(Const.RPWD_COMMAND_DESCRIPTION)
public class RpwdCommand extends AbstractCommand
{
    @Inject
    private ClientConversationManager conversationManager;

    @Override
    public void run()
    {
        conversationManager.startConversation(PwdClientAgent.class);
    }
}
