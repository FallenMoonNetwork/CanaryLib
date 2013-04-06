package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.hook.player.KickHook;
import net.visualillusionsent.utils.StringUtils;

public class Kick extends CanaryCommand {

    public Kick() {
        super("canary.command.kick", Translator.translate("kick info"), Translator.translateAndFormat("usage", "/kick <playername> [reason]"), 2);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        if (caller.hasPermission("canary.command.kick")) {
            Player target = Canary.getServer().matchPlayer(parameters[1]);

            if (target != null) {
                String reason = parameters[2] != null ? StringUtils.joinString(parameters, " ", 2) : Translator.translateAndFormat("kick message", caller.getName());
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
