package net.canarymod.api.world.blocks;


import net.canarymod.api.world.World;


/**
 * Interface representing a block in minecraft.
 * 
 * @author Chris
 * 
 */
public interface Block {

    /**
     * Get this blocks type
     * 
     * @return
     */
    public short getType();

    /**
     * Set this blocks type
     * 
     * @param type
     */
    public void setType(short type);

    /**
     * Set this blocks type
     * 
     * @param type
     */
    public void setType(int type);

    /**
     * Get this blocks data
     * 
     * @return
     */
    public short getData();

    /**
     * Get the current dimension for this block
     * 
     * @return
     */
    public World getDimension();

    /**
     * Set this block dimension
     * 
     * @param world
     */
    public void setDimension(World world);

    /**
     * Get the face that was clicked.
     * 
     * @return
     */
    public BlockFace getFaceClicked();

    /**
     * Set the clicked BlockFace
     * 
     * @param face
     */
    public void setFaceClicked(BlockFace face);

    /**
     * Send update packet for this block
     */
    public void update();

    /**
     * Get this blocks position on the X axis
     * @return
     */
    public int getX();

    /**
     * Get this blocks position on the Y axis
     * @return
     */
    public int getY();

    /**
     * Get this blocks position on the Z axis
     * @return
     */
    public int getZ();

    /**
     * Set this blocks position on the X axis
     * @param x
     */
    public void setX(int x);

    /**
     * Set this blocks position on the Y axis
     * @param y
     */
    public void setY(int y);

    /**
     * Set this blocks position on the Z axis
     * @param z
     */
    public void setZ(int z);

    /**
     * Sets the status of this block.
     * The meaning of a specific number can vary.
     * For detailed information see the javadocs of the respective hook.
     * @param status
     */
    public void setStatus(byte status);

    /**
     * Gets the status of this block.
     * The meaning of a specific number can vary.
     * For detailed information see the javadocs of the respective hook.
     * @param status
     */
    public byte getStatus();

}
