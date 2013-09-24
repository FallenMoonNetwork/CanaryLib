package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.OfflinePlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.permissionsystem.PermissionNode;

/**
 * Command to check if a player has a permission node     
 *
 * @author Chris (damagefilter)
 */
public class PlayerPermissionCheck implements NativeCommand {
    // groupmod permission add group value
    public void execute(MessageReceiver caller, String[] args) {
        Player player = Canary.getServer().matchPlayer(args[1]);
        PermissionNode node = PermissionNode.fromString(args[2]);
        boolean result;
        boolean hasPath;
        if (player == null) {
            OfflinePlayer oplayer = Canary.getServer().getOfflinePlayer(args[1]);
            result = oplayer.hasPermission(node.getName());
            hasPath = oplayer.getPermissionProvider().pathExists(node.getName());
        }
        else {
            result = player.hasPermission(node.getName());
            hasPath = player.getPermissionProvider().pathExists(node.getName());
        }
        if (hasPath) {
            if (result) {
                caller.message(Colors.LIGHT_GREEN + node.getName() + ": true");
            }
            else {
                caller.message(Colors.LIGHT_RED + node.getName() + ": false");
            }
        }
        else {
            if (result) {
                caller.message(Colors.LIGHT_GREEN + node.getName() + ": true");
            }
            else {
                caller.message(Colors.YELLOW + node.getName() + ": " + Translator.translate("no"));
            }
        }
    }
}
