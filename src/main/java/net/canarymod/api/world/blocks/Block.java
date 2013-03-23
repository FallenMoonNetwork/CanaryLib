package net.canarymod.api.world.blocks;

import net.canarymod.api.world.World;

/**
 * Class representing a block in minecraft.
 * 
 * @author Chris
 * 
 */
public class Block {

    private short type;
    private short data;
    private World world;
    private BlockFace lastClickedFace;
    private byte status;

    int x, y, z;

    /**
     * Get this blocks type
     * 
     * @return
     */
    public short getType() {
        return type;
    }

    /**
     * Set this blocks type
     * 
     * @param type
     */
    public void setType(short type) {
        this.type = type;
    }

    /**
     * Set this blocks type
     * 
     * @param type
     */
    public void setType(int type) {
        this.type = (short) type;
    }

    /**
     * Get this blocks data
     * 
     * @return
     */
    public short getData() {
        return data;
    }

    /**
     * Get the current dimension for this block
     * 
     * @return
     */
    public World getDimension() {
        return world;
    }

    /**
     * Set this block dimension
     * 
     * @param world
     */
    public void setDimension(World world) {
        this.world = world;
    }

    /**
     * Get the face that was clicked.
     * 
     * @return
     */
    public BlockFace getFaceClicked() {
        return lastClickedFace;
    }

    /**
     * Set the clicked BlockFace
     * 
     * @param face
     */
    public void setFaceClicked(BlockFace face) {
        this.lastClickedFace = face;
    }

    /**
     * Send update packet for this block
     */
    public void update() {
        world.setBlock(this);
    }

    /**
     * Get this blocks position on the X axis
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Get this blocks position on the Y axis
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Get this blocks position on the Z axis
     * @return
     */
    public int getZ() {
        return z;
    }

    /**
     * Set this blocks position on the X axis
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set this blocks position on the Y axis
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Set this blocks position on the Z axis
     * @param z
     */
    public void setZ(int z) {
        this.z = z;
    }

    /**
     * Sets the status of this block.
     * The meaning of a specific number can vary.
     * For detailed information see the javadocs of the respective hook.
     * @param status
     */
    public void setStatus(byte status) {
        this.status = status;
    }

    /**
     * Gets the status of this block.
     * The meaning of a specific number can vary.
     * For detailed information see the javadocs of the respective hook.
     * @param status
     */
    public byte getStatus() {
        return status;
    }

}
