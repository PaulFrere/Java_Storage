package command;

import conversations.ClientConversationManager;
import conversations.KeepAliveClientAgent;

import jakarta.inject.Inject;

@Keyword(Const.PING_COMMAND_KEYWORD)
@Description(Const.PING_COMMAND_DESCRIPTION)
public class PingCommand extends AbstractCommand
{
    @Inject
    private ClientConversationManager conversationManager;

    @Override
    public void run()
    {
        conversationManager.startConversation(KeepAliveClientAgent.class);
    }
}
