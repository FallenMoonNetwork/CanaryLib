package net.canarymod.commandsys.commands;

import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.config.Configuration;
import net.canarymod.converter.CanaryToVanilla;

public class CreateVanilla extends CanaryCommand {

    public CreateVanilla() {
        super("canary.command.createvanilla", "Create a vanilla setup from the current canary settings", "Usage: /createvanilla [defaultworld]", 2);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        CanaryToVanilla converter = new CanaryToVanilla();
        String world = Configuration.getServerConfig().getDefaultWorldName();
        if(parameters.length > 1) {
            world = parameters[1];
        }
        
        if(converter.convert(world) == false) {
            caller.notice("Failed to convert to vanilla.");
        }
        else {
            caller.message(Colors.YELLOW + "Succeed to convert to vanilla; result is in the vanilla/ folder.");
        }
    }
}
