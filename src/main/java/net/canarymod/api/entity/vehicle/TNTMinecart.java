package net.canarymod.api.entity.vehicle;


import net.canarymod.api.entity.Explosive;


/**
 * TrinitrotolueneMinecart wrapper
 * 
 * @author Jason (darkdiplomat)
 */
public interface TNTMinecart extends Minecart, Explosive {
    
    /**
     * Detonates this TNTMinecart
     */
    public void detonate();
    
    /**
     * Gets the fuse length of this TNTMinecart
     * 
     * @return fuse
     */
    public int getFuse();
    
    /**
     * Sets the fuse length of this TNTMinecart
     * 
     * @param fuse
     */
    public void setFuse(int fuse);
    
    /**
     * Increases the fuse length of this TNTMinecart
     * 
     * @param increase
     */
    public void increaseFuse(int increase);
    
    /**
     * Decreases the fuse length of this TNTMinecart
     * 
     * @param decrease
     */
    public void decreaseFuse(int decrease);

}
