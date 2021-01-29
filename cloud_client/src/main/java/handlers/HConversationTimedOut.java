package handlers;

import logging.ClientLogger;
import cloud_core.events.EConversationTimedOut;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.interceptor.Interceptors;

@ApplicationScoped
@Interceptors(ClientLogger.class)
public class HConversationTimedOut
{
    private void handleConversationTimedOut(@ObservesAsync final EConversationTimedOut event)
    {

    }
}
