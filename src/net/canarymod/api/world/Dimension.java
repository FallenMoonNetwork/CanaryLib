package net.canarymod.api.world;

import java.util.ArrayList;

import net.canarymod.api.EntityTracker;
import net.canarymod.api.entity.EntityAnimal;
import net.canarymod.api.entity.EntityItem;
import net.canarymod.api.entity.EntityLiving;
import net.canarymod.api.entity.EntityMob;
import net.canarymod.api.entity.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Vector3D;

/**
 * In MinecraftServer parlance this is a World wrapper. In fact, this resembles
 * a dimension (nether, normal, end) of a world It contains methods to work with
 * a worlds dimension.
 * 
 * @author Chris
 * @implementation This needs an {@link ChunkProviderServer} to serve chunk
 *                 updates etc
 * 
 */
public interface Dimension {

    public enum Type {
        NORMAL(0),
        NETHER(-1),
        END(1);
        
        private int id;
        
        Type(int id) {
            this.id = id;
        }
        
        public int getId() {
            return id;
        }
        
        public static Type fromId(int id) {
            switch(id) {
            case -1:
                return NETHER;
            case 0:
                return NORMAL;
            case 1:
                return END;
            default:
                return NORMAL;
            }
        }
    }
    
    /**
     * Get this dimensions entity tracker
     * @return
     */
    public EntityTracker getEntityTracker();
    
    /**
     * Set this dimensions entity tracker
     * @param tracker
     */
    public void setEntityTracker(EntityTracker tracker);
    
    
    /**
     * Get the world of this dimension
     * 
     * @return world
     */
    public World getWorld();
    
    /**
     * Get the type of this dimension (normal, nether, end)
     * @return
     */
    public Type getType();

    /**
     * Drop an item with this id into the dimension at the given coords
     * 
     * @param x
     * @param y
     * @param z
     * @param itemId
     */
    public EntityItem dropItem(int x, int y, int z, int itemId, int amount, int damage);

    /**
     * Drop the given item into the world
     * 
     * @param x
     * @param y
     * @param z
     * @param item
     */
    public EntityItem dropItem(int x, int y, int z, Item item);

    /**
     * Get list of entities that are {@link EntityAnimal}
     * 
     * @return
     */
    public ArrayList<EntityAnimal> getAnimalList();

    /**
     * Get list of all mobs currently in this world!
     * 
     * @return
     */
    public ArrayList<EntityMob> getMobList();

    /**
     * get list of all players currently in this world
     * 
     * @return
     */
    public ArrayList<Player> getPlayerList();

    /**
     * Get the block at this position
     * 
     * @param x
     * @param y
     * @param z
     * @return
     */
    public Block getBlockAt(int x, int y, int z);

    /**
     * Get only block data at this position
     * 
     * @param x
     * @param y
     * @param z
     * @return
     */
    public byte getDataAt(int x, int y, int z);

    /**
     * Get lightlevel at this point
     * 
     * @param x
     * @param y
     * @param z
     * @return
     */
    public int getLightLevelAt(int x, int y, int z);

    /**
     * Set lightlevel at this point
     * 
     * @param x
     * @param y
     * @param z
     * @param newLevel
     *            the new light level
     * @return
     */
    public int setLightLevelAt(int x, int y, int z, int newLevel);

    /**
     * Set this block.
     * 
     * @param block
     */
    public void setBlock(Block block);

    /**
     * Set raw block at the position specified.
     * 
     * @param x
     * @param y
     * @param z
     * @param type
     */
    public void setBlockAt(int x, int y, int z, short type);
    
    /**
     * Set a block at the given coordinates in the Vector3D
     * @param vector
     * @param block
     */
    public void setBlockAt(Vector3D vector, Block block);

    /**
     * Set raw block with data at the position specified.
     * 
     * @param x
     * @param y
     * @param z
     * @param type
     * @param data
     */
    public void setBlockAt(int x, int y, int z, short type, byte data);

    /**
     * Set block data at this position
     * 
     * @param data
     * @param x
     * @param y
     * @param z
     */
    public void setDataAt(byte data, int x, int y, int z);

    /**
     * Update the world at this position
     * 
     * @param x
     * @param y
     * @param z
     */
    public void updateBlockAt(int x, int y, int z);

    /**
     * Get the player closest to this coordinate
     * 
     * @param x
     * @param y
     * @param z
     * @param distance
     *            the maximum search distance
     * @return IPlayer or null if there is no one within the search radius
     */
    public Player getClosestPlayer(int x, int y, int z, int distance);

    /**
     * Get the player closest to this living entity
     * 
     * @param entity
     * @param distance
     *            the maximum search distance
     * @return IPlayer or null if there is no one within the search radius
     */
    public Player getClosestPlayer(EntityLiving entity, int distance);

    /**
     * Return this worlds {@link ChunkProviderServer}
     * 
     * @return
     */
    public ChunkProviderServer getChunkProvider();

    /**
     * Check if the chunk where that block is, is loaded
     * 
     * @param block
     * @return true if chunk is laoded, false otherwise
     */
    public boolean isChunkLoaded(Block block);

    /**
     * Check if the chunk at this position is loaded
     * 
     * @param x
     * @param y
     * @param z
     * @return
     */
    public boolean isChunkLoaded(int x, int y, int z);

    /**
     * Check if the chunk at this position is loaded
     * 
     * @param x
     * @param z
     * @return
     */
    public boolean isChunkLoaded(int x, int z);

    /**
     * Load a chunk
     * 
     * @param x
     * @param z
     * @return
     */
    public Chunk loadChunk(int x, int z);

    /**
     * Load a chunk
     * 
     * @param location
     * @return
     */
    public Chunk loadChunk(Location location);

    /**
     * Load a chunk
     * 
     * @param vec3d
     * @return
     */
    public Chunk loadChunk(Vector3D vec3d);

    /**
     * Get Dimensions height
     * 
     * @return
     */
    public int getHeight();

    /**
     * Get the first block that sees the sky (highest block on y) at the given
     * x/z axis height
     * 
     * @param x
     * @param z
     * @return int heighest Y
     */
    public int getYHeighestBlockAt(int x, int z);

    /**
     * Plays a note at the given position in the world
     * 
     * @param x
     * @param y
     * @param z
     * @param instrument
     * @param notePitch
     */
    public void playNoteAt(int x, int y, int z, int instrument, byte notePitch);

    /**
     * Set this worlds time
     * 
     * @param time
     */
    public void setTime(long time);

    /**
     * Get relative (shorter) time
     * 
     * @return
     */
    public long getRelativeTime();

    /**
     * Get raw time
     * 
     * @return
     */
    public long getRawTime();
    
    /**
     * Get the name of the world for this dimension
     * @return
     */
    public String getName();

    //TODO: Add weather controlling and thunder/lightning strikes
}
