package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.World;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;

public class Mode extends CanaryCommand {

    public Mode() {
        super("canary.command.mode", "Change your own or others game mode", "Usage: /mode <mode id> [playername] (0 = normal, 1 = creative)", 2, 3);
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
        if(args.length == 2) {
            caller.notice("You cannot set the Console Mode.");
        }
        else {
            Player target = caller.matchPlayer(args[2]);
            if(target != null) {
                int mode = Integer.parseInt(args[1]);
                target.setMode(mode);
                caller.notice(target.getName()+"'s mode has been set to "+World.GameMode.fromId(mode).name());
            }
            else {
                caller.notice(args[2]+" does not exist!");
            }
            
        }
    }
    
    private void player(Player player, String[] args) {
        int mode = Integer.parseInt(args[1]);
        if(args.length == 3) {
            Player receiver = Canary.getServer().matchPlayer(args[2]);
            if(receiver == null) {
                player.notice("Player "+args[2]+" does not exist!");
            } 
            else {
                receiver.setMode(mode);
                player.notice(receiver.getName()+"'s mode has been set to "+World.GameMode.fromId(mode).name());
            }
        }
        else {
            player.setMode(mode);
        }
    }

}
