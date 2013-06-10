package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.api.OfflinePlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;

public class PlayerPermissionList {
    // groupmod permission add group value
    public void execute(MessageReceiver caller, String[] args) {
        Player player = Canary.getServer().matchPlayer(args[1]);
        if (player == null) {
            OfflinePlayer oplayer = Canary.getServer().getOfflinePlayer(args[1]);
            oplayer.getPermissionProvider().printPermissionsToCaller(caller);
            return;
        }
        player.getPermissionProvider().printPermissionsToCaller(caller);
    }
}
