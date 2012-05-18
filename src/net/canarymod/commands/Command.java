package net.canarymod.commands;

import net.canarymod.api.entity.Player;

/**
 * Basic command class. It contains some pre-defined methods for a basic
 * evaluation of commands and a run() for implementation of different commands
 * 
 * @author Chris
 * 
 */
public abstract class Command {
    String permission, name, toolTip;
    boolean permissionValue;
    int maxParams, minParams;

    /**
     * Create a new command by this name (for example /warp)
     * 
     * @param name
     * @param permission
     * @param tooltip
     * @param permissionValue
     * @param minParams
     * @param maxParams
     */
    public Command(String name, String permission, String tooltip, boolean permissionValue, int minParams, int maxParams) {
        this.name = name;
        this.permission = permission;
        this.toolTip = tooltip;
        this.permissionValue = permissionValue;
        this.minParams = minParams;
        this.maxParams = maxParams;
    }

    public Command(String name, String permission, String tooltip, boolean permissionValue, int minParams) {
        this.name = name;
        this.permission = permission;
        this.toolTip = tooltip;
        this.permissionValue = permissionValue;
        this.minParams = minParams;
        this.maxParams = -1; //-1 is unspecified
    }

    /**
     * Validate the command against a given player and the specifications set
     * with the Ctor.<br>
     * <b>ONLY OVERRIDE THIS IF YOU KNOW WHAT YOU'RE DOING THERE!</b>
     * 
     * @param player
     * @param command
     * @return
     */
    public boolean parseCommand(Player player, String[] command) { // TODO #Jos @Chris: Rename to validateCommand, because that is what it does
        if (!name.equalsIgnoreCase(command[0])) {
            return false; //Whoops, wrong command!
        }
        if (!player.hasPermission(permission)) {
            return false; //TODO: Send denied message!
        }
        if ((command.length < minParams) && ((command.length > maxParams) || (maxParams != -1))) {
            return false; //TODO: Send tooltip
        }
        //Okay, all validation has passed, this command seems to be in order!
        return true;
    }

    /**
     * Implement this. You can put your logic here and issue
     * {@link Command#parseCommand(Player, String[])} to validate the command
     * and player permissions
     * 
     * @param player
     * @param command
     */
    public abstract void run(Player player, String[] command);
}
