package conversations;

import org.jetbrains.annotations.NotNull;
import cloud_core.conversations.RespondsTo;
import cloud_core.messages.IMessage;
import cloud_core.messages.pwd.PwdRequest;
import cloud_core.messages.pwd.PwdResponse;

import javax.inject.Inject;

@RespondsTo(PwdRequest.class)
public class PwdServerAgent extends ServerAgent
{
    @Inject
    private PwdResponse response;

    @Override
    public void processMessageFromPeer(@NotNull IMessage message)
    {
        response.setCurrentDirectory(getServerDirectory().pwd());
        sendMessageToPeer(response);
    }
}
