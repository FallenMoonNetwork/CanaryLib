package net.canarymod.api.world.blocks;

import net.canarymod.api.nbt.CompoundTag;
import net.canarymod.api.world.World;

/**
 * TileEntity wrapper
 *
 * @author Chris Ksoll
 * @author Jason Jones
 */
public interface TileEntity {

    /**
     * Returns the Block representation for the TileEntity
     *
     * @return block
     */
    public Block getBlock();

    /**
     * Gets the X location
     *
     * @return x
     */
    public int getX();

    /**
     * Gets the Y location
     *
     * @return y
     */
    public int getY();

    /**
     * Gets the Z
     *
     * @return z
     */
    public int getZ();

    /**
     * Gets the dimension
     *
     * @return dimension
     */
    public World getWorld();

    /** Updates this TileEntity */
    public void update();

    /**
     * Gets the NBTTagCompound for the TileEntity (null if not a tile entity)
     *
     * @return data tag
     */
    public CompoundTag getDataTag();

    /**
     * Gets the MetaTag for the TileEntity (null if not a tile entity)
     *
     * @return meta tag
     */
    public CompoundTag getMetaTag();

    /**
     * Writes to the NBTTagCompound for the TileEntity (null if not a tile entity)
     *
     * @param tag
     *         the {@link CompoundTag} to have entity data written to
     *
     * @return the written {@link CompoundTag}
     */
    public CompoundTag writeToTag(CompoundTag tag);

    /**
     * Reads from NBTTagCompound for the TileEntity (null if not a tile entity)
     *
     * @param tag
     *         the {@link CompoundTag} of data
     */
    public void readFromTag(CompoundTag tag);
}
