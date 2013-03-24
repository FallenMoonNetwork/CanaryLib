package net.canarymod.hook.player;


import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.Hook;


/**
 * Food level hook. Contains information about a player's food level/saturation/exhaustion changes
 * @author Jason Jones
 *
 */
public final class FoodLevelHook extends Hook {

    private Player player;
    private int oldval, newval;

    public FoodLevelHook(Player player, int oldval, int newval) {
        this.player = player;
        this.oldval = oldval;
        this.newval = newval;
    }

    /**
     * Gets the {@link Player}
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the old value
     * @return
     */
    public int getOldValue() {
        return oldval;
    }

    /**
     * Gets the new value
     * @return
     */
    public int getNewValue() {
        return newval;
    }

    /**
     * Sets the new value
     * @param value
     */
    public void setNewValue(int value) {
        this.newval = value;
    }

    /**
     * Return the set of Data in this order: PLAYER OLDVAL NEWVAL
     */
    @Override
    public Object[] getDataSet() {
        return new Object[] { player, oldval, newval };
    }
}
