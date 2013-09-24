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
 * Command to add a permission node to a player     
 *
 * @author Chris (damagefilter)
 */
public class PlayerPermissionAdd implements NativeCommand {
    // groupmod permission add group value
    public void execute(MessageReceiver caller, String[] args) {
        Player player = Canary.getServer().matchPlayer(args[1]);
        PermissionNode node = PermissionNode.fromString(args[2]);
        if (player == null) {
            OfflinePlayer oplayer = Canary.getServer().getOfflinePlayer(args[1]);
            oplayer.getPermissionProvider().addPermission(node.getName(), node.getValue());
        }
        else {
            player.getPermissionProvider().addPermission(node.getName(), node.getValue());
        }
        caller.message(Colors.YELLOW + Translator.translate("modify permission added"));
    }
}
