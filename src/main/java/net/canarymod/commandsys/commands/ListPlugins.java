package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to list all the plugins on the server (both enabled and disabled)
 *
 * @author Chris (damagefilter)
 */
public class ListPlugins implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Player) {
            player((Player) caller);
        }
        else {
            console(caller);
        }
    }

    private void console(MessageReceiver caller) {
        String list = Canary.loader().getReadablePluginListForConsole();

        caller.notice("**** PLUGINS ****");
        if (list != null) {
            caller.notice(list);
        }
        else {
            caller.notice(Translator.translate("no plugins"));
        }
    }

    private void player(Player player) {
        String list = Canary.loader().getReadablePluginList();

        player.message(Colors.YELLOW + "Plugins: ");
        if (list != null) {
            player.message(list);
        }
        else {
            player.notice(Translator.translate("no plugins"));
        }
    }

}
