package net.canarymod.commandsys.commands.group;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.permissionsystem.PermissionNode;
import net.canarymod.user.Group;

/**
 * Command to add a permission to a group   
 *
 * @author Chris (damagefilter)
 */
public class GroupPermissionAdd implements NativeCommand {
    // groupmod permission add group value
    public void execute(MessageReceiver caller, String[] args) {
        Group group = Canary.usersAndGroups().getGroup(args[1]);
        if (group == null) {
            caller.notice(Translator.translateAndFormat("unknown group", args[1]));
            return;
        }
        PermissionNode node = PermissionNode.fromString(args[2]);
        group.getPermissionProvider().addPermission(node.getName(), node.getValue());
        caller.message(Colors.YELLOW + Translator.translate("modify permission added"));
    }
}
