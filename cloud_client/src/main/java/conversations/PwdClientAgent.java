package conversations;

import org.jetbrains.annotations.NotNull;
import command.CLI;
import cloud_core.conversations.AbstractConversation;
import cloud_core.conversations.ActiveAgent;
import cloud_core.conversations.Expects;
import cloud_core.conversations.StartsWith;
import cloud_core.messages.IMessage;
import cloud_core.messages.pwd.PwdRequest;
import cloud_core.messages.pwd.PwdResponse;

import jakarta.inject.Inject;

@ActiveAgent
@StartsWith(PwdRequest.class)
@Expects(PwdResponse.class)
public class PwdClientAgent extends AbstractConversation
{
    @Inject
    private CLI cli;

    @Override
    public void processMessageFromPeer(@NotNull IMessage message)
    {
        final PwdResponse response = (PwdResponse) message;
        System.out.println();
        System.out.println("Current remote dir is " + response.getCurrentDirectory());
        cli.updatePrompt();
    }
}
