package handlers;

import logging.ClientLogger;
import cloud_core.events.EConversationComplete;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.interceptor.Interceptors;

@ApplicationScoped
@Interceptors(ClientLogger.class)
public class HConversationComplete
{
    private void handleConversationComplete(@ObservesAsync final EConversationComplete event)
    {

    }
}
