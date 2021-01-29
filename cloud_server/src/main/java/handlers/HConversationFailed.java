package handlers;

import cloud_core.events.EConversationFailed;
import logging.ServerLogger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import javax.interceptor.Interceptors;

@ApplicationScoped
@Interceptors(ServerLogger.class)
public class HConversationFailed
{
    private void handleConversationFailed(@ObservesAsync final EConversationFailed event)
    {

    }
}
