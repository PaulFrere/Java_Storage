package handlers;

import cloud_core.messages.auth.AuthMessage;
import cloud_core.messages.auth.UnauthorizedResponse;
import cloud_core.transport.ITransportChannel;
import conversations.ServerConversationManager;
import events.EMessageReceived;
import logging.ServerLogger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import javax.interceptor.Interceptors;

@ApplicationScoped
@Interceptors(ServerLogger.class)
public class HMessageReceived
{
    private void handleMessageReceived(@ObservesAsync final EMessageReceived event)
    {
        final ServerConversationManager conversationManager = event.getConversationManager();
        final ITransportChannel transportChannel = conversationManager.getTransportChannel();
        if (conversationManager.isAuthenticated() || event.getMessage() instanceof AuthMessage)
        {
            conversationManager.dispatchMessage(event.getMessage());
        }
        else
        {
            final UnauthorizedResponse unauthorizedResponse = new UnauthorizedResponse();
            unauthorizedResponse.setConversationId(event.getMessage().getConversationId());
            try
            {
                transportChannel.sendMessage(new UnauthorizedResponse());
            }
            catch (Exception e)
            {
                transportChannel.closeSilently();
            }
        }
    }
}
