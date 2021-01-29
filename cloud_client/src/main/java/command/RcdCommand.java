package command;

import conversations.CdClientAgent;
import conversations.ClientConversationManager;
import cloud_core.conversations.ActiveAgent;

import jakarta.inject.Inject;

import static command.Const.TARGET_DIRECTORY_ARGUMENT_NAME;

@Importance(2)
@Keyword(Const.RCD_COMMAND_KEYWORD)
@Arguments(TARGET_DIRECTORY_ARGUMENT_NAME)
@Description(Const.RCD_COMMAND_DESCRIPTION)
public class RcdCommand extends AbstractCommand
{
    @Inject
    @ActiveAgent
    private CdClientAgent agent;

    @Inject
    private ClientConversationManager conversationManager;

    @Override
    public void run()
    {
        final String targetDirectory = getArgumentValue(TARGET_DIRECTORY_ARGUMENT_NAME);
        agent.setTargetDirectory(targetDirectory);
        conversationManager.startConversation(agent);
    }
}
