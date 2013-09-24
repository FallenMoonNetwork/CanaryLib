package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.World;
import net.canarymod.api.world.blocks.CommandBlock;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandException;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to check or set the time   
 *
 * @author Chris (damagefilter)
 */
public class TimeCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            console((Server) caller);
        }
        else if (caller instanceof Player) {
            player((Player) caller, parameters);
        }
        else if (caller instanceof CommandBlock) {
            cmdblock((CommandBlock) caller, parameters);
        }
        else {
            throw new CommandException("Unknown MessageReceiver: " + caller.getClass().getSimpleName());
        }
    }

    private void cmdblock(CommandBlock block, String[] args) {
        World dim = block.getWorld();
        if (args[0].equals("/thetime")) {
            if (!block.hasPermission("canary.command.time.check")) {
                return;
            }
            block.message(Colors.YELLOW + Translator.translateAndFormat("time display", dim.getRelativeTime(), dim.getRawTime()));
            return;
        }

        if (args[1].equalsIgnoreCase("check")) {
            if (!block.hasPermission("canary.command.time.check")) {
                return;
            }
            block.message(Colors.YELLOW + Translator.translateAndFormat("time display", dim.getRelativeTime(), dim.getRawTime()));
        }
        else if (args[1].equalsIgnoreCase("day")) {
            if (!block.hasPermission("canary.command.time.set")) {
                return;
            }
            dim.setTime(0L);
            block.message(Colors.YELLOW + Translator.translate("time set day"));
        }
        else if (args[1].equalsIgnoreCase("night")) {
            if (!block.hasPermission("canary.command.time.set")) {
                return;
            }
            dim.setTime(13000L);
            block.message(Colors.YELLOW + Translator.translate("time set night"));
        }
        else if (args[1].matches("\\d+")) {
            if (!block.hasPermission("canary.command.time.set")) {
                return;
            }
            dim.setTime(Long.parseLong(args[1]));
            block.message(Colors.YELLOW + Translator.translate("time set"));
        }
        else {
            Canary.help().getHelp(block, "time");
        }
    }

    private void console(Server caller) {
        caller.notice(Translator.translate("time console"));
    }

    private void player(Player player, String[] args) {
        World dim = player.getWorld();
        if (args[0].equals("/thetime")) {
            if (!player.hasPermission("canary.command.time.check")) {
                return;
            }
            player.message(Colors.YELLOW + Translator.translateAndFormat("time display", dim.getRelativeTime(), dim.getRawTime()));
            return;
        }

        if (args[1].equalsIgnoreCase("check")) {
            if (!player.hasPermission("canary.command.time.check")) {
                return;
            }
            player.message(Colors.YELLOW + Translator.translateAndFormat("time display", dim.getRelativeTime(), dim.getRawTime()));
        }
        else if (args[1].equalsIgnoreCase("day")) {
            if (!player.hasPermission("canary.command.time.set")) {
                return;
            }
            dim.setTime(0L);
            player.message(Colors.YELLOW + Translator.translate("time set day"));
        }
        else if (args[1].equalsIgnoreCase("night")) {
            if (!player.hasPermission("canary.command.time.set")) {
                return;
            }
            dim.setTime(13000L);
            player.message(Colors.YELLOW + Translator.translate("time set night"));
        }
        else if (args[1].matches("\\d+")) {
            if (!player.hasPermission("canary.command.time.set")) {
                return;
            }
            dim.setTime(Long.parseLong(args[1]));
            player.message(Colors.YELLOW + Translator.translate("time set"));
        }
        else {
            Canary.help().getHelp(player, "time");
        }
    }

}
