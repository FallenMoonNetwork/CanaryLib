package net.canarymod.commandsys.commands.group;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.Group;

/**
 * Command to remove a group   
 *
 * @author Chris (damagefilter)
 */
public class GroupRemove implements NativeCommand {

    // group remove <name>
    public void execute(MessageReceiver caller, String[] args) {
        if (args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "group remove");
            return;
        }
        Group group = Canary.usersAndGroups().getGroup(args[1]);
        if (group == null) {
            caller.notice(Translator.translateAndFormat("unknown group", args[1]));
            return;
        }
        if (group.getName().equalsIgnoreCase(Canary.usersAndGroups().getDefaultGroup().getName())) {
            caller.notice(Translator.translate("group remove default group"));
            return;
        }
        // Fix players that had the said group
        for (Player player : Canary.getServer().getPlayerList()) {
            if (player.getGroup().getName().equals(group.getName())) {
                player.setGroup(group.getParent());
            }
        }
        Canary.usersAndGroups().removeGroup(group);
        caller.message(Colors.YELLOW + Translator.translateAndFormat("group removed", group.getName()));
    }
}
