package handlers;

import cloud_core.events.EConversationTimedOut;
import logging.ServerLogger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import javax.interceptor.Interceptors;

@ApplicationScoped
@Interceptors(ServerLogger.class)
public class HConversationTimedOut
{
    private void handleConversationTimedOut(@ObservesAsync final EConversationTimedOut event)
    {

    }
}
