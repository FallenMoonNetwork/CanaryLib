package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.hook.player.KickHook;
import net.visualillusionsent.utils.StringUtils;

public class Kick {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller.hasPermission("canary.command.kick")) {
            Player target = Canary.getServer().matchPlayer(parameters[1]);
            if (target != null) {
                String reason = Translator.translateAndFormat("kick message", caller.getName());
                if (parameters.length > 2) {
                    reason = StringUtils.joinString(parameters, " ", 2);
                }
                target.kick(reason);
                KickHook hook = new KickHook(target, (caller instanceof Player ? (Player) caller : null), reason);
                Canary.hooks().callHook(hook);
                caller.notice(Translator.translateAndFormat("kick kicked", target.getName()));
            } else {
                caller.notice(Translator.translate("kick failed") + " " + Translator.translateAndFormat("unknown player", parameters[1]));
            }
        }
    }

}
