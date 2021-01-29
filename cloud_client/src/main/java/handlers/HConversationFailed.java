package handlers;

import logging.ClientLogger;
import cloud_core.events.EConversationFailed;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.interceptor.Interceptors;

@ApplicationScoped
@Interceptors(ClientLogger.class)
public class HConversationFailed
{
    private void handleConversationFailed(@ObservesAsync final EConversationFailed event)
    {

    }
}
