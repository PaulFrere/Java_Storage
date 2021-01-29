package command;

import conversations.ClientConversationManager;
import conversations.LsClientAgent;

import jakarta.inject.Inject;

@Importance(2)
@Keyword(Const.RLS_COMMAND_KEYWORD)
@Description(Const.RLS_COMMAND_DESCRIPTION)
public class RlsCommand extends AbstractCommand
{
    @Inject
    private ClientConversationManager conversationManager;

    @Override
    public void run()
    {

        conversationManager.startConversation(LsClientAgent.class);
    }
}
