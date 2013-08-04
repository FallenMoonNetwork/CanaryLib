package net.canarymod.commandsys.commands;

import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandException;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.config.Configuration;

public class Motd implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller == null) {
            throw new CommandException(Translator.translateAndFormat("unknown messagereceiver", "null"));
        }
        caller.message(Configuration.getServerConfig().getMotd());
    }

}
