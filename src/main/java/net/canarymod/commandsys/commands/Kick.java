package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;

public class Kick extends CanaryCommand {

    public Kick() {
        super("canary.command.kick", Translator.translate("kick info"), Translator.translateAndFormat("usage", "/kick <playername>"), 2, 2);
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
            target.kick(args[2] != null ? args[2] : Translator.translateAndFormat("kicked", caller.getName()));
            caller.notice(Translator.translateAndFormat("you kicked", target.getName()));
        } else {
            caller.notice(Translator.translate("not kicked") + " " + Translator.translateAndFormat("unknown player", args[1]));
        }
    }

    private void player(Player player, String[] args) {
        if (player.hasPermission("canary.command.kick")) {
            Player target = Canary.getServer().matchPlayer(args[1]);

            if (target != null) {
                target.kick(args[2] != null ? args[2] : Translator.translateAndFormat("kicked", player.getName()));
                player.notice(Translator.translateAndFormat("you kicked", target.getName()));
            } else {
                player.notice(Translator.translate("not kicked") + " " + Translator.translateAndFormat("unknown player", args[1]));
            }
        }
    }

}
