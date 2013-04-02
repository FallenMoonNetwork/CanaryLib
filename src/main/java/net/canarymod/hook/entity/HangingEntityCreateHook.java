package net.canarymod.hook.entity;


import net.canarymod.api.entity.HangingEntity;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.CancelableHook;


/**
 * HangingEntity Create hook. Contains information about a player hanging a painting or itemframe.
 * 
 * @author Jason (darkdiplomat)
 */
public final class HangingEntityCreateHook extends CancelableHook {

    private HangingEntity hanging;
    private Player player;

    /**
     * Constructs a new HangingEntityCreateHook
     * 
     * @param hanging
     *            the {@link HangingEntity} being hung
     * @param player
     *            the {@link Player} doing the hanging
     */
    public HangingEntityCreateHook(HangingEntity hanging, Player player) {
        this.hanging = hanging;
        this.player = player;
    }

    /**
     * Gets the {@link HangingEntity}
     * 
     * @return the {@link HangingEntity}
     */
    public HangingEntity getHangingEntity() {
        return hanging;
    }

    /**
     * Gets the {@link Player}
     * 
     * @return the {@link Player}
     */
    public Player getPlayer() {
        return player;
    }

    @Override
    public final String toString() {
        return String.format("%s[Player=%s, HangingEntity=%s]", getName(), player, hanging);
    }
}