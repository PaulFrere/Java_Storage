package handlers;

import command.CLI;
import events.EAuthSuccess;
import logging.ClientLogger;
import cloud_core.conversations.IConversationManager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptors;

@ApplicationScoped
@Interceptors(ClientLogger.class)
public class HAuthSuccess
{
    @Inject
    private CLI cli;

    private void handleAuthSuccess(@ObservesAsync final EAuthSuccess event)
    {
        final IConversationManager conversationManager = event.getConversationManager();
        final String server = conversationManager.getTransportChannel().getRemoteAddress();
        cli.setRemoteServer(server);
        cli.setRemoteDirectory("/");
        cli.updatePrompt();
    }
}