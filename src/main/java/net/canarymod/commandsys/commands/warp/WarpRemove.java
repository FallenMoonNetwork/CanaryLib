package net.canarymod.commandsys.commands.warp;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.warp.Warp;

/**
 * Command to remove a warp
 *
 * @author Chris (damagefilter)
 */
public class WarpRemove implements NativeCommand {
    public void execute(MessageReceiver caller, String[] args) {
        if (Canary.warps().warpExists(args[1])) {
            Warp target = Canary.warps().getWarp(args[1]);
            Canary.warps().removeWarp(target);
            caller.message(Colors.YELLOW + Translator.translateAndFormat("warp removed", target.getName()));
        }
        else {
            caller.notice(Translator.translateAndFormat("warp unknown", args[1]));
        }
    }
}
