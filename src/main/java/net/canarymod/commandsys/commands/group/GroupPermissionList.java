package net.canarymod.commandsys.commands.group;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.user.Group;

public class GroupPermissionList {
    //groupmod permission add group value
    public void execute(MessageReceiver caller, String[] args) {
        Group group = Canary.usersAndGroups().getGroup(args[1]);
        if(group == null) {
            caller.notice(Translator.translateAndFormat("unknown group", args[1]));
            return;
        }
        group.getPermissionProvider().printPermissionsToCaller(caller);
    }
}
