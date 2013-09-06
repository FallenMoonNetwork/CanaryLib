package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.statistics.Stat;
import net.canarymod.hook.CancelableHook;

/**
 * StatGainedHook<br>
 * Called when a {@link Player} gains a Stat
 *
 * @author Jason (darkdiplomat)
 */
public final class StatGainedHook extends CancelableHook {
    private Player player;
    private Stat stat;

    /**
     * Constructs a new StatGainedHook
     *
     * @param player
     *         the {@link Player} gaining a stat
     * @param stat
     *         the {@link Stat} being gained
     */
    public StatGainedHook(Player player, Stat stat) {
        this.player = player;
        this.stat = stat;
    }

    /**
     * Gets the {@link Player} gaining a {@link Stat}
     *
     * @return the {@link Player}
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the {@link Stat} being gained
     *
     * @return the {@link Stat}
     */
    public Stat getStat() {
        return stat;
    }

    @Override
    public final String toString() {
        return String.format("%s[Player=%s, Stat=%s]", getName(), player, stat);
    }
}
