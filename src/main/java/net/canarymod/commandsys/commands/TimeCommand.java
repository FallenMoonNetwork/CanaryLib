package net.canarymod.commandsys.commands;

import net.canarymod.api.Server;
import net.canarymod.api.entity.Player;
import net.canarymod.api.world.World;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;

public class TimeCommand extends CanaryCommand {

    public TimeCommand() {
        super("canary.command.time", "Display or set the time", "Usage: /time 'day'|'night'|'check'|'relative time (0 to 24000)'", 2, 3);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        if(caller instanceof Server) {
            console((Server)caller, parameters);
        }
        else if(caller instanceof Player) {
            player((Player)caller, parameters);
        }
        else {
            throw new CommandException("Unknown MessageReceiver: "+caller.getClass().getSimpleName());
        }
    }
    
    private void console(Server caller, String[] args) {
        caller.notice("You cannot change the time from the greate Minecraft Skies.");
    }
    
    private void player(Player player, String[] args) {
        World dim = player.getWorld();
        if(args[1].equalsIgnoreCase("check")) {
            player.sendMessage(Colors.YELLOW+"The time: " + dim.getRelativeTime() + Colors.LIGHT_GRAY + " (RAW: " + dim.getRawTime() + ")");
            return;
        }
        else if(args[1].equalsIgnoreCase("day")) {
            dim.setTime(0L);
            player.sendMessage(Colors.YELLOW+"The time has been set. Good morning!");
            return;
        }
        else if(args[1].equalsIgnoreCase("night")) {
            dim.setTime(13000L);
            player.sendMessage(Colors.YELLOW+"The time has been set. Carpe noctem.");
            return;
        }
        else if(args[1].matches("\\d+")) {
            dim.setTime(Long.parseLong(args[1]));
            player.sendMessage(Colors.YELLOW+"The time has been set.");
            return;
        }
        else {
            player.notice("Usage: /time 'day' | 'night' | 'check' | 'relative time (0 to 24000)'");
        }
    }

}
