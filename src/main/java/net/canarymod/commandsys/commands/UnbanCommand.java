package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandException;
import net.canarymod.commandsys.NativeCommand;

public class UnbanCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] cmd) {
        if (caller instanceof Server || caller instanceof Player) {
            if (cmd.length < 2) {
                Canary.help().getHelp(caller, "unban");
                return;
            }
            Canary.bans().unban(cmd[1]);
            caller.message(Colors.YELLOW + Translator.translateAndFormat("unban success", cmd[1]));
        }
        else {
            throw new CommandException(Translator.translateAndFormat("unknown messagereceiver", caller.getClass().getSimpleName()));
        }
    }
}
