package net.canarymod.commandsys.commands;


import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;


public class WhitelistCommand extends CanaryCommand {

    public WhitelistCommand() {
        super("canary.command.whitelist", Translator.translate("whitelist help"), Translator.translateAndFormat("usage", "/whitelist <add|remove> <playername>"), 3);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] args) {
        if(args[1].equalsIgnoreCase("add")) {
            Canary.whitelist().addPlayer(args[2]);
            caller.message(Colors.YELLOW + Translator.translate("whitelist player added"));
        }
        if(args[1].equalsIgnoreCase("remove")) {
            Canary.whitelist().removePlayer(args[2]);
            caller.message(Colors.YELLOW + Translator.translate("whitelist player removed"));
        }
    }
}
