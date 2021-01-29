package command;

import config.ClientConfig;
import cloud_core.transport.INetworkEndpoint;

import jakarta.inject.Inject;

import static command.Const.*;

@Importance(6)
@Keyword(Const.OPEN_COMMAND_KEYWORD)
@Description(Const.OPEN_COMMAND_DESCRIPTION)
@Arguments({SERVER_ARGUMENT_NAME, LOGIN_ARGUMENT_NAME, PASSWORD_ARGUMENT_NAME})
public class OpenCommand extends AbstractCommand
{
    @Inject
    private ClientConfig config;

    @Inject
    private INetworkEndpoint networkEndpoint;

    @Override
    public void run()
    {
        config.setServerAddress(getArgumentValue(SERVER_ARGUMENT_NAME));
        config.setLogin(getArgumentValue(LOGIN_ARGUMENT_NAME));
        config.setPassword(getArgumentValue(PASSWORD_ARGUMENT_NAME));

        networkEndpoint.start();
    }
}