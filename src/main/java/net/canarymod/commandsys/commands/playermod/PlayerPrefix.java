package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.OfflinePlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.visualillusionsent.utils.StringUtils;

public class PlayerPrefix {
    //player) prefix <player> [prefix]
    public void execute(MessageReceiver caller, String[] args) {
        if(args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "playermod prefix");
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
        Player target = Canary.getServer().matchPlayer(args[1]);
        if(target == null) {
            OfflinePlayer oplayer = Canary.getServer().getOfflinePlayer(args[1]);
            oplayer.setPrefix(prefix);
            caller.message(Colors.YELLOW + Translator.translate("modify prefix set"));
            return;
        }
        target.setColor(prefix);
        caller.message(Colors.YELLOW + Translator.translate("modify prefix set"));
    }
}
