package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to kill yourself or another player 
 *
 * @author Chris (damagefilter)
 */
public class Kill implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Player) {
            player((Player) caller, parameters);
        }
        else {
            console(caller, parameters);
        }
    }

    private void console(MessageReceiver caller, String[] args) {
        if (args.length == 1) {
            caller.notice(Translator.translate("kill console"));
        }
        else {
            Player target = Canary.getServer().matchPlayer(args[1]);

            if (target != null) {
                target.kill();
                caller.notice(Translator.translateAndFormat("killed other", target.getName()));
            }
            else {
                caller.notice(Translator.translate("not killed") + " " + Translator.translateAndFormat("unknown player", args[1]));
            }
        }
    }

    private void player(Player player, String[] args) {
        if (args.length == 1) {
            player.notice(Translator.translate("player suicide"));
            player.kill();
        }
        else {
            if (player.hasPermission("canary.command.player.kill.other")) {
                Player target = Canary.getServer().matchPlayer(args[1]);

                if (target != null) {
                    target.kill();
                    player.notice(Translator.translateAndFormat("killed other", target.getName()));
                }
                else {
                    player.notice(Translator.translate("not killed") + " " + Translator.translateAndFormat("unknown player", args[1]));
                }
            }
        }
    }

}
