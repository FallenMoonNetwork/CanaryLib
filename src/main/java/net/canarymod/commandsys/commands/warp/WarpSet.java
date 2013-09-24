package net.canarymod.commandsys.commands.warp;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.blocks.CommandBlock;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.Group;
import net.canarymod.warp.Warp;

/**
 * Command to set a warp (including private and group warps)
 *
 * @author Chris (damagefilter)
 */
public class WarpSet implements NativeCommand {
    public void execute(MessageReceiver caller, String[] args) {
        if (caller instanceof Server || caller instanceof CommandBlock) {
            caller.notice(Translator.translate("setwarp console"));
        }
        else {
            Player player = (Player) caller;
            if (Canary.warps().warpExists(args[1]) && !player.hasPermission("canary.command.warp.setwarp.admin")) {
                player.notice(Translator.translate("setwarp failed"));
                return;
            }
            // SET PUBLIC WARP
            if (args.length == 2 && player.hasPermission("canary.command.warp.set.public")) {
                Warp newWarp = new Warp(player.getLocation(), args[1]);

                Canary.warps().addWarp(newWarp);
                player.message(Colors.YELLOW + Translator.translateAndFormat("setwarp success", args[1]));
            }
            else if (args.length > 3) {
                // SET GROUP SPECIFIC WARP
                if (args[2].equalsIgnoreCase("G") && player.hasPermission("canary.command.warp.set.group")) {
                    Group[] groups = new Group[args.length - 3];

                    for (int i = 0; i < groups.length; i++) {
                        groups[i] = Canary.usersAndGroups().getGroup(args[i + 3]);
                    }
                    Warp newWarp = new Warp(player.getLocation(), groups, args[1]);

                    Canary.warps().addWarp(newWarp);
                    player.message(Colors.YELLOW + Translator.translateAndFormat("setwarp success group", args[1]));
                    return;
                }
                // SET PRIVATE WARP
                if (args[2].equalsIgnoreCase("P") && player.hasPermission("canary.command.warp.set.private")) {
                    Warp newWarp = new Warp(player.getLocation(), args[1], args[3], false);

                    Canary.warps().addWarp(newWarp);
                    player.message(Colors.YELLOW + Translator.translateAndFormat("setwarp success private", args[1]));
                }
                else {
                    Canary.help().getHelp(player, "setwarp");
                }
            }
            else {
                Canary.help().getHelp(player, "setwarp");
            }
        }
    }
}
