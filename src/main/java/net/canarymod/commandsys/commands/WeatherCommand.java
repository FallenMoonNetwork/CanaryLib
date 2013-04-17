package net.canarymod.commandsys.commands;


import java.util.Random;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.World;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandException;


public class WeatherCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            console((Server) caller, parameters);
        } else if (caller instanceof Player) {
            player((Player) caller, parameters);
        } else {
            throw new CommandException("Unknown MessageReceiver: " + caller.getClass().getSimpleName());
        }
    }

    private void console(Server caller, String[] args) {
        caller.notice(Translator.translate("weather console"));
    }

    private void player(Player player, String[] args) {
        World dim = player.getWorld();

        if (args[1].equalsIgnoreCase("check")) {
            if(!player.hasPermission("canary.command.weather.check")) {
                return;
            }
            String weather = dim.isRaining() ? "weather raining" : "weather sunny";
            weather = dim.isThundering() ? "weather thundering" : "weather sunny";
            player.sendMessage(Colors.YELLOW + Translator.translateAndFormat("weather check", weather));
            return;
        }
        if (args[1].equalsIgnoreCase("rain")) {
            if(!player.hasPermission("canary.command.weather.set")) {
                return;
            }
            dim.setRaining(true);
            dim.setRainTime(new Random().nextInt(15000));
            player.sendMessage(Colors.YELLOW + Translator.translate("weather set rain"));
            return;
        }
        if (args[1].equalsIgnoreCase("thunder")) {
            if(!player.hasPermission("canary.command.weather.set")) {
                return;
            }
            dim.setThundering(true);
            dim.setThunderTime(new Random().nextInt(15000));
            player.sendMessage(Colors.YELLOW + Translator.translate("weather set thunder"));
            return;
        }
        if (args[1].matches("clear")) {
            if(!player.hasPermission("canary.command.weather.set")) {
                return;
            }
            dim.setRaining(false);
            dim.setRainTime(0);
            dim.setThundering(false);
            dim.setThunderTime(0);
            player.sendMessage(Colors.YELLOW + Translator.translate("weather set clear"));
            return;
        }
        else {
           Canary.help().getHelp(player, "weather");
        }
    }

}
