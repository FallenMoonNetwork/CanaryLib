package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Colors;
import net.canarymod.MessageReceiver;
import net.canarymod.api.Server;
import net.canarymod.api.entity.Player;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;
import net.canarymod.warp.Warp;

public class WarpCommand extends CanaryCommand {

    public WarpCommand() {
        super("canary.command.warp", "Teleport to a warp location", "Usage: /warp <warp name>", 2);
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
        caller.notify("The allmighty console may not decsend to the world of the mortals!");
    }
    
    private void player(Player player, String[] args) {
        Warp target = Canary.warps().getWarp(args[1]);
        if(target != null) {
            if(target.warp(player)) {
                player.sendMessage(Colors.Yellow + "Warped to "+target.getName());
                return;
            }
            else {
                player.sendMessage(Colors.Yellow + "You are not allowed to warp to "+target.getName());
                return;
            }
        }
        player.notify("Warp "+args[1]+" not found.");
    }

}
