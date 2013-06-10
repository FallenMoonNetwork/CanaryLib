package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandException;

public class ListPlugins {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            console((Server) caller);
        } else if (caller instanceof Player) {
            player((Player) caller);
        } else {
            throw new CommandException(Translator.translateAndFormat("unknown messagereceiver", caller.getClass().getSimpleName()));
        }
    }

    private void console(Server caller) {
        String list = Canary.loader().getReadablePluginListForConsole();

        caller.notice("**** PLUGINS ****");
        if (list != null) {
            caller.notice(list);
        } else {
            caller.notice(Translator.translate("no plugins"));
        }
    }

    private void player(Player player) {
        String list = Canary.loader().getReadablePluginList();

        player.sendMessage(Colors.YELLOW + "Plugins: ");
        if (list != null) {
            player.sendMessage(list);
        } else {
            player.notice(Translator.translate("no plugins"));
        }
    }

}
