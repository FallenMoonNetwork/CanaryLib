package net.canarymod.commandsys.commands.group;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.permissionsystem.PermissionNode;
import net.canarymod.user.Group;

public class GroupEditPermissions {

    //group) permission <group> <permission>:[value] add|remove
    public void execute(MessageReceiver caller, String[] args) {
        if(args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "group permission");
            return;
        }
        Group group = Canary.usersAndGroups().getGroup(args[1]);
        if(group == null) {
            caller.notice(Translator.translateAndFormat("group unknown", args[1]));
            return;
        }

        PermissionNode node = PermissionNode.fromString(args[2]);
        if(args[3].equalsIgnoreCase("add")) {
            group.getPermissionProvider().addPermission(node.getName(), node.getValue());
            caller.message(Colors.YELLOW + Translator.translate("modify permission added"));
        }
        else if(args[3].equalsIgnoreCase("remove")) {
            Canary.permissionManager().removeGroupPermission(node.getName(), group);
        }
        else {
            Canary.help().getHelp(caller, "group permission");
        }

    }
}
