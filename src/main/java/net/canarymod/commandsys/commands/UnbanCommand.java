package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to unban a player
 *
 * @author Chris (damagefilter)
 */
public class UnbanCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] cmd) {
        if (cmd.length < 2) {
            Canary.help().getHelp(caller, "unban");
            return;
        }
        Canary.bans().unban(cmd[1]);
        caller.message(Colors.YELLOW + Translator.translateAndFormat("unban success", cmd[1]));
    }
}
