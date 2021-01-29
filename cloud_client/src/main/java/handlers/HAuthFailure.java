package handlers;

import events.EAuthFailure;
import logging.ClientLogger;
import cloud_core.transport.INetworkEndpoint;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptors;

@ApplicationScoped
@Interceptors(ClientLogger.class)
public class HAuthFailure
{
    @Inject
    private INetworkEndpoint networkEndpoint;

    private void handleAuthFailure(@ObservesAsync final EAuthFailure event)
    {
        System.out.println("Authentication failed. Reason: " + event.getReason());
        networkEndpoint.stop();
    }
}
