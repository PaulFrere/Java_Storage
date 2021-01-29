package handlers;

import cloud_core.events.EConversationComplete;
import logging.ServerLogger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import javax.interceptor.Interceptors;

@ApplicationScoped
@Interceptors(ServerLogger.class)
public class HConversationComplete
{
    private void handleConversationComplete(@ObservesAsync final EConversationComplete event)
    {

    }
}
