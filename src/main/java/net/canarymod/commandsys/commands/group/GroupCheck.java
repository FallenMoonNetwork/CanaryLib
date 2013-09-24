package net.canarymod.commandsys.commands.group;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.Group;

/**
 * Command to check the values of a group   
 *
 * @author Chris (damagefilter)
 */
public class GroupCheck implements NativeCommand {
    // groupmod check <group>
    public void execute(MessageReceiver caller, String[] args) {
        if (args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "groupmod check");
            return;
        }
        Group g = Canary.usersAndGroups().getGroup(args[1]);
        if (g == null) {
            caller.notice(Translator.translateAndFormat("unknown group", args[1]));
            return;
        }
        caller.message("Name: " + g.getName());
        caller.message("ID: " + g.getId());
        caller.message("Prefix: " + Colors.MARKER + g.getPrefix() + g.getPrefix());
        caller.message("Parent: " + (g.hasParent() ? g.getParent().getName() : Translator.translate("no")));
    }
}
