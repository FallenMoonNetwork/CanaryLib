package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.CancelableHook;

/**
 * Experience hook. Contains information about player experience changes.
 *
 * @author Jason (darkdiplomat)
 */
public final class ExperienceHook extends CancelableHook {

    private Player player;
    private int oldval, newval;

    public ExperienceHook(Player player, int oldval, int newval) {
        this.player = player;
        this.oldval = oldval;
        this.newval = newval;
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
     * Gets the old experience value
     *
     * @return oldval
     */
    public int getOldValue() {
        return oldval;
    }

    /**
     * Gets the new experience value
     *
     * @return newval
     */
    public int getNewValue() {
        return newval;
    }

    @Override
    public final String toString() {
        return String.format("%s[Player=%s, Old Experience=%s, New Experience=%s]", getName(), player, oldval, newval);
    }
}
