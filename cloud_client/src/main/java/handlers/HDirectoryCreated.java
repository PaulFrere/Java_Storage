package handlers;

import conversations.ClientConversationManager;
import conversations.MkdirClientAgent;
import logging.ClientLogger;
import cloud_core.conversations.ActiveAgent;
import cloud_core.events.EDirectoryCreated;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.enterprise.util.AnnotationLiteral;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptors;

@ApplicationScoped
@Interceptors(ClientLogger.class)
public class HDirectoryCreated
{
    private static final AnnotationLiteral<ActiveAgent> ACTIVE_AGENT_ANNOTATION = new AnnotationLiteral<ActiveAgent>() {};

    @Inject
    private ClientConversationManager conversationManager;

    private void handleDirectoryCreated(@ObservesAsync final EDirectoryCreated event)
    {
        final MkdirClientAgent agent = CDI.current().select(MkdirClientAgent.class, ACTIVE_AGENT_ANNOTATION).get();
        agent.setDirectoryPath(event.getLocalRelativePath());
        conversationManager.startConversation(agent);
    }
}
