package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.MessageReceiver;
import net.canarymod.api.Server;
import net.canarymod.api.entity.Player;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;

public class EmoteChat extends CanaryCommand {
    
    public EmoteChat() {
        super("canary.command.emote", "Show an emotion in chat (* player facepalms)", "Usage: /me <message>", 2);
    }
    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        if(caller instanceof Player) {
            player((Player)caller, Canary.glueString(parameters, 1, " "));
        }
        else if(caller instanceof Server){
            console((Server)caller, Canary.glueString(parameters, 1, " "));
        }
        else {
            throw new CommandException("Unknown MessageReceiver: "+caller.getClass().getSimpleName());
        }
    }
    
    private void player(Player player, String message) {
        
    }
    
    private void console(Server server, String message) {
        
    }

}
