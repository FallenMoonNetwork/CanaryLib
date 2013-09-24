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
 * Command to check if a player is in a group
 *
 * @author Chris (damagefilter)
 */
public class PlayerGroupCheck implements NativeCommand {
    // player) group check <player> <group>
    public void execute(MessageReceiver caller, String[] args) {
        if (args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "playermod group check");
            return;
        }
        Player target = Canary.getServer().matchPlayer(args[1]);

        if (target == null) {
            OfflinePlayer oplayer = Canary.getServer().getOfflinePlayer(args[1]);
            for (Group g : oplayer.getPlayerGroups()) {
                if (g.getName().equals(args[2])) {
                    caller.message(Colors.LIGHT_GREEN + args[2] + ": " + Translator.translate("yes"));
                    return;
                }
            }
            caller.message(Colors.LIGHT_RED + args[2] + ": " + Translator.translate("no"));
            return;
        }
        for (Group g : target.getPlayerGroups()) {
            if (g.getName().equals(args[2])) {
                caller.message(Colors.LIGHT_GREEN + args[2] + ": " + Translator.translate("yes"));
                return;
            }
        }
        caller.message(Colors.LIGHT_RED + args[2] + ": " + Translator.translate("no"));
    }
}
