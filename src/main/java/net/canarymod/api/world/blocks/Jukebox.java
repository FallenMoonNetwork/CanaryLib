package net.canarymod.api.world.blocks;


/**
 * Wrap a TileEntityNote etc
 * 
 * @author Chris
 * 
 */
public interface Jukebox extends ComplexBlock {

    /**
     * Get the ID of the disc that is in this jukebox. 0 if there's no disc
     * inside
     * 
     * @return
     */
    public int getDiscId();

    /**
     * Setting the id that is played. This may activate the jukebox
     */
    public void setDiscId(int discid);
}
