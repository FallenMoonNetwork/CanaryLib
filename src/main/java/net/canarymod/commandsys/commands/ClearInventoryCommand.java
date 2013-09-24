package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to clear a players (or your own) inventory
 *
 * @author Chris (damagefilter)
 */
public class ClearInventoryCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] args) {
        if (args.length > 1) {
            Player target = Canary.getServer().matchPlayer(args[1]);
            if (target == null) {
                caller.notice(Translator.translateAndFormat("unknown player", args[1]));
            }
            else {
                target.getInventory().clearContents();
                caller.notice(Translator.translateAndFormat("clearinventory success other", target.getName()));
            }
        }
        else {
            if (!(caller instanceof Player)) {
                caller.notice(Translator.translate("clearinventory console"));
            }
            else {
                Player player = (Player) caller;
                player.getInventory().clearContents();
                caller.notice(Translator.translate("clearinventory success"));
            }
        }
    }
}
