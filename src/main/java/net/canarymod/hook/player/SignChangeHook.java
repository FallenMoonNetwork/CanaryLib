package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.blocks.Sign;
import net.canarymod.hook.CancelableHook;

/**
 * Sign Change hook. Contains information about a sign either being changed by a player
 *
 * @author Jason (darkdiplomat)
 */
public final class SignChangeHook extends CancelableHook {

    private Sign sign;
    private Player player;

    public SignChangeHook(Player player, Sign sign) {
        this.player = player;
        this.sign = sign;
    }

    /**
     * Gets the {@link Player}
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the {@link Sign}
     *
     * @return sign
     */
    public Sign getSign() {
        return sign;
    }

    @Override
    public final String toString() {
        return String.format("%s[Player=%s, Sign=%s]", getName(), player, sign);
    }
}
