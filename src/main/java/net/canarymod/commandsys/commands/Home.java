package net.canarymod.commandsys.commands;


import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandException;
import net.canarymod.warp.Warp;


public class Home {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            console(caller);
        } else if (caller instanceof Player) {
            player((Player) caller, parameters);
        } else {
            throw new CommandException(Translator.translateAndFormat("unknown messagereceiver", caller.getClass().getSimpleName()));
        }
    }

    private void console(MessageReceiver caller) {
        caller.notice(Translator.translate("home console"));
    }

    private void player(Player player, String[] args) {
        if (args.length == 1) {
            if (player.hasHome()) {
                player.notice(Translator.translate("home teleport"));
                player.teleportTo(player.getHome());
            } else {
                player.notice(Translator.translate("no home set"));
            }
        } else {
            if (player.hasPermission("canary.command.teleport.home.other")) {
                Player target = Canary.getServer().matchPlayer(args[1]);

                if (target != null) {
                    if (target.hasHome()) {
                        player.notice(Translator.translateAndFormat("home teleport other", target.getName()));
                        player.teleportTo(target.getHome());
                    } else {
                        player.notice(Translator.translateAndFormat("no home set other", target.getName()));
                    }
                } else {
                    Warp home = Canary.warps().getHome(args[1]);
                    if(home != null) {
                        player.notice(Translator.translateAndFormat("home teleport other", args[1]));
                        player.teleportTo(home.getLocation());
                    }
                    else {
                        player.notice(Translator.translateAndFormat("no home set other", args[1]));
                    }
                }
            }
        }
    }

}
