package net.canarymod.commandsys.commands;

import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;

public class Compass extends CanaryCommand {

    public Compass() {
        super("canary.command.player.compass", "Displays the cardinal direction you're looking at", "", 1, 1);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        if(caller instanceof Server) {
            console(caller);
        }
        else if(caller instanceof Player) {
            player((Player)caller);
        }
        else {
            throw new CommandException("Unknown MessageReceiver: "+caller.getClass().getSimpleName());
        }
    }
    
    private void console(MessageReceiver caller) {
        caller.notice("Looking down from the great Minecraft Skies!");
    }
    
    private void player(Player player) {
        double degrees =  (player.getRotation() - 180) % 360;
        if (degrees < 0) {
            degrees += 360.0;
        }
        
        player.notice("Compass: " + player.getCardinalDirection().toString() + " (" + (Math.round(degrees * 10) / 10.0) + ")");
    }

}
