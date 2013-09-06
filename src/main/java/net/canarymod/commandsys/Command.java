package net.canarymod.commandsys;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Used to annotate {@link CommandListener} methods and describe how a command should be handled within the {@link CommandManager} {@link CanaryCommand}
 *
 * @author Willem Mulder
 * @author Chris (damagefilter)
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface Command {

    /**
     * The command names
     *
     * @return string array of aliases
     */
    String[] aliases();

    /**
     * A list of permissions to use this command.
     * If you specify more than one, only one of them is needed to execute the command
     *
     * @return string array of permissions
     */
    String[] permissions();

    /**
     * What does this command do?
     * This will be displayed in a help context.
     * Note: This string will be pushed through the translator that is attached to this command.
     * If it finds a respective translation, it will output that instead
     *
     * @return description string
     */
    String description();

    /**
     * The tip to display when command parsing failed.
     * This may also be displayed when help for this command
     * was specifically requested
     *
     * @return the tool tip
     */
    String toolTip();

    /**
     * The parent command, for creating sub-command structures
     *
     * @return parent command
     */
    String parent() default "";

    /**
     * Explicitly define a name with which the command will be registered
     * at the help system. If this is empty (default), all aliases will be registered.
     * Otherwise only this name will be registered. <br>
     * Use it for registering sub-command helps to avoid name conflicts
     *
     * @return help lookup string
     */
    String helpLookup() default "";

    /**
     * Specifies specific terms for looking up this command in help search
     *
     * @return string array of search terms
     */
    String[] searchTerms() default { " " };

    /**
     * Min amount of parameters
     *
     * @return minimum parameters
     */
    int min() default 1;

    /**
     * The max amounts of parameters. -1 for infinite amount
     *
     * @return maximum parameters
     */
    int max() default -1;
}
