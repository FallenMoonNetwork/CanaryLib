package net.canarymod.commandsys.commands;

import java.util.ArrayList;

import net.canarymod.Canary;
import net.canarymod.api.Server;
import net.canarymod.api.entity.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.chat.TextFormat;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;

public class PlayerList extends CanaryCommand {

    public PlayerList() {
        super("canary.command.playerlist", "Display a list of online players", "Usage: /playerlist", 1);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        if(caller instanceof Server) {
            console((Server)caller);
        }
        else if(caller instanceof Player) {
            player((Player)caller);
        }
        else {
            throw new CommandException("Unknown MessageReceiver: "+caller.getClass().getSimpleName());
        }
    }
    
    private void console(Server caller) {
        caller.notice("**** PLAYERS ****");
        caller.notice(TextFormat.removeFormatting(createList()));
    }
    
    private void player(Player player) {
       player.sendMessage(createList());
    }
    
    private String createList() {
        ArrayList<Player> players = Canary.getServer().getPlayerList();
        StringBuilder sb = new StringBuilder();
        for(Player p : players) {
            sb.append(p.getColor())
            .append(p.getName())
            .append(Colors.WHITE)
            .append(", ");
        }
        if(sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
    

}
