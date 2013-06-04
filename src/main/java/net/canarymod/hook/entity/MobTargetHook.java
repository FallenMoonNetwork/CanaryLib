package net.canarymod.hook.entity;

import net.canarymod.api.entity.living.EntityLiving;
import net.canarymod.hook.CancelableHook;

/**
 * Mob target hook
 * <p>
 * Contains information about a {@link LivingEntity} targeting another {@link LivingEntity} either for attack or following
 * 
 * @author Jason (darkdiplomat)
 */
public final class MobTargetHook extends CancelableHook {

    private EntityLiving entity, target;

    /**
     * Constructs a new MobTargetHook
     * 
     * @param entity
     *            the {@link EntityLiving} doing the targeting
     * @param target
     *            the {@link EntityLiving} target
     */
    public MobTargetHook(EntityLiving entity, EntityLiving target) {
        this.entity = entity;
        this.target = target;
    }

    /**
     * Gets the {@link EntityLiving} doing the targeting
     * 
     * @return entity
     */
    public EntityLiving getEntity() {
        return entity;
    }

    /**
     * Gets the {@link EntityLiving} being targeted
     * 
     * @return the {@link EntityLiving} target
     */
    public EntityLiving getTarget() {
        return target;
    }

    @Override
    public final String toString() {
        return String.format("%s[Entity=%s, Target=%s]", getName(), entity, target);
    }
}
