package command;

import cloud_core.filesystem.PathDoesNotExistException;
import cloud_core.filesystem.PathIsNotADirectoryException;

import jakarta.inject.Inject;

import static command.Const.TARGET_DIRECTORY_ARGUMENT_NAME;

@Keyword(Const.LCD_COMMAND_KEYWORD)
@Arguments(TARGET_DIRECTORY_ARGUMENT_NAME)
@Description(Const.LCD_COMMAND_DESCRIPTION)
public class LcdCommand extends AbstractCommand
{
    private final static String PATH_DOES_NOT_EXIST_MESSAGE = "Specified path does not exist";
    private final static String PATH_IS_NOT_A_DIR_MESSAGE = "Specified path is not a directory";

    @Inject
    private CLI cli;

    @Override
    public void run()
    {
        try
        {
            final String path = getArgumentValue(TARGET_DIRECTORY_ARGUMENT_NAME);
            if (path == null) return;
            cli.cd(path);
        }
        catch (PathDoesNotExistException e)
        {
            System.out.println(PATH_DOES_NOT_EXIST_MESSAGE);
        }
        catch (PathIsNotADirectoryException e)
        {
            System.out.println(PATH_IS_NOT_A_DIR_MESSAGE);
        }
    }
}