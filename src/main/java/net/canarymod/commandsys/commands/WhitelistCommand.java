package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to add or remove a player from the whitelist  
 *
 * @author Chris (damagefilter)
 */
public class WhitelistCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] args) {
        if (args[1].equalsIgnoreCase("add")) {
            Canary.whitelist().addPlayer(args[2]);
            caller.message(Colors.YELLOW + Translator.translate("whitelist player added"));
        }
        if (args[1].equalsIgnoreCase("remove")) {
            Canary.whitelist().removePlayer(args[2]);
            caller.message(Colors.YELLOW + Translator.translate("whitelist player removed"));
        }
    }
}
