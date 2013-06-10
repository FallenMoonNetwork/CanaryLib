package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.permissionsystem.PermissionNode;

public class PlayerPermissionAdd {
    // groupmod permission add group value
    public void execute(MessageReceiver caller, String[] args) {
        Player player = Canary.getServer().matchPlayer(args[1]);
        PermissionNode node = PermissionNode.fromString(args[2]);
        if (player == null) {
            Canary.permissionManager().addPermission(node.getName(), node.getValue(), args[1], "player");
        }
        else {
            player.getPermissionProvider().addPermission(node.getName(), node.getValue());
        }
        caller.message(Colors.YELLOW + Translator.translate("modify permission added"));
    }
}
