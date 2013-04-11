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
        if(player == null) {
            caller.notice(Translator.translateAndFormat("unknown player", args[1]));
            return;
        }

        PermissionNode node = PermissionNode.fromString(args[2]);
        if(args[3].equalsIgnoreCase("add")) {
            player.getPermissionProvider().addPermission(node.getName(), node.getValue());
            caller.message(Colors.YELLOW + Translator.translate("modify permission added"));
        }
        else if(args[3].equalsIgnoreCase("remove")) {
            Canary.permissionManager().removePlayerPermission(node.getName(), player);
        }
        else {
            Canary.help().getHelp(caller, "player permission");
        }

    }
}
