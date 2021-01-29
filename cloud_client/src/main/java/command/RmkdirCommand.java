package command;

import conversations.ClientConversationManager;
import conversations.MkdirClientAgent;
import cloud_core.conversations.ActiveAgent;

import jakarta.inject.Inject;

@Importance(2)
@Keyword(Const.RMKDIR_COMMAND_KEYWORD)
@Description(Const.RMKDIR_COMMAND_DESCRIPTION)
@Arguments(Const.TARGET_DIRECTORY_ARGUMENT_NAME)
public class RmkdirCommand extends AbstractCommand
{
    @Inject
    @ActiveAgent
    private MkdirClientAgent agent;

    @Inject
    private ClientConversationManager conversationManager;

    @Override
    public void run()
    {
        agent.setDirectoryPath(getArgumentValue(Const.TARGET_DIRECTORY_ARGUMENT_NAME));
        conversationManager.startConversation(agent);
    }
}
