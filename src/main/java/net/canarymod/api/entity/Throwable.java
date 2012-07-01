package net.canarymod.api.entity;

public interface Throwable extends Entity{
    /**
     * @author Logan Kenwright
     *
     */
    public void getType();
    /**
     * Returns the type of this throwable.
     * 
     */
    public void getSource(net.canarymod.api.entity.EntityLiving source);
    /**
     * Gets the source of the throwable.
     */

}
