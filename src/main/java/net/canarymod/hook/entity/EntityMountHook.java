package net.canarymod.hook.entity;

import net.canarymod.api.entity.living.LivingBase;
import net.canarymod.hook.CancelableHook;

/**
 * Called when one entity is trying to mount another
 *
 * @author Chris (damagefilter)
 */
public final class EntityMountHook extends CancelableHook {
    private LivingBase entity;
    private LivingBase rider;

    public EntityMountHook(LivingBase entity, LivingBase rider) {
        this.entity = entity;
        this.rider = rider;
    }

    /**
     * Get the entity that is about to mount another one
     *
     * @return the entering Entity
     */
    public LivingBase getEntity() {
        return entity;
    }

    /**
     * Get the entity that is about to mount the other one
     *
     * @return the {@link LivingBase} rider
     */
    public LivingBase getRider() {
        return rider;
    }

    @Override
    public final String toString() {
        return String.format("%s[Entity=%s, Rider=%s]", getName(), entity, rider);
    }
}
