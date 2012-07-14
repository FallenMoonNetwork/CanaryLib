package net.canarymod.commandsys.commands;

import net.canarymod.MessageReceiver;
import net.canarymod.api.Server;
import net.canarymod.api.entity.Player;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;

public class RestartServer extends CanaryCommand {

    public RestartServer() {
        super("*", "Restart the underlying server. The -all parameter will also reload CanaryMod", "Usage: /restart [-all]", 1, 2);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        if(caller instanceof Server) {
            caller.notify(caller.getName()+" issued a manual restart!");
            if(parameters.length == 2 && parameters[1].equalsIgnoreCase("-all")) {
                ((Server)caller).restart(true);
            }
            else {
                ((Server)caller).restart(false);
            }
        }
        else if(caller instanceof Player) {
            caller.notify("You cannot restart the server from in-game. Please use the console!");
        }
        else {
            throw new CommandException("Unknown MessageReceiver: "+caller.getClass().getSimpleName());
        }
    }
    
//    private void console(MessageReceiver caller) {
//        caller.notify("Looking down from the great Minecraft Skies!");
//    }
    
//    private void player(Player player) {
//        double degrees =  (player.getRotation() - 180) % 360;
//        if (degrees < 0) {
//            degrees += 360.0;
//        }
//        
//        player.notify("Compass: " + player.getCardinalDirection().toString() + " (" + (Math.round(degrees * 10) / 10.0) + ")");
//    }

}
