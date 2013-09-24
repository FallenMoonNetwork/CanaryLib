package net.canarymod.commandsys.commands.group;

import net.canarymod.Canary;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.Group;

/**
 * Command to list all the groups defined   
 *
 * @author Chris (damagefilter)
 */
public class GroupList implements NativeCommand {
    // group) list
    public void execute(MessageReceiver caller, String[] args) {
        if (args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "groupmod list");
            return;
        }
        for (Group g : Canary.usersAndGroups().getGroups()) {
            StringBuilder line = new StringBuilder();
            line.append(Colors.YELLOW).append("Name: ").append(Colors.WHITE).append(g.getName());
            if (g.hasParent()) {
                line.append(", ").append(Colors.YELLOW).append("Parent: ").append(Colors.WHITE).append(g.getParent().getName());
            }
            caller.message(line.toString());
        }
    }
}
