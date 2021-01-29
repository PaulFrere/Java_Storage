package conversations;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import command.CLI;
import cloud_core.conversations.AbstractConversation;
import cloud_core.conversations.ActiveAgent;
import cloud_core.conversations.Expects;
import cloud_core.conversations.StartsWith;
import cloud_core.messages.IMessage;
import cloud_core.messages.ServerOkResponse;
import cloud_core.messages.cd.CdFailResponse;
import cloud_core.messages.cd.CdRequest;

import jakarta.inject.Inject;
import java.nio.file.Paths;

@ActiveAgent
@StartsWith(CdRequest.class)
@Expects({ServerOkResponse.class, CdFailResponse.class})
public class CdClientAgent extends AbstractConversation
{
    private static final String FAIL_MESSAGE = "Remote change dir failed. Reason: ";

    @Inject
    private CLI cli;

    @Getter
    @Setter
    private String targetDirectory;

    @Override
    protected void beforeStart(@NotNull IMessage initialMessage)
    {
        final CdRequest cdRequest = (CdRequest) initialMessage;
        cdRequest.setTargetDirectory(targetDirectory);
    }

    @Override
    public void processMessageFromPeer(@NotNull IMessage message)
    {
        if (message instanceof CdFailResponse)
        {
            final CdFailResponse cdFailResponse = (CdFailResponse) message;
            System.out.println();
            System.out.println(FAIL_MESSAGE + cdFailResponse.getReason());
        }
        else if (message instanceof ServerOkResponse)
        {
            cli.setRemoteDirectory(Paths.get(targetDirectory).normalize().toString());
            cli.updatePrompt();
        }
    }
}
