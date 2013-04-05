package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;
import net.canarymod.hook.player.KickHook;
import net.visualillusionsent.utils.StringUtils;

public class Kick extends CanaryCommand {

    public Kick() {
        super("canary.command.kick", Translator.translate("kick info"), Translator.translateAndFormat("usage", "/kick <playername> [reason]"), 2);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            console((Server) caller, parameters);
        } else if (caller instanceof Player) {
            player((Player) caller, parameters);
        } else {
            throw new CommandException(Translator.translateAndFormat("unknown messagereceiver", caller.getClass().getSimpleName()));
        }
    }

    private void console(Server caller, String[] args) {
        Player target = caller.matchPlayer(args[1]);

        if (target != null) {
            String reason = args[2] != null ? StringUtils.joinString(args, " ", 2) : Translator.translateAndFormat("kick message", caller.getName());
            target.kick(reason);
            KickHook hook = new KickHook(target, null, reason);
            Canary.hooks().callHook(hook);
            caller.notice(Translator.translateAndFormat("kick kicked", target.getName()));
        } else {
            caller.notice(Translator.translate("kick failed") + " " + Translator.translateAndFormat("unknown player", args[1]));
        }
    }

    private void player(Player player, String[] args) {
        if (player.hasPermission("canary.command.kick")) {
            Player target = Canary.getServer().matchPlayer(args[1]);

            if (target != null) {
                String reason = args[2] != null ? StringUtils.joinString(args, " ", 2) : Translator.translateAndFormat("kick message", player.getName());
                target.kick(reason);
                KickHook hook = new KickHook(target, player, reason);
                Canary.hooks().callHook(hook);
                player.notice(Translator.translateAndFormat("kick kicked", target.getName()));
            } else {
                player.notice(Translator.translate("kick failed") + " " + Translator.translateAndFormat("unknown player", args[1]));
            }
        }
    }

}
