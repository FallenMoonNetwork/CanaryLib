/*
 * This file is copyright of WarHead Gaming. It is open Source and
 * free to use. Use of our code without express consent from the
 * developers is in violation of our license which has been provided
 * for you in the source and compiled jar.
 */
package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandException;

/**
 *
 * @author Somners
 */
public class GodCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            console(caller, parameters);
        } else if (caller instanceof Player) {
            player((Player) caller, parameters);
        } else {
            throw new CommandException("Unknown MessageReceiver: " + caller.getClass().getSimpleName());
        }
    }

    private void console(MessageReceiver caller, String[] args) {
        if(args.length != 2) {
            Canary.help().getHelp(caller, "give");
            return;
        }
        Player other = Canary.getServer().getPlayer(args[1]);
            if (other == null) {
                caller.notice(Translator.translate("god failed") + " " + Translator.translateAndFormat("unknown player", args[1]));
                return;
            }
            if (other.isDamageDisabled()) {
                other.setDamageDisabled(false);
                caller.notice(Translator.translateAndFormat("god disabled other", other.getName()));
                other.notice(Translator.translate("god disabled"));
            } else {
                other.setDamageDisabled(true);
                caller.notice(Translator.translateAndFormat("god enabled other", other.getName()));
                other.notice(Translator.translate("god enabled"));
            }

    }

    private void player(Player player, String[] args) {
        //Give to player
        if(args.length == 1) {
            if(!player.hasPermission("canary.command.god")) {
                player.notice(Translator.translate("god failed"));
                return;
            }
            if (player.isDamageDisabled()) {
                player.setDamageDisabled(false);
                player.notice(Translator.translate("god disabled"));
            } else {
                player.setDamageDisabled(true);
                player.notice(Translator.translate("god enabled"));
            }
        }
        //Give to other
        else if(args.length == 2) {
            if(!player.hasPermission("canary.command.god.other")) {
                player.notice(Translator.translate("god failed"));
                return;
            }
            Player other = Canary.getServer().getPlayer(args[1]);
            if (other == null) {
                player.notice(Translator.translate("god failed") + " " + Translator.translateAndFormat("unknown player", args[1]));
                return;
            }
            if (other.isDamageDisabled()) {
                other.setDamageDisabled(false);
                player.notice(Translator.translateAndFormat("god disabled other", other.getName()));
                other.notice(Translator.translate("god disabled"));
            } else {
                other.setDamageDisabled(true);
                player.notice(Translator.translateAndFormat("god enabled other", other.getName()));
                other.notice(Translator.translate("god enabled"));
            }
        } else {
            player.notice(Translator.translate("god failed") + " " + Translator.translate("usage"));
            Canary.help().getHelp(player, "god");
        }
    }
}
