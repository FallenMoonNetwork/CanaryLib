package net.canarymod.commandsys.commands.group;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.user.Group;

public class GroupCreate {
    //group) create <name> [parent]
    public void execute(MessageReceiver caller, String[] args) {
        if (args.length < 3) {
            Canary.help().getHelp(caller, "groupmod create");
            return;
        }
        if(args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "group create");
            return;
        }
        Group group = new Group();
        group.setName(args[1]);
        group.setPermissionProvider(Canary.permissionManager().getGroupsProvider(args[1]));
        if (args.length == 3) {
            Group parent = Canary.usersAndGroups().getGroup(args[2]);
            if (parent == null) {
                caller.notice(Translator.translateAndFormat("group unknown parent", args[2]));
                return;
            }
            group.setParent(parent);
        }
        Canary.usersAndGroups().addGroup(group);
        caller.message(Colors.YELLOW + Translator.translateAndFormat("group created", group.getName()));
    }
}
