package handlers;

import events.EMessageReceived;
import logging.ClientLogger;
import cloud_core.conversations.IConversationManager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptors;

@ApplicationScoped
@Interceptors(ClientLogger.class)
public class HMessageReceived
{
    @Inject
    private IConversationManager conversationManager;

    private void handleMessageReceived(@ObservesAsync final EMessageReceived event)
    {
        conversationManager.dispatchMessage(event.getMessage());
    }
}
