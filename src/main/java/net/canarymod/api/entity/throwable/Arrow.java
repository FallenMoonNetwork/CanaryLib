package net.canarymod.api.entity.throwable;

import net.canarymod.api.entity.Entity;

/**
 * Arrow wrapper
 * 
 * @author Jason (darkdiplomat)
 */
public interface Arrow extends Entity, Throwable {

    /**
     * Returns true if this Arrow can be retrieved by a Player
     * 
     * @return {@code true} if it can be retrieved; {@code false} if not
     */
    public boolean canPickUp();

    /**
     * Sets whether this Arrow can be retrieved by a Player
     * 
     * @param canPickUp
     *            {@code true} if it can be retrieved; {@code false} if not
     */
    public void setCanPickUp(boolean canPickUp);

    /**
     * Gets the Entity that fired this Arrow
     * 
     * @return The Entity the fired the Arrow
     */
    public Entity getOwner();

    /**
     * Gets the amount of damage the Arrow can do
     * 
     * @return the damage, default of 2.0
     */
    public double getDamage();

    /**
     * Sets the amount of damage the Arrow can do
     * 
     * @param damage
     *            the amount of damage
     */
    public void setDamage(double damage);

    /**
     * Gets if this Arrow is to cause Critical damage or not
     * 
     * @return {@code true} if critical; {@code false} if not
     */
    public boolean isCritical();

    /**
     * Sets whether this Arrow will be critical or not
     * 
     * @param critical
     */
    public void setIsCritical(boolean critical);

}
