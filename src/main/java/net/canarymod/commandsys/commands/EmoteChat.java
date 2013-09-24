package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.visualillusionsent.utils.StringUtils;

/**
 * Command to send an emote message to chat
 *
 * @author Chris (damagefilter)
 */
public class EmoteChat implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Player) {
            player((Player) caller, StringUtils.joinString(parameters, " ", 1));
        }
        else if (caller instanceof Server) {
            console((Server) caller, StringUtils.joinString(parameters, " ", 1));
        }
        else {
            others(caller, StringUtils.joinString(parameters, " ", 1));
        }
    }

    private void player(Player player, String message) {
        if (player.isMuted()) {
            player.notice(Translator.translate("muted"));
        }
        else {
            Canary.getServer().broadcastMessage(player.getPrefix() + "* " + player.getName() + " " + Colors.WHITE + message);
        }
    }

    private void console(Server server, String message) {
        Canary.getServer().broadcastMessage(Colors.BLUE + "* " + server.getName() + " " + Colors.WHITE + message);
    }

    private void others(MessageReceiver r, String message) {
        Canary.getServer().broadcastMessage(Colors.LIGHT_GREEN + "** " + Colors.WHITE + message);
    }

}
