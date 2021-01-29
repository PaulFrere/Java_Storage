package conversations;

import jakarta.inject.Qualifier;
import java.lang.annotation.*;

@Qualifier
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface ActiveAgent
{
}
