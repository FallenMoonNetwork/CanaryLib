package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandException;
import net.canarymod.commandsys.NativeCommand;

public class SetHome implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            console(caller);
        }
        else if (caller instanceof Player) {
            player((Player) caller, parameters);
        }
        else {
            throw new CommandException("Unknown MessageReceiver: " + caller.getClass().getSimpleName());
        }
    }

    private void console(MessageReceiver caller) {
        caller.notice("Your home has been set to everywhere.");
    }

    private void player(Player player, String[] args) {
        if (args.length == 2) {
            Player target = Canary.getServer().matchPlayer(args[1]);
            if (target != null) {
                target.setHome(player.getLocation());
                target.message(Colors.YELLOW + "Your home has been set by " + player.getName());
                player.message(Colors.YELLOW + target.getName() + "'s  home has been set.");
            }
            else {
                player.notice(Translator.translateAndFormat("unknown player", args[1]));
            }
        }
        else {
            player.setHome(player.getLocation());
            player.message(Colors.YELLOW + "Your home has been set.");
        }
    }

}
