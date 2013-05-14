package net.canarymod.commandsys.commands.group;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.user.Group;
import net.visualillusionsent.utils.StringUtils;

public class GroupPrefix {
    //group) prefix <group> <prefix>
    public void execute(MessageReceiver caller, String[] args) {
        if(args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "groupmod prefix");
            return;
        }
        Group group = Canary.usersAndGroups().getGroup(args[1]);
        if(group == null) {
            caller.notice(Translator.translateAndFormat("unknown group", args[1]));
            return;
        }
        String prefix = null;
        if(args.length >= 3) {
            //TODO: Use a regex instead? Can't think of any :S
            char[] chars = StringUtils.joinString(args, " ", 2).toCharArray();
            for(int i = 0; i < chars.length; ++i) {
                if((i+1 < chars.length) && (chars[i] == '&' && chars[i+1] != ' ')) {
                    chars[i] = '§';
                }
            }
            prefix = String.copyValueOf(chars);
        }
        group.setPrefix(prefix);
        Canary.usersAndGroups().updateGroup(group);
        caller.message(Colors.YELLOW + Translator.translate("modify prefix set"));
    }
}
