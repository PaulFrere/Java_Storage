package persistence.repositories;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import persistence.entitites.User;

public class UserRepository extends AbstractRepository<User>
{
    @Override
    public @NotNull Class<User> getEntityClass()
    {
        return User.class;
    }

    @Nullable
    public User findByName(@NotNull String name)
    {
        return selectByUniqueValue("name", name);
    }
}
