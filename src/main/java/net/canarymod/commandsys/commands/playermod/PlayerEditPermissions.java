package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.permissionsystem.PermissionNode;

public class PlayerEditPermissions {

    //group) permission <player> <permission>:[value] add|remove
    public void execute(MessageReceiver caller, String[] args) {
        if(args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "playermod permission");
            return;
        }
        Player player = Canary.getServer().matchPlayer(args[1]);

        PermissionNode node = PermissionNode.fromString(args[2]);
        if(args[3].equalsIgnoreCase("add")) {
            if(player == null) {
                Canary.permissionManager().addPermission(node.getName(), node.getValue(), args[1], "player");
            }
            else {
                player.getPermissionProvider().addPermission(node.getName(), node.getValue());
            }
            caller.message(Colors.YELLOW + Translator.translate("modify permission added"));
        }
        else if(args[3].equalsIgnoreCase("remove")) {
            if(player == null) {
                Canary.permissionManager().removePlayerPermission(node.getName(), args[1]);
            }
            else {
                Canary.permissionManager().removePlayerPermission(node.getName(), player);
            }
            caller.message(Colors.YELLOW + Translator.translate("modify permission removed"));
        }
        else {
            Canary.help().getHelp(caller, "playermod permission");
        }

    }
}
