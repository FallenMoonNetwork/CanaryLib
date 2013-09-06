package net.canarymod.hook.entity;

import net.canarymod.api.DamageSource;
import net.canarymod.api.entity.hanging.HangingEntity;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.CancelableHook;

/**
 * HangingEntity destroy hook. Contains information about a player destroying a painting or item frame.
 *
 * @author Jason (darkdiplomat)
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
     * Gets the {@link Player}
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
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
