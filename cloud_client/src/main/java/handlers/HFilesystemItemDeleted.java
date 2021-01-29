package handlers;

import conversations.ClientConversationManager;
import conversations.RmClientAgent;
import logging.ClientLogger;
import cloud_core.conversations.ActiveAgent;
import cloud_core.events.EFilesystemItemDeleted;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.enterprise.util.AnnotationLiteral;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptors;

@ApplicationScoped
@Interceptors(ClientLogger.class)
public class HFilesystemItemDeleted
{
    private static final AnnotationLiteral<ActiveAgent> ACTIVE_AGENT_ANNOTATION = new AnnotationLiteral<ActiveAgent>() {};

    @Inject
    private ClientConversationManager conversationManager;

    private void handleFilesystemItemDeleted(@ObservesAsync final EFilesystemItemDeleted event)
    {
        final RmClientAgent agent = CDI.current().select(RmClientAgent.class, ACTIVE_AGENT_ANNOTATION).get();
        agent.setTargetPath(event.getLocalRelativePath());
        conversationManager.startConversation(agent);
    }
}
