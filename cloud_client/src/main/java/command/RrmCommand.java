package command;

import conversations.ClientConversationManager;
import conversations.RmClientAgent;
import cloud_core.conversations.ActiveAgent;

import jakarta.inject.Inject;

@Importance(2)
@Keyword(Const.RRM_COMMAND_KEYWORD)
@Description(Const.RRM_COMMAND_DESCRIPTION)
@Arguments(Const.TARGET_PATH_ARGUMENT_NAME)
public class RrmCommand extends AbstractCommand
{
    @Inject
    @ActiveAgent
    private RmClientAgent agent;

    @Inject
    private ClientConversationManager conversationManager;

    @Override
    public void run()
    {
        agent.setTargetPath(getArgumentValue(Const.TARGET_PATH_ARGUMENT_NAME));
        conversationManager.startConversation(agent);
    }
}
