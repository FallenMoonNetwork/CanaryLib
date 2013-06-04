package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.CancelableHook;

/**
 * Health Change hook
 * 
 * @author Jason (darkdiplomat)
 */
public final class HealthChangeHook extends CancelableHook {
    private Player player;
    private int oldVal, newVal;

    /**
     * @param player
     * @param oldVal
     * @param newVal
     */
    public HealthChangeHook(Player player, int oldVal, int newVal) {
        this.player = player;
        this.oldVal = oldVal;
        this.newVal = newVal;
    }

    /**
     * Gets the {@link Player} who's health is changing
     * 
     * @return the {@link Player}
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the old Health value
     * 
     * @return old value
     */
    public int getOldValue() {
        return oldVal;
    }

    /**
     * Gets the new Health value
     * 
     * @return new value
     */
    public int getNewValue() {
        return newVal;
    }

    @Override
    public final String toString() {
        return String.format("%s[Player=%s, Old Health=%s, New Health=%s]", getName(), player, oldVal, newVal);
    }
}
