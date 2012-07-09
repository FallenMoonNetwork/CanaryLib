package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Colors;
import net.canarymod.MessageReceiver;
import net.canarymod.TextFormat;
import net.canarymod.api.Server;
import net.canarymod.api.entity.Player;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;

public class ListPlugins extends CanaryCommand {

    public ListPlugins() {
        super("canary.command.plugin.list", "Get a list of plugins", "Usage: /listplugins", 1);
    }
    
    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        if(caller instanceof Server) {
            console((Server)caller);
        }
        else if(caller instanceof Player) {
            player((Player)caller);
        }
        else {
            throw new CommandException("Unknown MessageReceiver: "+caller.getClass().getSimpleName());
        }
    }
    
    private void console(Server caller) {
        String list = Canary.loader().getReadablePluginList();
        caller.notify("**** PLUGINS ****");
        if(list != null) {
            caller.notify(TextFormat.removeFormatting(list));
        }
        else {
            caller.notify("No plugins loaded.");
        }
    }
    
    private void player(Player player) {
        String list = Canary.loader().getReadablePluginList();
        player.sendMessage(Colors.Yellow+"Plugins: ");
        if(list != null) {
            player.sendMessage(list);
        }
        else {
            player.notify("No plugins loaded.");
        }
    }

}
