package command;

import jakarta.inject.Qualifier;
import java.lang.annotation.*;

@Qualifier
@Inherited
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Command
{
}
