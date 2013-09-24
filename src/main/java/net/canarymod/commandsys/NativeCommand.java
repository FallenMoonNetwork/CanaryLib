package net.canarymod.commandsys;

import net.canarymod.chat.MessageReceiver;

/*
 * Marks a command as a Canary native one
 *
 * @author Jason (darkdiplomat) 
 */
public interface NativeCommand {

    void execute(MessageReceiver caller, String[] parameters);

}
