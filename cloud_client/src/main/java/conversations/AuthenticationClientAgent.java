package conversations;

import org.jetbrains.annotations.NotNull;
import config.ClientConfig;
import events.EAuthFailure;
import events.EAuthSuccess;
import cloud_core.conversations.AbstractConversation;
import cloud_core.conversations.ActiveAgent;
import cloud_core.conversations.Expects;
import cloud_core.conversations.StartsWith;
import cloud_core.messages.IMessage;
import cloud_core.messages.auth.AuthFailResponse;
import cloud_core.messages.auth.AuthMessage;
import cloud_core.messages.auth.AuthSuccessResponse;
import cloud_core.utils.Util;

import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ActiveAgent
@StartsWith(AuthMessage.class)
@Expects({AuthFailResponse.class, AuthSuccessResponse.class})
public class AuthenticationClientAgent extends AbstractConversation
{
    @Inject
    private ClientConfig config;

    @Inject
    private Event<EAuthFailure> authFailureBus;

    @Inject
    private Event<EAuthSuccess> authSuccessBus;

    @Override
    protected void beforeStart(@NotNull IMessage initialMessage)
    {
        final AuthMessage authMessage = (AuthMessage) initialMessage;
        authMessage.setLogin(config.getLogin());
        authMessage.setPasswordHash(Util.hash(config.getPassword()));
    }

    @Override
    public synchronized void processMessageFromPeer(@NotNull IMessage message)
    {
        if (message instanceof AuthFailResponse)
        {
            final AuthFailResponse authFailResponse = (AuthFailResponse) message;
            authFailureBus.fireAsync(new EAuthFailure(authFailResponse.getReason()));
        }
        else if (message instanceof AuthSuccessResponse)
        {
            authSuccessBus.fireAsync(new EAuthSuccess(getConversationManager()));
        }
    }
}
