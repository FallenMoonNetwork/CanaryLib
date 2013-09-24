package net.canarymod.commandsys.commands;

import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.blocks.CommandBlock;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandException;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to get your own rotation (cardinal direction and degrees yaw)
 *
 * @author Chris (damagefilter)
 */
public class Compass implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if ((caller instanceof Server) || (caller instanceof CommandBlock)) {
            console(caller);
        }
        else if (caller instanceof Player) {
            player((Player) caller);
        }
        else {
            throw new CommandException("Unknown MessageReceiver: " + caller.getClass().getSimpleName());
        }
    }

    private void console(MessageReceiver caller) {
        caller.notice(Translator.translate("compass console"));
    }

    private void player(Player player) {
        double degrees = (player.getRotation() - 180) % 360;

        if (degrees < 0) {
            degrees += 360.0;
        }

        player.notice(Translator.translate("compass") + " " + Translator.translate(player.getCardinalDirection().toString()) + " (" + (Math.round(degrees * 10) / 10.0) + ")");
    }

}
