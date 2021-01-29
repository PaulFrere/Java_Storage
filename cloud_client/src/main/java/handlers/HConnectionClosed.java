package handlers;

import command.CLI;
import events.EConnectionClosed;
import logging.ClientLogger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptors;

@ApplicationScoped
@Interceptors(ClientLogger.class)
public class HConnectionClosed
{
    @Inject
    private CLI cli;

    private void handleConnectionClosed(@ObservesAsync final EConnectionClosed event)
    {
        System.out.println("Disconnected.");
        cli.resetPrompt();
        cli.updatePrompt();
    }
}
