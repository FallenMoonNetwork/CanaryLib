package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.api.entity.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;

public class PrivateMessage extends CanaryCommand {

    public PrivateMessage() {
        super("canary.command.tell", "Send a private message to other players", "Usage: /msg <playername> <message>", 3);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        Player target = Canary.getServer().matchPlayer(parameters[1]);
        if(target != null) {
            target.sendMessage(Colors.LIGHT_GRAY+"(MSG)"+Colors.BLUE+caller.getName()+Colors.WHITE+": "+Canary.glueString(parameters, 2, " "));
        }
        else {
            caller.notice(parameters[1]+" not found!");
        }
    }
}
