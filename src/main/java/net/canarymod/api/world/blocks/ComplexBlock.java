package net.canarymod.api.world.blocks;


import net.canarymod.api.nbt.CompoundTag;
import net.canarymod.api.world.World;


/**
 * TileEntity wrapper
 * 
 * @author Chris Ksoll
 * @author Jason Jones
 * 
 */
public interface ComplexBlock {

    /**
     * Returns the Block representation for this complex block
     * @return block
     */
    public Block getBlock();
    
    /**
     * Gets the X location
     * @return x
     */
    public int getX();
    
    /**
     * Gets the Y location
     * @return y
     */
    public int getY();
    
    /**
     * Gets the Z 
     * @return
     */
    public int getZ();
    
    /**
     * Gets the dimension
     * @return dimension
     */
    public World getWorld();
    
    /**
     * Updates this ComplexBlock
     */
    public void update();

    /**
     * Gets the NBTTagCompound for the TileEntity (null if not a tile entity)
     * 
     * @return
     */
    public CompoundTag getDataTag();

    /**
     * Gets the MetaTag for the TileEntity (null if not a tile entity)
     * 
     * @return
     */
    public CompoundTag getMetaTag();

    /**
     * Writes to the NBTTagCompound for the TileEntity (null if not a tile entity)
     * 
     * @return
     */
    public CompoundTag writeToTag(CompoundTag tag);

    /**
     * Reads from NBTTagCompound for the TileEntity (null if not a tile entity)
     * 
     * @return
     */
    public void readFromTag(CompoundTag tag);
}
