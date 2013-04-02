package net.canarymod.hook.entity;


import net.canarymod.api.entity.living.EntityLiving;
import net.canarymod.hook.CancelableHook;


/**
 * Called when one entity is trying to mount another
 * @author chris
 *
 */
public final class EntityMountHook extends CancelableHook {
    private EntityLiving entity;
    private EntityLiving rider;

    public EntityMountHook(EntityLiving entity, EntityLiving rider) {
        this.entity = entity;
        this.rider = rider;
    }

    /**
     * Get the entity that is about to mount another one
     * @return the enteringEntity
     */
    public EntityLiving getEntity() {
        return entity;
    }

    /**
     * Get the entity that is about to mount the other one
     * @return
     */
    public EntityLiving getRider() {
        return rider;
    }

    @Override
    public final String toString() {
        return String.format("%s[Entity=%s, Rider=%s]", getName(), entity, rider);
    }
}
