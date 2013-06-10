package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.Hook;

/**
 * Chat hook. Contains player, prefix, message and receivers information
 * 
 * @author Chris Ksoll
 */
public final class LevelUpHook extends Hook {
    private Player player;

    public LevelUpHook(Player player) {
        this.player = player;
    }

    /**
     * Get the {@link Player} instance
     * 
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    @Override
    public final String toString() {
        return String.format("%s[Player=%s]", getName(), player);
    }
}
