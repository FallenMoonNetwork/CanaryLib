package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.Group;

/**
 * Command to add a player (for permissions, groups, etc.) to the database    
 *
 * @author Chris (damagefilter)
 */
public class PlayerCreate implements NativeCommand {
    // player) add <playername> <groupname>
    public void execute(MessageReceiver caller, String[] args) {
        if (args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "playermod add");
            return;
        }
        Group g = Canary.usersAndGroups().getGroup(args[2]);
        Player target = Canary.getServer().matchPlayer(args[1]);
        if (g == null) {
            caller.notice(Translator.translateAndFormat("unknown group", args[2]));
            return;
        }

        if (target == null) {
            Canary.usersAndGroups().addOfflinePlayer(args[1], g.getName());
            caller.message(Colors.YELLOW + Translator.translate("modify group set"));
            return;
        }
        target.setGroup(g);
        caller.message(Colors.YELLOW + Translator.translate("modify group set"));
    }
}
