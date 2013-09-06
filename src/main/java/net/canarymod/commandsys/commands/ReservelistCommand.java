package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Reserve List Command!
 *
 * @author Jason (darkdiplomat)
 */
public class ReservelistCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] args) {
        if (args[1].equalsIgnoreCase("add")) {
            Canary.reservelist().addPlayer(args[2]);
            caller.message(Colors.YELLOW + Translator.translate("reservelist player added"));
        }
        if (args[1].equalsIgnoreCase("remove")) {
            Canary.reservelist().removePlayer(args[2]);
            caller.message(Colors.YELLOW + Translator.translate("reservelist player removed"));
        }
    }
}
