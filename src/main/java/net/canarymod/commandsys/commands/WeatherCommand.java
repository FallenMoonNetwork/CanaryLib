package net.canarymod.commandsys.commands;

import java.util.Random;

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
 * Command to check or set the weather
 *
 * @author Chris (damagefilter)
 */
public class WeatherCommand implements NativeCommand {

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

    private void console(Server caller) {
        caller.notice(Translator.translate("weather console"));
    }

    private void cmdblock(CommandBlock block, String[] args) {
        World dim = block.getWorld();

        if (args[1].equalsIgnoreCase("check")) {
            if (!block.hasPermission("canary.command.weather.check")) {
                return;
            }
            String weather = dim.isRaining() ? dim.isThundering() ? "weather thundering" : "weather raining" : "weather sunny";
            block.message(Colors.YELLOW + Translator.translateAndFormat("weather check", weather));
            return;
        }
        if (args[1].equalsIgnoreCase("rain")) {
            if (!block.hasPermission("canary.command.weather.set")) {
                return;
            }
            dim.setRaining(true);
            dim.setRainTime(new Random().nextInt(15000));
            block.message(Colors.YELLOW + Translator.translate("weather set rain"));
            return;
        }
        if (args[1].equalsIgnoreCase("thunder")) {
            if (!block.hasPermission("canary.command.weather.set")) {
                return;
            }
            dim.setThundering(true);
            dim.setThunderTime(new Random().nextInt(15000));
            block.message(Colors.YELLOW + Translator.translate("weather set thunder"));
            return;
        }
        if (args[1].matches("clear")) {
            if (!block.hasPermission("canary.command.weather.set")) {
                return;
            }
            dim.setRaining(false);
            dim.setRainTime(0);
            dim.setThundering(false);
            dim.setThunderTime(0);
            block.message(Colors.YELLOW + Translator.translate("weather set clear"));
        }
        else {
            Canary.help().getHelp(block, "weather");
        }
    }

    private void player(Player player, String[] args) {
        World dim = player.getWorld();

        if (args[1].equalsIgnoreCase("check")) {
            if (!player.hasPermission("canary.command.weather.check")) {
                return;
            }
            String weather = dim.isRaining() ? dim.isThundering() ? "weather thundering" : "weather raining" : "weather sunny";
            player.message(Colors.YELLOW + Translator.translateAndFormat("weather check", weather));
            return;
        }
        if (args[1].equalsIgnoreCase("rain")) {
            if (!player.hasPermission("canary.command.weather.set")) {
                return;
            }
            dim.setRaining(true);
            dim.setRainTime(new Random().nextInt(15000));
            player.message(Colors.YELLOW + Translator.translate("weather set rain"));
            return;
        }
        if (args[1].equalsIgnoreCase("thunder")) {
            if (!player.hasPermission("canary.command.weather.set")) {
                return;
            }
            dim.setThundering(true);
            dim.setThunderTime(new Random().nextInt(15000));
            player.message(Colors.YELLOW + Translator.translate("weather set thunder"));
            return;
        }
        if (args[1].matches("clear")) {
            if (!player.hasPermission("canary.command.weather.set")) {
                return;
            }
            dim.setRaining(false);
            dim.setRainTime(0);
            dim.setThundering(false);
            dim.setThunderTime(0);
            player.message(Colors.YELLOW + Translator.translate("weather set clear"));
        }
        else {
            Canary.help().getHelp(player, "weather");
        }
    }

}
