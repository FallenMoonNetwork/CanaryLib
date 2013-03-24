package net.canarymod.api.entity;


/**
 * TNTPrimed wrapper interface
 * @author Jason Jones
 */
public interface TNTPrimed extends Entity, Explosive {
    
    /**
     * gets the 'length' of the fuse
     * @return fuse
     */
    public int getFuse();
    
    /**
     * sets the 'length' of the fuse
     * @param fuse
     */
    public void setFuse(int fuse);
    
    /**
     * Immediately detonates this tnt
     */
    public void detonate();

}
