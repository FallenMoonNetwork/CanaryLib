package net.canarymod.commandsys;

import net.canarymod.chat.MessageReceiver;

public interface NativeCommand {

    void execute(MessageReceiver caller, String[] parameters);

}
