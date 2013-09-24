package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.api.OfflinePlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.Group;

/**
 * Command to list all the groups a player belongs to     
 *
 * @author Chris (damagefilter)
 */
public class PlayerGroupList implements NativeCommand {
    // player) group list <player>
    public void execute(MessageReceiver caller, String[] args) {
        if (args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "playermod group list");
            return;
        }
        Player target = Canary.getServer().matchPlayer(args[1]);

        if (target == null) {
            OfflinePlayer oplayer = Canary.getServer().getOfflinePlayer(args[1]);
            for (Group g : oplayer.getPlayerGroups()) {
                caller.message(g.getName() + (g.hasParent() ? " : " + g.getParent().getName() : ""));
            }
            return;
        }
        for (Group g : target.getPlayerGroups()) {
            caller.message(g.getName() + (g.hasParent() ? " : " + g.getParent().getName() : ""));
        }
    }
}
