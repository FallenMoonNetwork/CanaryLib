package net.canarymod.commandsys.commands;

import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.config.Configuration;
import net.canarymod.converter.CanaryToVanilla;

/**
 * Command to convert a Canary server setup into a Vanilla setup
 *
 * @author Chris (damagefilter)
 */
public class CreateVanilla implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        CanaryToVanilla converter = new CanaryToVanilla();
        String world = Configuration.getServerConfig().getDefaultWorldName();
        world = world + "_NORMAL";

        if (parameters.length > 1) {
            world = parameters[1];
        }

        if (!converter.convert(world)) {
            caller.notice("Failed to convert to vanilla.");
        }
        else {
            caller.message(Colors.YELLOW + "Succeed to convert to vanilla; result is in the vanilla/ folder.");
        }
    }
}
