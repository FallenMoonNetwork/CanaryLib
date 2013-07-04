package net.canarymod.api.entity.living;

import net.canarymod.api.entity.Entity;

/**
 * EntityLivingBase wrapper interface
 * 
 * @author Jason (darkdiplomat)
 */
public interface LivingBase extends Entity {

    /**
     * Get this Entities health. May not work on entities that are not
     * LivingEntity
     * 
     * @return health
     */
    public float getHealth();

    /**
     * Get the maximum allowed amount of health for this entity
     * 
     * @return maximum health
     */
    public float getMaxHealth();

    /**
     * Set this entities health. May not work on entities that are not
     * LivingEntity
     * 
     * @param health
     *            the health to be set
     */
    public void setHealth(float health);

    /**
     * Increase this entities health. This does not set but add the amount of
     * health
     * 
     * @param amount
     *            to increase the health with (negative values decrease)
     */
    public void increaseHealth(float health);

    /**
     * Check if this entity is a mob
     * 
     * @return {@code true} when it is a mob
     */
    public boolean isMob();

    /**
     * Check if this entity is an animal (implements the animal interface)
     * 
     * @return {@code true} if animal; {@code false} otherwise
     */
    public boolean isAnimal();

    /**
     * Check if this entity is a player entity
     * 
     * @return {@code true} when it is a player
     */
    public boolean isPlayer();

    public boolean isGolem();

}
