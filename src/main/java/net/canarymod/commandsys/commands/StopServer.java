package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandException;
import net.canarymod.commandsys.NativeCommand;

public class StopServer implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            caller.notice(Translator.translateAndFormat("stop console", caller.getName()));
            ((Server) caller).initiateShutdown();
        }
        else if (caller instanceof Player) {
            Canary.getServer().notice(Translator.translateAndFormat("stop console", caller.getName()));
            Canary.getServer().initiateShutdown();
        }
        else {
            throw new CommandException("Unknown MessageReceiver: " + caller.getClass().getSimpleName());
        }
    }
}
