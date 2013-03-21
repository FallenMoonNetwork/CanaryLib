package net.canarymod.commandsys;

import net.canarymod.chat.MessageReceiver;

/**
 * Contains methods common to all types of chat commands.
 * 
 * @author lightweight
 * @author Willem Mulder
 */
public abstract class CanaryCommand {
    
    /** The permission node for this command. */
    public final String permissionNode;
    
    /** The text that shows up in the help command. */
    public final String tooltip;
    
    /**
     * The error message. This shows up when there are less than 
     * <tt>minParam</tt> parameters, or when {@link
     * #onBadSyntax(net.canarymod.chat.MessageReceiver, java.lang.String[])} is
     * called.
     */
    public final String errorMessage;
    
    /**
     * The minimum number of parameters for this command. If the number of
     * parameters is less than this number, {@link
     * #onBadSyntax(net.canarymod.chat.MessageReceiver, java.lang.String[])} will be
     * called.
     */
    public final int    minParam;
    
    /**
     * The maximum number of parameters for this command. If the number of
     * parameters is greater than this number, {@link
     * #onBadSyntax(net.canarymod.chat.MessageReceiver, java.lang.String[])} will be 
     * called.
     */
    public final int    maxParam;

    /**
     * Creates a new CanaryCommand with the specified permission and tooltip.
     * Defaults to no parameter count restrictions, and the error message "There
     * was an error in your syntax"
     * @param permissionNode The permission node for this command.
     * @param tooltip The text that shows up in the help command.
     */
    public CanaryCommand(String permissionNode, String tooltip) {
        this(permissionNode, tooltip, "There was an error in your syntax", 0);
    }

    /**
     * Creates a new CanaryCommand using the specified parameters.
     * Defaults to no maximun parameter count.
     * @param permissionNode The permission node for this command.
     * @param tooltip The text that shows up in the help command.
     * @param errorMessage The message that shows up when there are less than
     * <tt>minParam</tt> parameters, or when {@link
     * #onBadSyntax(net.canarymod.chat.MessageReceiver, java.lang.String[])} is
     * called.
     * @param minParam The minimum number of parameters for this command. If the
     * number of parameters is less than this number, {@link
     * #onBadSyntax(net.canarymod.chat.MessageReceiver, java.lang.String[])} will be
     * called.
     */
    public CanaryCommand(String permissionNode, String tooltip, String errorMessage, int minParam) {
        this(permissionNode, tooltip, errorMessage, minParam, 0);
    }

    
    /**
     * Creates a new CanaryCommand using the specified parameters.
     * @param permissionNode The permission node for this command.
     * @param tooltip The text that shows up in the help command.
     * @param errorMessage The message that shows up when there are less than
     * <tt>minParam</tt> or more then <tt>maxParam</tt> parameters, or when
     * {@link #onBadSyntax(net.canarymod.chat.MessageReceiver, java.lang.String[])}
     * is called.
     * @param minParam The minimum number of parameters for this command. If the
     * number of parameters is less than this number, {@link
     * #onBadSyntax(net.canarymod.chat.MessageReceiver, java.lang.String[])} will be
     * called.
     * @param maxParam The maximum number of parameters for this command. If the
     * number of parameters is greater than this number, {@link
     * #onBadSyntax(net.canarymod.chat.MessageReceiver, java.lang.String[])} will be 
     * called.
     */
    public CanaryCommand(String permissionNode, String tooltip, String errorMessage, int minParam, int maxParam) {
        this.permissionNode = permissionNode;
        this.tooltip = tooltip;
        this.errorMessage = errorMessage;
        this.minParam = minParam;
        this.maxParam = maxParam;
    }

    /**
     * Parses this command using the specified parameters.
     * @param caller This command's caller.
     * @param parameters The parameters for the command to use.
     * @return <tt>true</tt> if the command was executed, <tt>false</tt> otherwise.
     */
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

    /**
     * Called when the permission to this command is denied.
     * @param caller This command's caller.
     */
    protected void onPermissionDenied(MessageReceiver caller) {
        caller.notice("Unknown command");
        
    }

    /**
     * Should be called when a bad syntax is detected.
     * Called automatically when the number of parameters is out of range.
     *
     * @param caller This command's caller.
     * @param parameters The parameters to the command (including the command itself).
     */
    protected void onBadSyntax(MessageReceiver caller, String[] parameters) {
        if (!errorMessage.isEmpty()) {
            caller.notice(errorMessage);
        }
        else {
            caller.notice(tooltip);
        }
    }

    /**
     * Executes a command.
     * NOTE: should not be called directly. Use parseCommand() instead!
     * 
     * @param caller This command's caller.
     * @param parameters The parameters to this command (including the command itself).
     */
    protected abstract void execute(MessageReceiver caller, String[] parameters);
}
