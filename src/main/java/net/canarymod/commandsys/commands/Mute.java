package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandException;

public class Mute {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            console((Server) caller, parameters);
        } else if (caller instanceof Player) {
            player((Player) caller, parameters);
        } else {
            throw new CommandException("Unknown MessageReceiver: " + caller.getClass().getSimpleName());
        }
    }

    private void console(Server caller, String[] args) {
        Player target = caller.matchPlayer(args[1]);

        if (target != null) {
            if (target.isMuted()) {
                target.setMuted(false);
                caller.notice(Translator.translateAndFormat("mute unmuted", target.getName()));
            } else {
                target.setMuted(true);
                caller.notice(Translator.translateAndFormat("mute muted", target.getName()));
            }
        } else {
            caller.notice(Translator.translateAndFormat("unknown player", args[1]));
        }
    }

    private void player(Player player, String[] args) {
        Player target = Canary.getServer().matchPlayer(args[1]);

        if (target != null) {
            if (target.isMuted()) {
                target.setMuted(false);
                player.notice(Translator.translateAndFormat("mute unmuted", target.getName()));
            } else {
                target.setMuted(true);
                player.notice(Translator.translateAndFormat("mute muted", target.getName()));
            }
        } else {
            player.notice(Translator.translateAndFormat("unknown player", args[1]));
        }
    }

}
