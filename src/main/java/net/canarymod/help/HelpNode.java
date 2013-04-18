package net.canarymod.help;

import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandOwner;

/**
 * Contains relevant information about a piece of help.
 * One help node contains the localized description of a command, its tooltip,
 * required permissions, keywords (currently unused), parent and a list of sub commands.
 * @author Chris (damagefilter)
 * @author Jarvix
 *
 */
public class HelpNode {
    /** The Plugin (or Server) that has registered this command */
    public CommandOwner plugin;
    /** The name of this command, without the slash*/
    public String command;
    /** The localized description of this command*/
    public String description;
    /** The tooltip that is usually shown when the command was used wrongly*/
    public String toolTip;
    /** The list of permissions for this command. One of those is required in order for the command to be allowed*/
    public String[] permissions;
    /** Search keywords. They are used for help-search lookup*/
    public String[] keywords;
    /** The parent command. If this command is not a subcommand, this string is empty so that isEmpty() returns true*/
    public String parent;
    /** Is true when this command is a subcommand (!parent.isEmpty())*/
    public boolean isSubgroup;
    /** A list of names of sub commands*/
    public String[] subCommands;

    /**
     * Checks if a MessageReceiver (Player for instance) can use the command associated with this help node
     * @param caller
     * @return true if player can use this command, false otherwise
     */
    public boolean canUse(MessageReceiver caller) {
        if(caller == null) {
            return true;
        }
        for(String perm : permissions) {
            if(caller.hasPermission(perm)) {
                return true;
            }
        }
        return false;
    }
}