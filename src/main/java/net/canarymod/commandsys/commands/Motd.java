package net.canarymod.commandsys.commands;


import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;
import net.canarymod.config.Configuration;


public class Motd extends CanaryCommand {

    public Motd() {
        super("canary.command.motd", Translator.translate("motd info"), Translator.translateAndFormat("usage", "/motd"), 1);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        if (caller == null) {
            throw new CommandException(Translator.translateAndFormat("unknown messagereceiver", "null"));
        }
        caller.message(Configuration.getServerConfig().getMotd());
    }

}
