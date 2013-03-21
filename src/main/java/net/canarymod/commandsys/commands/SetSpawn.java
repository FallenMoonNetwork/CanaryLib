package net.canarymod.commandsys.commands;

import net.canarymod.api.Server;
import net.canarymod.api.entity.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;

public class SetSpawn extends CanaryCommand {

    public SetSpawn() {
        super("canary.command.setspawn", "Set the current worlds spawn location", "Usage: /setspawn", 1);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        if(caller instanceof Server) {
            console(caller);
        }
        else if(caller instanceof Player) {
            player((Player)caller, parameters);
        }
        else {
            throw new CommandException("Unknown MessageReceiver: "+caller.getClass().getSimpleName());
        }
    }
    
    private void console(MessageReceiver caller) {
        caller.notice("As the great Minecraft Skies cannot be reached by mortals, you cannot set a spawn here.");
    }
    
    private void player(Player player, String[] args) {
        player.getWorld().setSpawnLocation(player.getLocation());
        player.sendMessage(Colors.YELLOW + "Spawn has been set.");
    }

}
