package conversations;

import org.jetbrains.annotations.Nullable;
import cloud_core.conversations.AbstractConversation;
import cloud_core.conversations.PassiveAgent;
import filesystem.ServerDirectory;
import persistence.entitites.User;

import java.nio.file.Path;

@PassiveAgent
public abstract class ServerAgent extends AbstractConversation
{
    @Nullable
    public User getUser()
    {
        final ServerConversationManager conversationManager = (ServerConversationManager) getConversationManager();
        return conversationManager.getUser();
    }

    public Path getCurrentDirectory()
    {
        final ServerConversationManager conversationManager = (ServerConversationManager) getConversationManager();
        return conversationManager.getServerDirectory().getCurrentDirectory();
    }

    public ServerDirectory getServerDirectory()
    {
        final ServerConversationManager conversationManager = (ServerConversationManager) getConversationManager();
        return conversationManager.getServerDirectory();
    }
}
