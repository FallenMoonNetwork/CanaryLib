package net.canarymod.commandsys;


import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;


/**
 * Used in {@link CommandManager} to determine which strings invoke the annotated
 * {@link CanaryCommand}
 * @author Willem Mulder
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface Command {

    /**
     * The command names
     * @return value
     */
    String[] value() default "";
}
