package net.canarymod.commandsys;

public class CommandDependencyException extends Exception {

    private static final long serialVersionUID = 6961878125185151847L;

    public CommandDependencyException(String str) {
        super(str);
    }
}
