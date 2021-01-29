package conversations;

import lombok.Getter;
import lombok.Setter;
import cloud_core.conversations.AbstractConversationManager;
import filesystem.ServerDirectory;
import persistence.entitites.User;

public class ServerConversationManager extends AbstractConversationManager
{
    @Getter
    @Setter
    private User user;

    public boolean isAuthenticated()
    {
        return user != null;
    }

    @Getter
    private ServerDirectory serverDirectory = new ServerDirectory();
}
