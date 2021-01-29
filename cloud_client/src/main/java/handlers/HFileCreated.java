package handlers;

import cloud_core.conversations.ActiveAgent;
import cloud_core.events.EFileCreated;
import conversations.ClientConversationManager;
import conversations.PutClientAgent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.enterprise.util.AnnotationLiteral;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptors;
import logging.ClientLogger;

import java.nio.file.Paths;

@ApplicationScoped
@Interceptors(ClientLogger.class)
public class HFileCreated
{
    private static final AnnotationLiteral<ActiveAgent> ACTIVE_AGENT_ANNOTATION = new AnnotationLiteral<ActiveAgent>() {};

    @Inject
    private ClientConversationManager conversationManager;

    private void handleFileCreated(@ObservesAsync final EFileCreated event)
    {
        final PutClientAgent agent = CDI.current().select(PutClientAgent.class, ACTIVE_AGENT_ANNOTATION).get();
        agent.setLocalRoot(Paths.get(event.getLocalRoot()));
        agent.setRelativeFilePath(Paths.get(event.getLocalRelativePath()));
        conversationManager.startConversation(agent);
    }
}
