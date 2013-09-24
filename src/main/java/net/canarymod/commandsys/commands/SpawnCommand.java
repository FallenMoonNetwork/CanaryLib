package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.World;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to teleport yourself or someoneelse to spawn  
 *
 * @author Chris (damagefilter)
 */
public class SpawnCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Player) {
            player((Player) caller, parameters);
        }
        else {
            console(caller, parameters);
        }
    }

    private void console(MessageReceiver caller, String[] args) {
        if (args.length < 3) {
            caller.notice(Translator.translate("spawn failed console"));
        }
        else {
            Player player = Canary.getServer().matchPlayer(args[2]);
            World w = Canary.getServer().getWorld(args[1]);

            if (player != null && w != null) {
                player.teleportTo(w.getSpawnLocation());
                caller.notice(Translator.translateAndFormat("spawn success other", player.getName()));
            }
            else {
                caller.notice(Translator.translate("spawn failed console"));
            }
        }

    }

    private void player(Player player, String[] args) {
        if (args.length == 1) {
            player.teleportTo(player.getWorld().getSpawnLocation());
            player.message(Colors.YELLOW + Translator.translate("spawn success"));
        }
        else if (args.length == 2) {
            World w = Canary.getServer().getWorld(args[1]);

            if (w == null) {
                player.notice(Translator.translate("spawn failed"));
            }
            else {
                player.teleportTo(w.getSpawnLocation());
                player.message(Colors.YELLOW + Translator.translate("spawn success"));
            }
        }
        else {
            World w = Canary.getServer().getWorld(args[1]);
            Player target = Canary.getServer().matchPlayer(args[2]);

            if (target != null && w != null) {
                target.teleportTo(w.getSpawnLocation());
                player.message(Colors.YELLOW + Translator.translateAndFormat("spawn success other", player.getName()));
            }
        }
    }

}
