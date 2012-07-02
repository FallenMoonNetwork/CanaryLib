package net.canarymod.api.entity;

public interface EntityThrowable extends Entity{
    /**
     * @author Logan Kenwright
     *
     *
     */
    /**
     * Returns the type of this throwable.
     * 
     */
    public ThrowableType getType();
    
    /**
     * Gets the source of the throwable.
     */
    public EntityLiving getSource();

}
