package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.hook.player.KickHook;
import net.visualillusionsent.utils.StringUtils;

/**
 * Command to kick a player from the server
 *
 * @author Brian (WWOL)
 */
public class Kick implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        Player target = Canary.getServer().matchPlayer(parameters[1]);
        if (target != null) {
            String reason = Translator.translateAndFormat("kick message", caller.getName());
            if (parameters.length > 2) {
                reason = StringUtils.joinString(parameters, " ", 2);
            }
            new KickHook(target, caller, reason).call(); // Call KickHook here to pass the caller that is actually doing the kicking
            target.kickNoHook(reason); // Don't call the hook again
            caller.notice(Translator.translateAndFormat("kick kicked", target.getName()));
        }
        else {
            caller.notice(Translator.translate("kick failed") + " " + Translator.translateAndFormat("unknown player", parameters[1]));
        }
    }

}
