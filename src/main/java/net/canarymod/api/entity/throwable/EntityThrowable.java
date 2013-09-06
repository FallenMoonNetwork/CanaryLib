package net.canarymod.api.entity.throwable;

import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.Projectile;
import net.canarymod.api.entity.living.EntityLiving;

/**
 * Throwable interface
 *
 * @author Jason (darkdiplomat)
 */
public interface EntityThrowable extends Entity, Projectile {

    /**
     * Gets the EntityLiving that threw the Throwable
     *
     * @return the EntityLiving that threw the Throwable
     */
    public EntityLiving getThrower();

    /**
     * Gets the amount of gravity to apply to the thrown entity with each tick.
     *
     * @return gravity
     *         the amount of gravity applied
     */
    public float getGravity();

    /**
     * Sets the amount of gravity to apply to the thrown entity with each tick.
     *
     * @param velocity
     *         the amount of gravity to apply
     */
    public void setGravity(float velocity);
}
