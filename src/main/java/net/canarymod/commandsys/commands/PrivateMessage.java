package net.canarymod.commandsys.commands;


import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.visualillusionsent.utils.StringUtils;


public class PrivateMessage {

    public void execute(MessageReceiver caller, String[] parameters) {
        Player target = Canary.getServer().matchPlayer(parameters[1]);
        String prefix = Colors.BLUE;
        if(caller instanceof Player) {
            prefix = ((Player)caller).getPrefix();
        }
        if (target != null) {
            target.sendMessage(Colors.LIGHT_GRAY + "-> " + target.getPrefix() + target.getName() + Colors.WHITE + ": " + StringUtils.joinString(parameters, " ", 2));
            target.sendMessage(Colors.LIGHT_GRAY + "(MSG) " + prefix + caller.getName() + Colors.WHITE + ": " + StringUtils.joinString(parameters, " ", 2));
        } else {
            caller.notice(Translator.translateAndFormat("unknown player", parameters[1]));
        }
    }
}
