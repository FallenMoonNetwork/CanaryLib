package net.canarymod.commandsys.commands.warp;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.blocks.CommandBlock;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.warp.Warp;

public class WarpUse {
    public void execute(MessageReceiver caller, String[] args) {
        if (caller instanceof Server || caller instanceof CommandBlock) {
            caller.notice(Translator.translate("warp console"));
        }
        else {
            Player player = (Player) caller;
            Warp target = Canary.warps().getWarp(args[1]);

            if (target != null) {
                if (target.warp(player)) {
                    player.sendMessage(Colors.YELLOW + Translator.translateAndFormat("warp success", target.getName()));
                    return;
                } else {
                    player.sendMessage(Colors.YELLOW + Translator.translateAndFormat("warp not allowed", target.getName()));
                    return;
                }
            }
            player.notice(Translator.translateAndFormat("warp unknown", args[1]));
        }
    }
}
