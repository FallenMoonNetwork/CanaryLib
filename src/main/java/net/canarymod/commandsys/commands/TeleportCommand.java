package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.DimensionType;
import net.canarymod.api.world.UnknownWorldException;
import net.canarymod.api.world.World;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.hook.player.TeleportHook;

/**
 * Command to teleport a player to a player or x, y, z  
 *
 * @author Chris (damagefilter)
 */
public class TeleportCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Player) {
            player((Player) caller, parameters);
        }
        else {
            console(caller);
        }
    }

    private void console(MessageReceiver caller) {
        caller.notice(Translator.translate("tp console"));
    }

    private void player(Player player, String[] args) {
        Player target = Canary.getServer().matchPlayer(args[1]);
        if (args.length == 2) {
            if (target != null) {
                player.teleportTo(target.getLocation(), TeleportHook.TeleportCause.COMMAND);
                player.message(Colors.YELLOW + Translator.translateAndFormat("tp success other", target.getName()));
            }
            else {
                World w = Canary.getServer().getWorldManager().getWorld(args[1], player.hasPermission("canary.command.teleport.self.world.load"));
                if (w == null) {
                    player.notice(Translator.translateAndFormat("unknown player", args[1]));
                    return;
                }
                if (player.hasPermission("canary.command.teleport.self.world")) {
                    player.teleportTo(w.getSpawnLocation());
                }
            }
        }
        else if (args.length > 3) { // is it X Y Z?
            try {
                double x = Double.parseDouble(args[1]), y = Double.parseDouble(args[2]), z = Double.parseDouble(args[3]);
                if (args.length > 4 && player.hasPermission("canary.command.teleport.self.world")) {
                    try {
                        String dTypeName = args[4].replaceAll("(\\w+)(_(\\w+)$)", "$3");
                        DimensionType dType = DimensionType.fromName(dTypeName);
                        String correctedName = dType == null ? args[4] : args[4].replaceAll("_(\\w+)$", "");
                        if (dType == null) {
                            dType = DimensionType.fromName("NORMAL");
                        }
                        World world = Canary.getServer().getWorldManager().getWorld(correctedName, dType, args.length > 5 && player.hasPermission("canary.command.teleport.self.world.load") ? Boolean.valueOf(args[5]) : false);
                        if (world != null) {
                            player.teleportTo(x, y, z, world);
                        }
                        else {
                            player.notice("world not found");
                        }
                    }
                    catch (UnknownWorldException uwex) {
                        player.notice("world not found");
                    }
                }
                else {
                    player.teleportTo(x, y, z);
                }
            }
            catch (NumberFormatException nfex) {
                player.notice(Translator.translateAndFormat("bad coordinates"));
            }
        }
        else if (target != null) {
            player.teleportTo(target.getLocation(), TeleportHook.TeleportCause.COMMAND);
            player.message(Colors.YELLOW + Translator.translateAndFormat("tp success", target.getName()));
        }
        else {
            player.notice(Translator.translateAndFormat("unknown player", args[1]));
        }
    }

}
