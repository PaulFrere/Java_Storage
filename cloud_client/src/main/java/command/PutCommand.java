package command;

import conversations.PutClientAgent;
import cloud_core.conversations.ActiveAgent;
import cloud_core.conversations.IConversationManager;

import jakarta.inject.Inject;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Importance(3)
@Keyword(Const.PUT_COMMAND_KEYWORD)
@Description(Const.PUT_COMMAND_DESCRIPTION)
@Arguments(Const.TARGET_FILE_ARGUMENT_NAME)
public class PutCommand extends AbstractCommand
{
    private final static String PATH_DOES_NOT_EXIST_MESSAGE = "Specified path does not exist";

    @Inject
    @ActiveAgent
    private PutClientAgent agent;

    @Inject
    private IConversationManager conversationManager;

    @Inject
    private CLI cli;

    @Override
    public void run()
    {
        final Path relativeLocalFilePath = Paths.get(getArgumentValue(Const.TARGET_FILE_ARGUMENT_NAME));
        final Path absoluteLocalFilePath = cli.getCurrentDirectory().resolve(relativeLocalFilePath);
        if (!Files.exists(absoluteLocalFilePath))
        {
            System.out.println(PATH_DOES_NOT_EXIST_MESSAGE);
            return;
        }

        agent.setRelativeFilePath(relativeLocalFilePath);
        agent.setLocalRoot(cli.getCurrentDirectory());
        conversationManager.startConversation(agent);
    }
}
