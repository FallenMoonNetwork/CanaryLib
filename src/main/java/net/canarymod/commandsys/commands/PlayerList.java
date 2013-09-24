package net.canarymod.commandsys.commands;

import java.util.ArrayList;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.chat.TextFormat;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to list the players currently connected to the server  
 *
 * @author Chris (damagefilter)
 */
public class PlayerList implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Player) {
            player((Player) caller);
        }
        else {
            console(caller);
        }
    }

    private void console(MessageReceiver caller) {
        caller.notice("**** PLAYERS ****");
        caller.notice(TextFormat.removeFormatting(createList()));
    }

    private void player(Player player) {
        player.message(createList());
    }

    private String createList() {
        ArrayList<Player> players = Canary.getServer().getPlayerList();
        StringBuilder sb = new StringBuilder();

        for (Player p : players) {
            sb.append(p.getPrefix()).append(p.getName()).append(Colors.WHITE).append(", ");
        }
        if (sb.length() > 0) {
            sb.delete(sb.length() - 2, sb.length() - 1);
        }
        return sb.toString();
    }

}
