package net.canarymod.commandsys.commands;

import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to set the spawn point of the server  
 *
 * @author Chris (damagefilter)
 */
public class SetSpawn implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Player) {
            player((Player) caller);
        }
        else {
            console(caller);
        }
    }

    private void console(MessageReceiver caller) {
        caller.notice(Translator.translate("setspawn console"));
    }

    private void player(Player player) {
        player.getWorld().setSpawnLocation(player.getLocation());
        player.message(Colors.YELLOW + Translator.translate("setspawn success"));
    }

}
