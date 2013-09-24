package net.canarymod.commandsys;

/**
 * Thrown when a commands parent command is not registered upon attempting
 * to register the child command
 *
 * @author Chris (damagefilter)
 */
public class CommandDependencyException extends Exception {

    private static final long serialVersionUID = 6961878125185151847L;

    public CommandDependencyException(String str) {
        super(str);
    }
}
