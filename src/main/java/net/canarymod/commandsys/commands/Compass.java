package net.canarymod.commandsys.commands;


import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;


public class Compass extends CanaryCommand {

    public Compass() {
        super("canary.command.player.compass", Translator.translate("compass info"), "", 1, 1);
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
        caller.notice( Translator.translate("compass console"));
    }

    private void player(Player player) {
        double degrees = (player.getRotation() - 180) % 360;

        if (degrees < 0) {
            degrees += 360.0;
        }

        player.notice( Translator.translate("compass") + Translator.translate(player.getCardinalDirection().toString()) + " (" + (Math.round(degrees * 10) / 10.0) + ")");
    }

}
