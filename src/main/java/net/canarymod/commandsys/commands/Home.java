package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.warp.Warp;

/**
 * Command to teleport you to your own or another player home 
 *
 * @author Chris (damagefilter)
 */
public class Home implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            console(caller);
        }
        else if (caller instanceof Player) {
            player((Player) caller, parameters);
        }
        else {
            others(caller, parameters);
        }
    }

    private void console(MessageReceiver caller) {
        caller.notice(Translator.translate("home console"));
    }

    //Special behaviour for command blocks, they teleport the given player to their homes
    private void others(MessageReceiver player, String[] args) {
        if (player.hasPermission("canary.command.teleport.home.other")) {
            Player target = Canary.getServer().matchPlayer(args[1]);

            if (target != null) {
                if (target.hasHome()) {
                    target.teleportTo(target.getHome());
                }
                else {
                    player.notice(Translator.translateAndFormat("no home set other", target.getName()));
                }
            }
        }
    }

    private void player(Player player, String[] args) {
        if (args.length == 1) {
            if (player.hasHome()) {
                player.notice(Translator.translate("home teleport"));
                player.teleportTo(player.getHome());
            }
            else {
                player.notice(Translator.translate("no home set"));
            }
        }
        else {
            if (player.hasPermission("canary.command.teleport.home.other")) {
                Player target = Canary.getServer().matchPlayer(args[1]);

                if (target != null) {
                    if (target.hasHome()) {
                        player.notice(Translator.translateAndFormat("home teleport other", target.getName()));
                        player.teleportTo(target.getHome());
                    }
                    else {
                        player.notice(Translator.translateAndFormat("no home set other", target.getName()));
                    }
                }
                else {
                    Warp home = Canary.warps().getHome(args[1]);
                    if (home != null) {
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
