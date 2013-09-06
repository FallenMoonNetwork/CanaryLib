package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.Hook;

/**
 * Player Left Click Hook<br>
 * Called when a {@link Player} swings their arm with a Left Click
 *
 * @author Jason (darkdiplomat)
 */
public class PlayerArmSwingHook extends Hook {
    private Player player;

    public PlayerArmSwingHook(Player player) {
        this.player = player;
    }

    /**
     * Gets the {@link Player} clicking
     *
     * @return the {@link Player}
     */
    public Player getPlayer() {
        return player;
    }

    @Override
    public final String toString() {
        return String.format("%s[Player=%s]", getName(), player);
    }
}
