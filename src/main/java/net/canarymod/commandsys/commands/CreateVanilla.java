package net.canarymod.commandsys.commands;

import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.config.Configuration;
import net.canarymod.converter.CanaryToVanilla;

public class CreateVanilla {

    public void execute(MessageReceiver caller, String[] parameters) {
        CanaryToVanilla converter = new CanaryToVanilla();
        String world = Configuration.getServerConfig().getDefaultWorldName();
        world = world + "_NORMAL";

        if (parameters.length > 1) {
            world = parameters[1];
        }

        if (converter.convert(world) == false) {
            caller.notice("Failed to convert to vanilla.");
        } else {
            caller.message(Colors.YELLOW + "Succeed to convert to vanilla; result is in the vanilla/ folder.");
        }
    }
}
