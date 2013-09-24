package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to show help for the playermod command   
 *
 * @author Chris (damagefilter)
 */
public class PlayermodBase implements NativeCommand {
    public void execute(MessageReceiver caller, String[] parameters) {
        if (parameters.length == 1) {
            Canary.help().getHelp(caller, parameters[0].replace("/", ""));
        }
        if (parameters.length == 2 && parameters[1].equals("--help")) {
            Canary.help().getHelp(caller, parameters[0].replace("/", ""));
        }
    }
}
