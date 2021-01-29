package conversations;

import org.jetbrains.annotations.NotNull;
import command.CLI;
import cloud_core.conversations.AbstractConversation;
import cloud_core.conversations.ActiveAgent;
import cloud_core.conversations.Expects;
import cloud_core.conversations.StartsWith;
import cloud_core.messages.IMessage;
import cloud_core.messages.ls.LsRequest;
import cloud_core.messages.ls.LsResponse;

import jakarta.inject.Inject;

@ActiveAgent
@StartsWith(LsRequest.class)
@Expects(LsResponse.class)
public class LsClientAgent extends AbstractConversation
{
    @Inject
    private CLI cli;

    @Override
    public void processMessageFromPeer(@NotNull IMessage message)
    {
        System.out.println();
        final LsResponse response = (LsResponse) message;
        response.getElements().stream()
                .filter(LsResponse.FilesystemElement::isDirectory)
                .forEach(directory -> System.out.println("<DIR>\t\t\t\t\t" + directory.getName()));

        response.getElements().stream()
                .filter(filesystemElement -> !filesystemElement.isDirectory())
                .forEach(file -> System.out.println("\t\t" + file.getSize() + "\t\t\t" + file.getName()));
        cli.updatePrompt();
    }
}
