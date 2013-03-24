package net.canarymod.commandsys.commands;


import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.chat.TextFormat;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;


public class GetPosition extends CanaryCommand {

    public GetPosition() {
        super("canary.command.getpos", "Get your current position in the world", "", 1);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            console(caller);
        } else if (caller instanceof Player) {
            player((Player) caller);
        } else {
            throw new CommandException("Unknown MessageReceiver: " + caller.getClass().getSimpleName());
        }
    }
    
    private void console(MessageReceiver caller) {
        caller.notice("You are in the great Minecraft Skies! (" + TextFormat.RANDOM + "Altitude: 30000km " + TextFormat.RESET + ")");
    }
    
    private void player(Player player) {
        player.sendMessage(Colors.ORANGE + "Pos X: " + Colors.LIGHT_GRAY + player.getX()); 
        player.sendMessage(Colors.ORANGE + " Y: " + Colors.LIGHT_GRAY + player.getY());
        player.sendMessage(Colors.ORANGE + " Z: " + Colors.LIGHT_GRAY + player.getZ());
        player.sendMessage(Colors.ORANGE + "Rotation: " + Colors.LIGHT_GRAY + player.getRotation() + Colors.ORANGE + " Pitch: " + Colors.LIGHT_GRAY + player.getPitch());

        double degrees = ((player.getRotation() - 90) % 360);

        if (degrees < 0) {
            degrees += 360.0;
        }
        
        player.notice("Compass: " + player.getCardinalDirection().toString() + " (" + (Math.round(degrees * 10) / 10.0) + ")");
    }

}
