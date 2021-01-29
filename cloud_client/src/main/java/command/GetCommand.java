package command;

import conversations.ClientConversationManager;
import conversations.GetClientAgent;
import cloud_core.conversations.ActiveAgent;

import jakarta.inject.Inject;

@Importance(3)
@Keyword(Const.GET_COMMAND_KEYWORD)
@Description(Const.GET_COMMAND_DESCRIPTION)
@Arguments(Const.TARGET_FILE_ARGUMENT_NAME)
public class GetCommand extends AbstractCommand
{
    @Inject
    @ActiveAgent
    private GetClientAgent agent;

    @Inject
    private ClientConversationManager conversationManager;

    @Override
    public void run()
    {
        agent.setFileName(getArgumentValue(Const.TARGET_FILE_ARGUMENT_NAME));
        conversationManager.startConversation(agent);
    }
}
