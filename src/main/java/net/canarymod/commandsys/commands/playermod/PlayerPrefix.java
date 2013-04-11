package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;

public class PlayerPrefix {
    //player) prefix <player> <prefix>
    public void execute(MessageReceiver caller, String[] args) {
        if(args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "playermod prefix");
            return;
        }
        Player target = Canary.getServer().matchPlayer(args[1]);
        if(target == null) {
            String[] data = Canary.usersAndGroups().getPlayerData(args[1]);
            data[0] = args[2];
            Canary.usersAndGroups().updatePlayerData(args[1], data);
            caller.message(Colors.YELLOW + Translator.translate("modify prefix set"));
            return;
        }
        target.setColor(args[2]);
        caller.message(Colors.YELLOW + Translator.translate("modify prefix set"));
    }
}
