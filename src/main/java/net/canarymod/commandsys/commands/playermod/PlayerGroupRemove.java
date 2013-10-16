package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.OfflinePlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.Group;

/**
 * Command to remove a group from a player (not main group)
 *
 * @author Chris (damagefilter)
 */
public class PlayerGroupRemove implements NativeCommand {
    // player) group remove <player> <group>
    public void execute(MessageReceiver caller, String[] args) {
        if (args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "playermod group remove");
            return;
        }
        Player target = Canary.getServer().matchPlayer(args[1]);
        Group group = Canary.usersAndGroups().getGroup(args[2]);
        if (group == null) {
            caller.notice(Translator.translateAndFormat("unknown group", args[2]));
            return;
        }
        if (target == null) {
            OfflinePlayer oplayer = Canary.getServer().getOfflinePlayer(args[1]);
            if (oplayer.removeGroup(group)) {
                caller.message(Colors.YELLOW + Translator.translate("modify group removed"));
            }
            else {
                caller.message(Colors.YELLOW + Translator.translate("modify group removed failed"));
            }
            return;
        }

        if (target.removeGroup(group)) {
            caller.message(Colors.YELLOW + Translator.translate("modify group removed"));
        }
        else {
            caller.message(Colors.YELLOW + Translator.translate("modify group removed failed"));
        }
    }
}
