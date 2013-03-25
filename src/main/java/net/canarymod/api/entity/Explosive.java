package net.canarymod.api.entity;

/**
 * Interface for manipulating Explosive devices
 * 
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public interface Explosive {

    /**
     * Sets whether the explosion can damage the world: blocks
     * 
     * @param canDamage
     *            {@true} if can damage entities; {@code false} if not
     */
    public void setCanDamageWorld(boolean canDamage);

    /**
     * Gets whether the explosion can damage the world
     * 
     * @return {@true} if can damage world; {@code false} if not
     */
    public boolean canDamageWorld();

    /**
     * Sets whether the explosion can damage entities
     * 
     * @param canDamage
     *            {@true} if can damage entities; {@code false} if not
     */
    public void setCanDamageEntities(boolean canDamage);

    /**
     * Gets whether the explosion can damage entities
     * 
     * @return {@true} if can damage entities; {@code false} if not
     */
    public boolean canDamageEntities();

    /**
     * Gets the power level of the explosion
     * 
     * @return power level
     */
    public float getPower();

    /**
     * Sets the power level of the explosion
     * 
     * @param power
     *            the power level of the explosion
     */
    public void setPower(float power);

    /**
     * Detonates this entity
     */
    public void detonate();
}
