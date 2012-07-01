package net.canarymod.api.entity;

public interface Throwable extends Entity{
    /**
     * @author Logan Kenwright
     *
     */
    public String getType();
    /**
     * Returns the type of this throwable.
     * 
     */
    public CanaryEntityLiving getSource();
    /**
     * Gets the source of the throwable.
     */

}
