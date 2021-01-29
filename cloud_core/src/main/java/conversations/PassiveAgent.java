package conversations;

import jakarta.inject.Qualifier;
import java.lang.annotation.*;

@Qualifier
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PassiveAgent
{
}
