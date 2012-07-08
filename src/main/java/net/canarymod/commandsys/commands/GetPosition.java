package net.canarymod.commandsys.commands;

import net.canarymod.Colors;
import net.canarymod.MessageReceiver;
import net.canarymod.TextFormat;
import net.canarymod.api.Server;
import net.canarymod.api.entity.Player;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;

public class GetPosition extends CanaryCommand {

    public GetPosition() {
        super("canary.command.getpos", "Get your current position in the world", "", 1);
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
        caller.notify("You are in the great Minecraft Skies! (" + TextFormat.Random + "Altitude: 30000km " + TextFormat.Reset + ")" );
    }
    
    private void player(Player player) {
        player.sendMessage(Colors.Gold+"Pos X: "+Colors.LightGray+player.getX()); 
        player.sendMessage(Colors.Gold+" Y: "+Colors.LightGray+ player.getY());
        player.sendMessage(Colors.Gold+" Z: "+Colors.LightGray+player.getZ());
        player.sendMessage(Colors.Gold+"Rotation: "+Colors.LightGray+player.getRotation()+Colors.Gold+" Pitch: "+Colors.LightGray+player.getPitch());

        double degrees =  ((player.getRotation() - 90) % 360);
        if (degrees < 0) {
            degrees += 360.0;
        }
        
        player.notify("Compass: " + player.getCardinalDirection().toString() + " (" + (Math.round(degrees * 10) / 10.0) + ")");
    }

}
