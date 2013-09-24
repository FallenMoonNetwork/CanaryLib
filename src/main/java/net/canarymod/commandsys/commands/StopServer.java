package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to stop Canary  
 *
 * @author Chris (damagefilter)
 */
public class StopServer implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        Canary.getServer().notice(Translator.translateAndFormat("stop console", caller.getName()));
        Canary.getServer().initiateShutdown();
    }
}
