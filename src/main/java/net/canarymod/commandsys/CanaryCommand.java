package net.canarymod.commandsys;

import net.canarymod.MessageReceiver;

/**
 * Contains methods common to all types of chat commands.
 * 
 * @author lightweight
 * 
 */
public abstract class CanaryCommand {
    public final String permissionNode;
    public final String tooltip;
    public final String errorMessage;
    public final int    minParam;
    public final int    maxParam;

    public CanaryCommand(String permissionNode, String tooltip) {
        this(permissionNode, tooltip, "Undefined", 0);
    }

    public CanaryCommand(String permissionNode, String tooltip, String errorMessage, int minParam) {
        this(permissionNode, tooltip, errorMessage, minParam, 0);
    }

    public CanaryCommand(String permissionNode, String tooltip, String errorMessage, int minParam, int maxParam) {
        this.permissionNode = permissionNode;
        this.tooltip = tooltip;
        this.errorMessage = errorMessage;
        this.minParam = minParam;
        this.maxParam = maxParam;
    }

    public boolean parseCommand(MessageReceiver caller, String[] parameters) {        
        if (parameters.length < minParam || (parameters.length > maxParam && maxParam != 0)) {
            onBadSyntax(caller, parameters);
            return false;
        }
        if(!caller.hasPermission(permissionNode)) {
            onPermissionDenied(caller);
            return false;
        }
        execute(caller, parameters);
        return true;
    }

    public void onPermissionDenied(MessageReceiver caller) {
        caller.notify("Unknown command");
        
    }

    public void onBadSyntax(MessageReceiver caller, String[] parameters) {
        if (!errorMessage.isEmpty()) {
            caller.notify(errorMessage);
        }
        else {
            caller.notify(tooltip);
        }
    }

    /**
     * Executes a command. Note: should not be called directly. Use
     * parseCommand() instead!
     * 
     * @param player
     * @param parameters
     */
    protected abstract void execute(MessageReceiver caller, String[] parameters);
}
