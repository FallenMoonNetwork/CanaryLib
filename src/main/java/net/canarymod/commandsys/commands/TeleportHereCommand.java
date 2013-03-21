package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.api.Server;
import net.canarymod.api.entity.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;

public class TeleportHereCommand extends CanaryCommand {

    public TeleportHereCommand() {
        super("canary.command.tphere", "Teleport to a player", "Usage: /tphere [playername]", 2);
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
        caller.notice("The allmighty console may not call a mortal into the great Minecraft Skies!");
    }
    
    private void player(Player player, String[] args) {
       Player target = Canary.getServer().matchPlayer(args[1]);
       if(target != null) {
           target.teleportTo(player.getLocation());
           player.sendMessage(Colors.YELLOW + target.getName() + " was teleported to you");
       }
       else {
           player.notice(args[1] + " does not exist.");
       }
    }

}
