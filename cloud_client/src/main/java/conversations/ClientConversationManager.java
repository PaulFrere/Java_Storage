package conversations;

import cloud_core.conversations.AbstractConversationManager;
import cloud_core.conversations.ActiveAgent;
import cloud_core.conversations.IConversation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.enterprise.util.AnnotationLiteral;

@ApplicationScoped
public class ClientConversationManager extends AbstractConversationManager
{
    private static final AnnotationLiteral<ActiveAgent> ACTIVE_AGENT_ANNOTATION = new AnnotationLiteral<ActiveAgent>() {};

    public <T extends IConversation> void startConversation(Class<T> conversationClass)
    {
        startConversation(CDI.current().select(conversationClass, ACTIVE_AGENT_ANNOTATION).get());
    }

    public void authenticate()
    {
        startConversation(AuthenticationClientAgent.class);
    }
}
