package net.canarymod.commandsys.commands;


import java.util.Random;

import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.World;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;


public class WeatherCommand extends CanaryCommand {

    public WeatherCommand() {
        super("canary.command.weather", Translator.translate("weather info"), Translator.translateAndFormat("usage", "/weather 'check'|'sun'|'rain'|'thunder'"), 2, 3);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
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
            String weather = dim.isRaining() ? "raining" : "sunny";
            weather = dim.isThundering() ? "thundering" : "sunny";
            player.sendMessage(Colors.YELLOW + Translator.translateAndFormat("weather check", weather));
            return;
        }
        if (args[1].equalsIgnoreCase("rain")) {
            dim.setRaining(true);
            dim.setRainTime(new Random().nextInt(15000));
            player.sendMessage(Colors.YELLOW + Translator.translate("weather set rain"));
            return;
        }
        if (args[1].equalsIgnoreCase("thunder")) {
            dim.setThundering(true);
            dim.setThunderTime(new Random().nextInt(15000));
            player.sendMessage(Colors.YELLOW + Translator.translate("weather set thunder"));
            return;
        }
        if (args[1].matches("sun")) {
            dim.setRaining(false);
            dim.setRainTime(0);
            dim.setThundering(false);
            dim.setThunderTime(0);
            player.sendMessage(Colors.YELLOW + Translator.translate("weather set sun"));
            return;
        }
        else {
            player.notice(Translator.translateAndFormat("usage", "/weather 'check'|'sun'|'rain'|'thunder'"));
        }
    }

}
