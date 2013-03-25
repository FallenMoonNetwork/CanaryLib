package net.canarymod.hook.world;


import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.blocks.Sign;
import net.canarymod.hook.CancelableHook;


/**
 * Sign hook. Contains infomation about a sign either being changed by or shown to a player
 * @author Jason Jones
 */
public final class SignHook extends CancelableHook {

    private Sign sign;
    private Player player;

    public SignHook(Player player, Sign sign, boolean change) {
        this.player = player;
        this.sign = sign;
    }

    /**
     * Gets the {@link Player}
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the {@link Sign}
     * @return sign
     */
    public Sign getSign() {
        return sign;
    }
}
