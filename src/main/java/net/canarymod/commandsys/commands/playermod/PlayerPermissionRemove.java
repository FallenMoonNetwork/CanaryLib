package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.OfflinePlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.permissionsystem.PermissionNode;

public class PlayerPermissionRemove {
    // groupmod permission add group value
    public void execute(MessageReceiver caller, String[] args) {
        Player player = Canary.getServer().matchPlayer(args[1]);
        PermissionNode node = PermissionNode.fromString(args[2]);
        if (player == null) {
            OfflinePlayer oplayer = Canary.getServer().getOfflinePlayer(args[1]);
            Canary.permissionManager().removePlayerPermission(node.getName(), oplayer.getName(), oplayer.getWorld().getFqName());
        }
        else {
            Canary.permissionManager().removePlayerPermission(node.getName(), player);
        }
        caller.message(Colors.YELLOW + Translator.translate("modify permission removed"));
    }
}
