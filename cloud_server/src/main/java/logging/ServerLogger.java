package logging;

import lombok.SneakyThrows;
import cloud_core.events.EConversationComplete;
import cloud_core.events.EConversationFailed;
import cloud_core.events.EConversationTimedOut;
import cloud_core.logging.CommonLogger;
import events.EClientConnected;
import events.EMessageReceived;
import handlers.*;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class ServerLogger extends CommonLogger
{
    @AroundInvoke
    @SneakyThrows
    private Object logMethod(final InvocationContext invocationContext)
    {
        final Class targetClass = invocationContext.getTarget().getClass();
        if (HClientConnected.class.isAssignableFrom(targetClass))
        {
            final EClientConnected event = (EClientConnected) invocationContext.getParameters()[0];
            final String remoteAddress = event
                    .getConversationManager()
                    .getTransportChannel()
                    .getRemoteAddress();
            write("Connection from " + remoteAddress + " accepted.");
        }
        else if (HMessageReceived.class.isAssignableFrom(targetClass))
        {
            final EMessageReceived event = (EMessageReceived) invocationContext.getParameters()[0];
            final String remoteAddress = event
                    .getConversationManager()
                    .getTransportChannel()
                    .getRemoteAddress();
            final String messageType = event.getMessage().getClass().getSimpleName();
            final String conversationId = event.getMessage().getConversationId();
            write("A message of type " +
                    messageType +
                    " bound to conversation " +
                    conversationId +
                    " was received from " +
                    remoteAddress);
        }
        else if (HConversationFailed.class.isAssignableFrom(targetClass))
        {
            final EConversationFailed event = (EConversationFailed) invocationContext.getParameters()[0];
            write("Conversation " + event.getConversation().getId() + " failed. Reason: " +
                    event.getReason() + ". Remote: " + event.isRemote());
        }
        else if (HConversationTimedOut.class.isAssignableFrom(targetClass))
        {
            final EConversationTimedOut event = (EConversationTimedOut) invocationContext.getParameters()[0];
            write("Conversation " + event.getConversation().getId() + " timed out.");
        }
        else if (HConversationComplete.class.isAssignableFrom(targetClass))
        {
            final EConversationComplete event = (EConversationComplete) invocationContext.getParameters()[0];
            write("Conversation " + event.getConversation().getId() + " completed.");
        }
        return invocationContext.proceed();
    }
}
