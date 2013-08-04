package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandException;
import net.canarymod.commandsys.NativeCommand;

public class Kill implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            console((Server) caller, parameters);
        } else if (caller instanceof Player) {
            player((Player) caller, parameters);
        } else {
            throw new CommandException(Translator.translateAndFormat("unknown messagereceiver", caller.getClass().getSimpleName()));
        }
    }

    private void console(Server caller, String[] args) {
        if (args.length == 1) {
            caller.notice(Translator.translate("kill console"));
        } else {
            Player target = caller.matchPlayer(args[1]);

            if (target != null) {
                target.kill();
                caller.notice(Translator.translateAndFormat("killed other", target.getName()));
            } else {
                caller.notice(Translator.translate("not killed") + " " + Translator.translateAndFormat("unknown player", args[1]));
            }
        }
    }

    private void player(Player player, String[] args) {
        if (args.length == 1) {
            player.notice(Translator.translate("player suicide"));
            player.kill();
        } else {
            if (player.hasPermission("canary.command.player.kill.other")) {
                Player target = Canary.getServer().matchPlayer(args[1]);

                if (target != null) {
                    target.kill();
                    player.notice(Translator.translateAndFormat("killed other", target.getName()));
                } else {
                    player.notice(Translator.translate("not killed") + " " + Translator.translateAndFormat("unknown player", args[1]));
                }
            }
        }
    }

}
