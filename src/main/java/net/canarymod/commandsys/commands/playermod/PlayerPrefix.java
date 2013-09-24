package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.OfflinePlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.visualillusionsent.utils.StringUtils;

/**
 * Command to change the prefix of a player   
 *
 * @author Chris (damagefilter)
 */
public class PlayerPrefix implements NativeCommand {
    // player) prefix <player> [prefix]
    public void execute(MessageReceiver caller, String[] args) {
        if (args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "playermod prefix");
            return;
        }
        String prefix = null;
        if (args.length >= 3) {
            prefix = StringUtils.joinString(args, " ", 2).replaceAll("&([A-FK-Oa-fk-oRr0-9])", "\u00A7$1");
        }
        Player target = Canary.getServer().matchPlayer(args[1]);
        if (target == null) {
            OfflinePlayer oplayer = Canary.getServer().getOfflinePlayer(args[1]);
            oplayer.setPrefix(prefix);
            caller.message(Colors.YELLOW + Translator.translate("modify prefix set"));
            return;
        }
        target.setPrefix(prefix);
        caller.message(Colors.YELLOW + Translator.translate("modify prefix set"));
    }
}
