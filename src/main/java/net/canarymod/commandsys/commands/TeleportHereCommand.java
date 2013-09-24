package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.blocks.CommandBlock;
import net.canarymod.api.world.position.Location;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandException;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.hook.player.TeleportHook;

/**
 * Command to teleport a player to the executers location
 *
 * @author Chris (damagefilter)
 */
public class TeleportHereCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            console((Server) caller);
        }
        else if (caller instanceof Player) {
            player((Player) caller, parameters);
        }
        else if(caller instanceof CommandBlock) {
            cmdblock((CommandBlock)caller, parameters);
        }
        else {
            throw new CommandException("Unknown MessageReceiver: " + caller.getClass().getSimpleName());
        }
    }

    private void cmdblock(CommandBlock block, String[] args) {
        Player target = Canary.getServer().matchPlayer(args[1]);

        if (target != null) {
            Location l = block.getBlock().getLocation();
            l.setY(l.getY()+1);
            target.teleportTo(l, TeleportHook.TeleportCause.COMMAND);
            block.message(Colors.YELLOW + Translator.translateAndFormat("tphere success", target.getName()));
        }
        else {
            block.notice(Translator.translateAndFormat("unknown player", args[1]));
        }
    }

    private void console(Server caller) {
        caller.notice(Translator.translate("tphere console"));
    }

    private void player(Player player, String[] args) {
        Player target = Canary.getServer().matchPlayer(args[1]);

        if (target != null) {
            target.teleportTo(player.getLocation(), TeleportHook.TeleportCause.COMMAND);
            player.message(Colors.YELLOW + Translator.translateAndFormat("tphere success", target.getName()));
        }
        else {
            player.notice(Translator.translateAndFormat("unknown player", args[1]));
        }
    }

}
