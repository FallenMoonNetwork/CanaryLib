package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to remove player data from the database   
 *
 * @author Chris (damagefilter)
 */
public class PlayerRemove implements NativeCommand {
    // player) remove <playername>
    public void execute(MessageReceiver caller, String[] args) {
        if (args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "playermod remove");
            return;
        }
        Player target = Canary.getServer().matchPlayer(args[1]);
        if (target == null) {
            Canary.usersAndGroups().removeUserData(args[1]);
            caller.notice(Translator.translate("modify player removed"));
            return;
        }
        target.setGroup(Canary.usersAndGroups().getDefaultGroup());
        Canary.usersAndGroups().removeUserData(target.getName());
        caller.notice(Translator.translate("modify player removed"));
    }
}
