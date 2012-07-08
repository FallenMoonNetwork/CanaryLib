package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Logman;
import net.canarymod.MessageReceiver;
import net.canarymod.api.Server;
import net.canarymod.api.entity.Player;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;

public class HelpCommand extends CanaryCommand {

    public HelpCommand() {
        super("canary.command.help", "Get some help!", "Usage: /help [page] [search terms]", 1);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        if(caller instanceof Server) {
            console(caller, parameters);
        }
        else if(caller instanceof Player) {
            player((Player)caller, parameters);
        }
        else {
            throw new CommandException("Unknown MessageReceiver: "+caller.getClass().getSimpleName());
        }
    }
    
    //TODO: Implement search terms
    
    private void console(MessageReceiver caller, String[] args) {
        int page = 0;
        if(args.length > 1) {
            page = Integer.valueOf(args[1])-1;
        }

        String[] lines = Canary.help().getHelp(null, page); 

        if(lines == null) {
            Logman.logInfo("Help-page not found");
        }
        // Send all lines
        for(String l : lines) {
            Logman.logInfo(l);
        }
    }
    
    private void player(Player player, String[] args) {

        int page = 0;
        if(args.length > 1) {
            page = Integer.valueOf(args[1])-1;
        }

        String[] lines = Canary.help().getHelp(player,page); 

        if(lines == null) {
            player.notify("Help-page not found");
        }
        
        //Send all the fancy pre-formatted lines
        for(String l : lines) {
            player.sendMessage(l);
        }
    }

}
