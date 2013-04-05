package net.canarymod.commandsys.commands;


import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.visualillusionsent.utils.StringUtils;


public class PrivateMessage extends CanaryCommand {

    public PrivateMessage() {
        super("canary.command.tell", Translator.translate("msg info"), Translator.translateAndFormat("usage", "/msg <playername> <message>"), 3);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        Player target = Canary.getServer().matchPlayer(parameters[1]);

        if (target != null) {
            target.sendMessage(Colors.LIGHT_GRAY + "(MSG)" + Colors.BLUE + caller.getName() + Colors.WHITE + ": " + StringUtils.joinString(parameters, " ", 2));
        } else {
            caller.notice(Translator.translateAndFormat("unknown player", parameters[1]));
        }
    }
}
