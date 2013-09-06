package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.Hook;

/**
 * LevelUp hook. Called when a {@link Player} levels up
 *
 * @author Chris (damagefilter)
 */
public final class LevelUpHook extends Hook {
    private Player player;

    public LevelUpHook(Player player) {
        this.player = player;
    }

    /**
     * Get the {@link Player} instance
     *
     * @return the {@link Player} leveling up
     */
    public Player getPlayer() {
        return player;
    }

    @Override
    public final String toString() {
        return String.format("%s[Player=%s]", getName(), player);
    }
}
