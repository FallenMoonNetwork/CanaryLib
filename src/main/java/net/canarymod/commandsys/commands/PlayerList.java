package net.canarymod.commandsys.commands;

import java.util.ArrayList;
import net.canarymod.Canary;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.chat.TextFormat;
import net.canarymod.commandsys.CommandException;
import net.canarymod.commandsys.NativeCommand;

public class PlayerList implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            console((Server) caller);
        } else if (caller instanceof Player) {
            player((Player) caller);
        } else {
            throw new CommandException("Unknown MessageReceiver: " + caller.getClass().getSimpleName());
        }
    }

    private void console(Server caller) {
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
