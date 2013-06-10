package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandException;

public class EmoteChat {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Player) {
            player((Player) caller, Canary.glueString(parameters, 1, " "));
        } else if (caller instanceof Server) {
            console((Server) caller, Canary.glueString(parameters, 1, " "));
        } else {
            throw new CommandException(Translator.translateAndFormat("unknown messagereceiver", caller.getClass().getSimpleName()));
        }
    }

    private void player(Player player, String message) {
        if (player.isMuted()) {
            player.notice(Translator.translate("muted"));
        } else {
            Canary.getServer().broadcastMessage(player.getPrefix() + "* " + player.getName() + " " + Colors.WHITE + message);
        }
    }

    private void console(Server server, String message) {
        Canary.getServer().broadcastMessage(Colors.BLUE + "* " + server.getName() + " " + Colors.WHITE + message);
    }

}
