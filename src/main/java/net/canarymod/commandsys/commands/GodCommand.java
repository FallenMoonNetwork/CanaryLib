package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/** @author Somners */
public class GodCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Player) {
            player((Player) caller, parameters);
        }
        else {
            console(caller, parameters);
        }
    }

    private void console(MessageReceiver caller, String[] args) {
        if (args.length != 2) {
            Canary.help().getHelp(caller, "god");
            return;
        }
        if (!caller.hasPermission("canary.command.god.other")) {
            caller.notice(Translator.translate("god failed"));
            return;
        }
        Player other = Canary.getServer().getPlayer(args[1]);
        if (other == null) {
            caller.notice(Translator.translate("god failed") + " " + Translator.translateAndFormat("unknown player", args[1]));
            return;
        }
        if (other.getCapabilities().isInvulnerable()) {
            other.getCapabilities().setInvulnerable(false);
            caller.notice(Translator.translateAndFormat("god disabled other", other.getName()));
            other.notice(Translator.translate("god disabled"));
        }
        else {
            other.getCapabilities().setInvulnerable(true);
            caller.notice(Translator.translateAndFormat("god enabled other", other.getName()));
            other.notice(Translator.translate("god enabled"));
        }

    }

    private void player(Player player, String[] args) {
        // Give to player
        if (args.length == 1) {
            if (!player.hasPermission("canary.command.god")) {
                player.notice(Translator.translate("god failed"));
                return;
            }
            if (player.getCapabilities().isInvulnerable()) {
                player.getCapabilities().setInvulnerable(false);
                player.notice(Translator.translate("god disabled"));
            }
            else {
                player.getCapabilities().setInvulnerable(true);
                player.notice(Translator.translate("god enabled"));
            }
        }
        // Give to other
        else if (args.length == 2) {
            if (!player.hasPermission("canary.command.god.other")) {
                player.notice(Translator.translate("god failed"));
                return;
            }
            Player other = Canary.getServer().matchPlayer(args[1]);
            if (other == null) {
                player.notice(Translator.translate("god failed") + " " + Translator.translateAndFormat("unknown player", args[1]));
                return;
            }
            if (other.getCapabilities().isInvulnerable()) {
                other.getCapabilities().setInvulnerable(false);
                player.notice(Translator.translateAndFormat("god disabled other", other.getName()));
                other.notice(Translator.translate("god disabled"));
            }
            else {
                other.getCapabilities().setInvulnerable(true);
                player.notice(Translator.translateAndFormat("god enabled other", other.getName()));
                other.notice(Translator.translate("god enabled"));
            }
        }
        else {
            player.notice(Translator.translate("god failed") + " " + Translator.translate("usage"));
            Canary.help().getHelp(player, "god");
        }
    }
}
