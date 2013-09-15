package net.canarymod.hook.entity;

import net.canarymod.api.DamageSource;
import net.canarymod.api.entity.hanging.HangingEntity;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.CancelableHook;

/**
 * HangingEntity destroy hook. Contains information about a painting or item frame being destroyed.
 * In case the it was destroyed by a player, you will get this player too.
 *
 * @author Jason (darkdiplomat)
 * @author Chris (damagefilter)
 */
public final class HangingEntityDestroyHook extends CancelableHook {

    private HangingEntity hanging;
    private Player player;
    private DamageSource source;

    public HangingEntityDestroyHook(HangingEntity hanging, Player player, DamageSource source) {
        this.hanging = hanging;
        this.player = player;
        this.source = source;
    }

    /**
     * Gets the {@link HangingEntity}
     *
     * @return hanging entity
     */
    public HangingEntity getPainting() {
        return hanging;
    }

    /**
     * Gets the {@link Player} who destroyed the item frame or painting.
     * If the hook was not caused by a player, this will return null.
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }
    
    /**
     * Check if this hook was called because a player destroyed an item frame or painting.
     * 
     * @return
     *      boolean true if player destroyed the hanging entity, false otherwise
     */
    public boolean isCausedByPlayer() {
        return player != null;
    }

    /**
     * Gets the DamageSource associated
     *
     * @return damage source
     */
    public DamageSource getDamageSource() {
        return source;
    }

    @Override
    public final String toString() {
        return String.format("%s[Player=%s, HangingEntity=%s]", getName(), player, hanging);
    }
}
