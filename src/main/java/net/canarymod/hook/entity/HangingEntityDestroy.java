package net.canarymod.hook.entity;


import net.canarymod.api.entity.HangingEntity;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.CancelableHook;


/**
 * HangingEntity destroy hook. Contains information about a player destroying a painting or item frame.
 * 
 * @author Jason (darkdiplomat)
 */
public final class HangingEntityDestroy extends CancelableHook {

    private HangingEntity hanging;
    private Player player;

    public HangingEntityDestroy(HangingEntity hanging, Player player) {
        this.hanging = hanging;
        this.player = player;
    }

    /**
     * Gets the {@link HangingEntity}
     * 
     * @return hanging
     */
    public HangingEntity getPainting() {
        return hanging;
    }

    /**
     * Gets the {@link Player}
     * 
     * @return player
     */
    public Player getPlayer() {
        return player;
    }
}
