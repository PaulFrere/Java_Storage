package conversations;

import org.jetbrains.annotations.NotNull;
import cloud_core.conversations.RespondsTo;
import cloud_core.messages.IMessage;
import cloud_core.messages.auth.AuthFailResponse;
import cloud_core.messages.auth.AuthMessage;
import cloud_core.messages.auth.AuthSuccessResponse;
import persistence.entitites.User;
import persistence.repositories.UserRepository;

import javax.inject.Inject;
import java.nio.file.Paths;

@RespondsTo(AuthMessage.class)
public class AuthenticationServerAgent extends ServerAgent
{
    private static final String UNKNOWN_USER_MESSAGE = "Unknown user";
    private static final String WRONG_PASSWORD_MESSAGE = "Wrong password";

    @Inject
    private UserRepository userRepository;

    @Override
    public void processMessageFromPeer(final @NotNull IMessage message)
    {
        if (message instanceof AuthMessage)
        {
            final ServerConversationManager conversationManager = (ServerConversationManager) getConversationManager();
            final AuthMessage authMessage = (AuthMessage) message;
            final User user = userRepository.findByName(authMessage.getLogin());
            IMessage response = null;
            if (user == null)
            {
                final AuthFailResponse unknownUserResponse = new AuthFailResponse();
                unknownUserResponse.setReason(UNKNOWN_USER_MESSAGE);
                response = unknownUserResponse;
            }
            else if (!user.getPasswordHash().equals(authMessage.getPasswordHash()))
            {
                final AuthFailResponse wrongPasswordResponse = new AuthFailResponse();
                wrongPasswordResponse.setReason(WRONG_PASSWORD_MESSAGE);
                response = wrongPasswordResponse;
            }
            else
            {
                conversationManager.setUser(user);
                conversationManager.getServerDirectory().setRootDirectory(Paths.get(user.getHomeDirectory()));
                response = new AuthSuccessResponse();
            }

            sendMessageToPeer(response);
        }
    }
}
