package net.canarymod.commandsys.commands.group;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.user.Group;

public class GroupPrefix {
    //group) prefix <group> <prefix>
    public void execute(MessageReceiver caller, String[] args) {
        if(args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "groupmod prefix");
            return;
        }
        Group group = Canary.usersAndGroups().getGroup(args[1]);
        if(group == null) {
            caller.notice(Translator.translateAndFormat("modify invalid group", args[1]));
        }
        group.setPrefix(args[2]);
        caller.message(Colors.YELLOW + Translator.translate("modify prefix set"));
    }
}
