package net.canarymod.api.world.blocks;



import net.canarymod.api.world.World;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Position;


/**
 * Interface representing a block in minecraft.
 *
 * @author Chris (damagefitler)
 * @author Jason (darkdiplomat)
 */
public interface Block {

    /**
     * Get this blocks type
     *
     * @return
     */
    public short getTypeID();

    /**
     * Set this blocks type
     *
     * @param type
     */
    public void setTypeID(short type);

    /**
     * Get this blocks data
     *
     * @return
     */
    public short getData();

    /**
     * Sets the block's data
     *
     * @param data
     */
    public void setData(short data);

    /**
     * Gets the block's BlockType
     *
     * @return
     */
    public BlockType getType();

    /**
     * Set this blocks type
     *
     * @param type
     */
    public void setType(BlockType type);

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
     * Get the block that is next to this block on the given face
     * That means the block north or south east etc from this block
     * @param face
     * @return
     */
    public Block getFacingBlock(BlockFace face);

    /**
     * Get the block relative from this block
     * @param x
     * @param y
     * @param z
     * @return
     */
    public Block getRelative(int x, int y, int z);

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
     * Gets this blocks location, with world info
     * @return
     */
    public Location getLocation();

    /**
     * Gets this blocks position.
     * @return
     */
    public Position getPosition();

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
