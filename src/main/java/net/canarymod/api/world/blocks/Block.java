package net.canarymod.api.world.blocks;

import net.canarymod.api.entity.EntityItem;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.packet.BlockChangePacket;
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
     * @return type id
     */
    public short getTypeId();

    /**
     * Set this blocks type
     *
     * @param type
     *         the type id
     */
    public void setTypeId(short type);

    /**
     * Get this blocks data
     *
     * @return data
     */
    public short getData();

    /**
     * Sets the block's data
     *
     * @param data
     *         the data to set
     */
    public void setData(short data);

    /**
     * Gets the block's BlockType
     *
     * @return {@link BlockType}
     */
    public BlockType getType();

    /**
     * Set this blocks type
     *
     * @param type
     *         the {@link BlockType} to set
     */
    public void setType(BlockType type);

    /**
     * Get the current dimension for this block
     *
     * @return world
     */
    public World getWorld();

    /**
     * Set this block dimension
     *
     * @param world
     *         the {@link World} to set
     */
    public void setWorld(World world);

    /**
     * Get the face that was clicked.
     *
     * @return {@link BlockFace}
     */
    public BlockFace getFaceClicked();

    /**
     * Set the clicked BlockFace
     *
     * @param face
     *         the {@link BlockFace}
     */
    public void setFaceClicked(BlockFace face);

    /**
     * Get the block that is next to this block on the given face
     * That means the block north or south east etc from this block
     *
     * @param face
     *         the {@link BlockFace} to check
     *
     * @return the adjacent block
     */
    public Block getFacingBlock(BlockFace face);

    /**
     * Get the block relative from this block
     *
     * @param x
     *         the block x
     * @param y
     *         the block y
     * @param z
     *         the block z
     *
     * @return the relative block
     */
    public Block getRelative(int x, int y, int z);

    /** Send update packet for this block */
    public void update();

    /**
     * Get this blocks position on the X axis
     *
     * @return x coordinate
     */
    public int getX();

    /**
     * Get this blocks position on the Y axis
     *
     * @return y coordinate
     */
    public int getY();

    /**
     * Get this blocks position on the Z axis
     *
     * @return z coordinate
     */
    public int getZ();

    /**
     * Set this blocks position on the X axis
     *
     * @param x
     *         x coordinate
     */
    public void setX(int x);

    /**
     * Set this blocks position on the Y axis
     *
     * @param y
     *         y coordinate
     */
    public void setY(int y);

    /**
     * Set this blocks position on the Z axis
     *
     * @param z
     *         z coordinate
     */
    public void setZ(int z);

    /**
     * Gets this blocks location, with world info
     *
     * @return the {@link Location}
     */
    public Location getLocation();

    /**
     * Gets this blocks position.
     *
     * @return the {@link Position}
     */
    public Position getPosition();

    /**
     * Sets the status of this block.
     * The meaning of a specific number can vary.
     * For detailed information see the javadocs of the respective hook.
     *
     * @param status
     *         the block status
     */
    public void setStatus(byte status);

    /**
     * Gets the status of this block.
     * The meaning of a specific number can vary.
     * For detailed information see the javadocs of the respective hook.
     *
     * @return status
     */
    public byte getStatus();

    /**
     * Checks if the block is air
     *
     * @return {@code true} if air; {@code false} otherwise
     */
    public boolean isAir();

    /**
     * Gets the {@link BlockMaterial} this Block is made of
     *
     * @return the {@link BlockMaterial}
     */
    public BlockMaterial getBlockMaterial();

    /**
     * Gets the Id of the Block as it would be dropped as an {@link Item}
     *
     * @return the id
     */
    public int getIdDropped();

    /**
     * Gets the damage of the Block as it would be dropped as an {@link Item}
     *
     * @return the damage
     */
    public int getDamageDropped();

    /**
     * Gets the amount that would be dropped when the Block is harvested.<br>
     * NOTE: Some blocks are random on the amount so returns may differ for those each call
     *
     * @return the quantity that would be dropped
     */
    public int getQuantityDropped();

    /**
     * Drops the Block into the world as an {@link EntityItem}
     *
     * @param remove
     *         {@code true} to replace the block with air; {@code false} to leave the block as is
     */
    public void dropBlockAsItem(boolean remove);

    /**
     * Gets a {@link TileEntity} at the same location as the Block
     *
     * @return {@link TileEntity} at the location or {@code null} if none
     */
    public TileEntity getTileEntity();

    /**
     * Simulates a right click on the block.<br>
     * Useful for forcing changes to blocks like levers, buttons, doors, etc...
     *
     * @param player
     *         the {@link Player} to use with activation. Can be {@code null}
     *
     * @return {@code true} if the Block responded; {@code false} if not
     */
    public boolean rightClick(Player player);

    /**
     * Sends the update packet to the specified {@link Player}(s) without modifing the world.<br>
     * Useful for displaying things like borders to a specific player(s)
     *
     * @param players
     *         the {@link Player}(s) to send info to
     */
    public void sendUpdateToPlayers(Player... players);

    /**
     * Gets a {@link BlockChangePacket} of the Block that would be used in an update
     *
     * @return the {@link BlockChangePacket} of the Block
     */
    public BlockChangePacket getBlockPacket();
}
