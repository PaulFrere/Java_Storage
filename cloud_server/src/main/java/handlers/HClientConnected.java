package handlers;

import events.EClientConnected;
import logging.ServerLogger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import javax.interceptor.Interceptors;

@ApplicationScoped
@Interceptors(ServerLogger.class)
public class HClientConnected
{
    private void handleClientConnected(@ObservesAsync final EClientConnected event)
    {

    }
}
