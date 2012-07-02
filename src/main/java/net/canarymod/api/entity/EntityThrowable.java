package net.canarymod.api.entity;

public interface EntityThrowable extends Entity{
    /**
     * @author Logan Kenwright
     *
     */
    public ThrowableType getType();
    /**
     * Returns the type of this throwable.
     * 
     */
    public EntityLiving getSource();
    /**
     * Gets the source of the throwable.
     */

}
