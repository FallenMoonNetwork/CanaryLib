package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.api.Server;
import net.canarymod.api.entity.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;

public class Kill extends CanaryCommand {

    public Kill() {
        super("canary.command.kill", "Kill yourself or another player.", "Usage: /kill [playername]", 1, 2);
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
        if(args.length == 1) {
            caller.notice("You cannot kill the console. Use \"stop\" to stop the server.");
        }
        else {
            Player target = caller.matchPlayer(args[1]);
            if(target != null) {
                target.kill();
                caller.notice("You killed "+target.getName());
            }
            else {
                caller.notice(args[1]+" does not exist and cannot be killed.");
            }
        }
    }
    
    private void player(Player player, String[] args) {
        if(args.length == 1) {
            player.notice("You suicided.");
            player.kill();
        }
        else {
            if(player.hasPermission("canary.command.kill.other")) {
                Player target = Canary.getServer().matchPlayer(args[1]);
                if(target != null) {
                    target.kill();
                    player.notice("You killed "+target.getName());
                }
                else {
                    player.notice(args[1]+" does not exist and cannot be killed.");
                }
            }
        }
    }

}
