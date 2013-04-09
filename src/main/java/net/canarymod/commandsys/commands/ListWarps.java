package net.canarymod.commandsys.commands;


import java.util.List;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandException;
import net.canarymod.warp.Warp;


public class ListWarps {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            console((Server) caller);
        } else if (caller instanceof Player) {
            player((Player) caller);
        } else {
            throw new CommandException(Translator.translateAndFormat("unknown messagereceiver", caller.getClass().getSimpleName()));
        }
    }

    private void console(Server caller) {
        caller.notice("**** WARPS ****");

        List<Warp> warps = Canary.warps().getAllWarps();
        StringBuilder warpList = new StringBuilder();

        for (Warp warp : warps) {
            warpList.append(warp.getName()).append(",");
        }
        if (warpList.length() > 0) {
            Canary.logInfo(warpList.toString());
        } else {
            Canary.logInfo(Translator.translate("no warps"));
        }
    }

    private void player(Player player) {
        player.sendMessage(Colors.YELLOW + Translator.translate("warps available"));

        List<Warp> warps = Canary.warps().getAllWarps();
        StringBuilder warpList = new StringBuilder();

        for (Warp w : warps) {
            if (w.getOwner() != null) {
                if (w.isPlayerHome() && w.getOwner().equals(player.getName())) {
                    warpList.append(Colors.LIGHT_GREEN).append("(").append(Translator.translate("your home")).append(")").append(Colors.WHITE).append(",");
                }
                else if (!w.isPlayerHome() && w.getOwner().equals(player.getName()) || (player.isAdmin() || player.hasPermission("canary.command.warp.admin"))) {
                    warpList.append(Colors.ORANGE).append(w.getName()).append("(").append(Translator.translate("private")).append(")").append(Colors.WHITE).append(",");
                }
            }
            else if (w.isGroupRestricted() && w.isGroupAllowed(player.getGroup())) {
                warpList.append(Colors.YELLOW).append(w.getName()).append("(").append(Translator.translate("group")).append(")").append(Colors.WHITE).append(",");
            }
            else if (!w.isGroupRestricted()) {
                warpList.append(w.getName()).append(",");
            }
        }

        if (warpList.length() > 0) {
            player.sendMessage(warpList.toString());
        } else {
            player.notice(Translator.translate("no warps"));
        }

    }

}
