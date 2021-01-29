package command;

import lombok.SneakyThrows;
import cloud_core.filesystem.DirectoryWatcher;

import jakarta.inject.Inject;

@Keyword(Const.WATCH_COMMAND_KEYWORD)
@Description(Const.WATCH_COMMAND_DESCRIPTION)
@Arguments(Const.TARGET_DIRECTORY_ARGUMENT_NAME)
public class WatchCommand extends AbstractCommand
{
    @Inject
    private CLI cli;

    @Inject
    private DirectoryWatcher directoryWatcher;

    @Override
    @SneakyThrows
    public void run()
    {
        directoryWatcher.watchDirectory(cli.getCurrentDirectory().resolve(getArgumentValue(Const.TARGET_DIRECTORY_ARGUMENT_NAME)).toString());
        directoryWatcher.run();
    }
}
