package net.canarymod.hook;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import net.canarymod.plugin.Priority;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HookHandler {
    Priority priority() default Priority.NORMAL;

    boolean ignoreCanceled() default false;
}
