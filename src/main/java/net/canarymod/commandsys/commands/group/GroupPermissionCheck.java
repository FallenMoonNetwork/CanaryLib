package net.canarymod.commandsys.commands.group;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.permissionsystem.PermissionNode;
import net.canarymod.user.Group;

/**
 * Command to check if a group has a permission node
 *
 * @author Chris (damagefilter)
 */
public class GroupPermissionCheck implements NativeCommand {
    // groupmod permission check group value
    public void execute(MessageReceiver caller, String[] args) {
        Group group = Canary.usersAndGroups().getGroup(args[1]);
        if (group == null) {
            caller.notice(Translator.translateAndFormat("unknown group", args[1]));
            return;
        }
        PermissionNode node = PermissionNode.fromString(args[2]);
        if (group.getPermissionProvider().pathExists(node.getName())) {
            if (group.hasPermission(node.getName())) {
                caller.message(Colors.LIGHT_GREEN + node.getName() + ": true");
            }
            else {
                caller.message(Colors.LIGHT_RED + node.getName() + ": false");
            }
        }
        else {
            if (group.hasPermission(node.getName())) {
                caller.message(Colors.LIGHT_GREEN + node.getName() + ": true");
            }
            else {
                caller.message(Colors.YELLOW + node.getName() + ": " + Translator.translate("no"));
            }
        }
    }
}
