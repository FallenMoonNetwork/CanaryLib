package net.canarymod.commandsys.commands;

import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.chat.TextFormat;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to get your own position (x, y, z and rotation)
 *
 * @author Chris (damagefilter)
 */
public class GetPosition implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Player) {
            player((Player) caller);
        }
        else {
            console(caller);
        }
    }

    private void console(MessageReceiver caller) {
        caller.notice(Translator.translate("getpos console") + "(" + TextFormat.RANDOM + "Altitude: 30000km " + TextFormat.RESET + ")");
    }

    private void player(Player player) {
        player.message(Colors.ORANGE + " X: " + Colors.LIGHT_GRAY + player.getX());
        player.message(Colors.ORANGE + " Y: " + Colors.LIGHT_GRAY + player.getY());
        player.message(Colors.ORANGE + " Z: " + Colors.LIGHT_GRAY + player.getZ());
        player.message(Colors.ORANGE + "Rotation: " + Colors.LIGHT_GRAY + player.getRotation() + Colors.ORANGE + " Pitch: " + Colors.LIGHT_GRAY + player.getPitch());

        double degrees = ((player.getRotation() - 90) % 360);

        if (degrees < 0) {
            degrees += 360.0;
        }

        player.notice(Translator.translate("compass") + " " + Translator.translate(player.getCardinalDirection().toString()) + " (" + (Math.round(degrees * 10) / 10.0) + ")");
    }

}
