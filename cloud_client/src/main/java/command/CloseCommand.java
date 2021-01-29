package command;

import cloud_core.transport.INetworkEndpoint;

import jakarta.inject.Inject;

import static command.Const.CLOSE_COMMAND_KEYWORD;

@Importance(5)
@Keyword(CLOSE_COMMAND_KEYWORD)
@Description(Const.CLOSE_COMMAND_DESCRIPTION)
public class CloseCommand extends AbstractCommand
{
    @Inject
    private INetworkEndpoint networkEndpoint;

    @Override
    public void run()
    {
        networkEndpoint.stop();
    }
}
