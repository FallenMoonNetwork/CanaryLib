package net.canarymod.api.packet;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.position.Position;

/**
 * Packet #53 BlockChange
 *
 * @author Jason (darkdiplomat)
 */
public interface BlockChangePacket extends Packet {

    /**
     * Gets the X coordinate
     *
     * @return X coordinate
     */
    public int getX();

    /**
     * Sets the X coordinate
     *
     * @param x
     *         the X coordinate
     */
    public void setX(int x);

    /**
     * Gets the Y coordinate
     *
     * @return Y coordinate
     */
    public int getY();

    /**
     * Sets the Y coordinate
     *
     * @param y
     *         the Y coordinate
     */
    public void setY(int y);

    /**
     * Gets the Z coordinate
     *
     * @return Z coordinate
     */
    public int getZ();

    /**
     * Sets the Z coordinate
     *
     * @param z
     *         the Z coordinate
     */
    public void setZ(int z);

    /**
     * Gets the {@link Position}
     *
     * @return the {@link Position}
     */
    public Position getPosition();

    /**
     * Sets the {@link Position}
     *
     * @param position
     *         the {@link Position} to set
     */
    public void setPosition(Position position);

    /**
     * Gets the {@link BlockType}
     *
     * @return the {@link BlockType}
     */
    public BlockType getType();

    /**
     * Sets the {@link BlockType} (id and data)
     *
     * @param type
     *         the {@link BlockType} to set
     */
    public void setType(BlockType type);

    /**
     * Gets the Type ID
     *
     * @return the Type ID
     */
    public int getTypeId();

    /**
     * Sets the Type Id
     *
     * @param id
     *         the Type ID
     */
    public void setTypeId(int id);

    /**
     * Gets the Block Data value
     *
     * @return the data
     */
    public int getData();

    /**
     * Sets the Block data value
     *
     * @param data
     *         the Block data
     */
    public void setData(int data);

    /**
     * Gets the {@link Block}
     *
     * @return the {@link Block}
     */
    public Block getBlock();

    /**
     * Sets the {@link Block}
     *
     * @param block
     *         the {@link Block}
     */
    public void setBlock(Block block);
}
