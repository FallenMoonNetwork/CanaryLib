package net.canarymod.api.world.blocks;

import net.canarymod.api.inventory.Item;

/**
 * Wrap a TileEntityNote etc
 * 
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public interface Jukebox extends ComplexBlock {

    /**
     * Get the disc that is in this jukebox. null if no disc
     * inside
     * 
     * @return the Disc or {@code null} if no disc
     */
    public Item getDisc();

    /**
     * Setting the disc that is played. This may activate the jukebox
     */
    public void setDisc(Item disc);
}
