package net.canarymod.api.world;


import java.util.ArrayList;

import net.canarymod.api.EntityTracker;
import net.canarymod.api.Particle;
import net.canarymod.api.PlayerManager;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.EntityItem;
import net.canarymod.api.entity.living.animal.EntityAnimal;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.entity.living.monster.EntityMob;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.ComplexBlock;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Position;


/**
 * This is a container for all of the dimensions containing a world
 *
 * @author Chris Ksoll
 * @author Jos Kuijpers
 *
 */
public interface World {

    public enum Difficulty {
        PEACEFUL(0), EASY(1), NORMAL(2), HARD(3);

        private int id;

        Difficulty(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public static Difficulty fromId(int id) {
            switch (id) {
                case 0:
                    return PEACEFUL;

                case 1:
                    return EASY;

                case 3:
                    return HARD;

                default:
                    return NORMAL;
            }
        }
    }


    public enum GameMode {
        SURVIVAL(0), CREATIVE(1), ADVENTURE(2);

        private int id;

        GameMode(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public static GameMode fromId(int id) {
            switch (id) {
                case 1:
                    return CREATIVE;

                case 2:
                    return ADVENTURE;

                default:
                    return SURVIVAL;
            }
        }
    }

    public void setNanoTick(int tickIndex, long tick);

    /**
     * Set a nano tick for this dimension
     * @param dimension
     * @param tickIndex
     * @return
     */
    public long getNanoTick(int tickIndex);

    /**
     * Enable or disable this world
     *
     * By default, a newly created world is enabled
     *
     * @param enabled
     */
    public void setEnabled(boolean enabled);

    /**
     * Whether this world is enabled. A disabled world can't be accessed by
     * anyone, and might not be loaded at all.
     *
     * @return
     */
    public boolean isEnabled();

    /**
     * Returns all players in this world
     * @return
     */
    public ArrayList<Player> getPlayers();

    /**
     * Whether the specified player is allowed to enter this world
     *
     * @param player
     * @return true if the player is allowed
     */
    public boolean canEnterWorld(Player player);

    /**
     * Whether the specified player is allowed to leave this world
     *
     * @param player
     * @return true if the player is allowed
     */
    public boolean canLeaveWorld(Player player);

    /**
     * Returns an array of all entity trackers contained in this world.
     * Usually 3 trackers, one for each dimension
     * @return
     */
    public EntityTracker getEntityTracker();

    /**
     * Set this dimensions entity tracker
     * @param tracker
     */
    public void setEntityTracker(EntityTracker tracker);

    /**
     * Get the type of this dimension (normal, nether, end)
     * @return
     */
    public DimensionType getType();

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
     * Drop item at the given vector
     * @param position
     * @param item
     * @return
     */
    public EntityItem dropItem(Position position, Item item);

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
     * Get the block at this coordinates
     *
     * @param x
     * @param y
     * @param z
     * @return
     */
    public Block getBlockAt(int x, int y, int z);

    /**
     * Get the block at this position
     * @param position
     * @return
     */
    public Block getBlockAt(Position position);

    /**
     * Get only block data at this coordinates
     *
     * @param x
     * @param y
     * @param z
     * @return
     */
    public short getDataAt(int x, int y, int z);

    /**
     * Get only block data at this position
     * @param position
     * @return
     */
    public short getDataAt(Position position);

    /**
     * Returns the spawn position of this world
     * @return
     */
    public Location getSpawnLocation();

    /**
     * Set this worlds spawn position
     * @param position
     */
    public void setSpawnLocation(Location position);

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
     * Set lightlevel at this point in the block map (the torch light)
     *
     * @param x
     * @param y
     * @param z
     * @param newLevel
     *            the new light level
     * @return
     */
    public void setLightLevelOnBlockMap(int x, int y, int z, int newLevel);

    /**
     * Set lightlevel at this point in the sky map (the sky light)
     * @param x
     * @param y
     * @param z
     * @param newLevel
     *            the new light level
     * @return
     */
    public void setLightLevelOnSkyMap(int x, int y, int z, int newLevel);

    /**
     * Set this block.
     *
     * @param block
     */
    public void setBlock(Block block);

    /**
     * Set raw block at the coordinates specified.
     *
     * @param x
     * @param y
     * @param z
     * @param type
     */
    public void setBlockAt(int x, int y, int z, short type);

    /**
     * Set a block at the given position
     * @param vector
     * @param block
     */
    public void setBlockAt(Position vector, Block block);

    /**
     * Set the block type given at the position specified
     * @param lastPosition
     * @param type
     */
    public void setBlockAt(Position position, short type);

    /**
     * Set the block type/data given at the position specified
     * @param position
     * @param type
     * @param data
     */
    public void setBlockAt(Position position, short type, short data);

    /**
     * Set raw block with data at the position specified.
     *
     * @param x
     * @param y
     * @param z
     * @param type
     * @param data
     */
    public void setBlockAt(int x, int y, int z, short type, short data);

    /**
     * Set block data at this position
     *
     * @param data
     * @param x
     * @param y
     * @param z
     */
    public void setDataAt(int x, int y, int z, short data);

    /**
     * Update the world at this position
     *
     * @param x
     * @param y
     * @param z
     */
    public void markBlockNeedsUpdate(int x, int y, int z);

    /**
     * Get the player closest to this coordinate
     *
     * @param x
     * @param y
     * @param z
     * @param distance
     *            the maximum search distance
     * @return Player or null if there is no one within the search radius
     */
    public Player getClosestPlayer(double x, double y, double z, double distance);

    /**
     * Get the player closest to this living entity
     *
     * @param entity
     * @param distance
     *            the maximum search distance
     * @return Player or null if there is no one within the search radius
     */
    public Player getClosestPlayer(Entity entity, int distance);

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
    public Chunk loadChunk(Position vec3d);

    /**
     * Get a chunk from the chunk provider.
     * If the chunk isn't loaded, this will return null
     * @param x
     * @param z
     * @return
     */
    public Chunk getChunk(int x, int z);

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
     * Set this worlds time. (0 - 24000)
     *
     * @param time
     */
    public void setTime(long time);

    /**
     * Get relative time (0 - 24000)
     *
     * @return
     */
    public long getRelativeTime();

    /**
     * Get raw time for this world (really long number)
     *
     * @return
     */
    public long getRawTime();

    /**
     * Spawns the given particle in the world
     */
    public void spawnParticle(Particle particle);

    /**
     * Get the name of the world for this dimension
     * @return
     */
    public String getName();

    /**
     * Get unique fully qualified name for this world (usually the folder name)
     * @return
     */
    public String getFqName();

    /**
     * Get this worlds player manager
     * @return
     */
    public PlayerManager getPlayerManager();

    /**
     * Set a new PlayerManager for this Dimension
     * @param pm
     */
    public void setPlayerManager(PlayerManager pm);

    /**
     * Create a new Item that can be used in this world.
     * @param itemType
     * @return
     */
    public Item createItem(ItemType itemType);

    /**
     * Create a new item with the given data.
     * @param itemType
     * @param amount
     * @param data
     * @return
     */
    public Item createItem(ItemType itemType, int amount, int data);

    /**
     * Create a new item with the given data
     * @param itemId
     * @param amount
     * @param data
     * @return
     */
    public Item createItem(int itemId, int amount, int data);

    /**
     * Check if this block is powered by redstone
     * @param block
     * @return
     */
    public boolean isBlockPowered(Block block);

    /**
     * Check if the block at the given vector position is powered by redstone
     * @param position
     * @return
     */
    public boolean isBlockPowered(Position position);

    /**
     * Check if a block is powered at the given coordinates
     * @param x
     * @param y
     * @param z
     * @return
     */
    public boolean isBlockPowered(int x, int y, int z);

    /**
     * Check if this block is indirectly powered by redstone
     * @param block
     * @return
     */
    public boolean isBlockIndirectlyPowered(Block block);

    /**
     * Check if the block at the given vector position is indirectly powered by redstone
     * @param position
     * @return
     */
    public boolean isBlockIndirectlyPowered(Position position);

    /**
     * Check if a block is indirectly powered at the given coordinates
     * @param x
     * @param y
     * @param z
     * @return
     */
    public boolean isBlockIndirectlyPowered(int x, int y, int z);

    /**
     * Set the thunder state
     * @param thundering whether it should thunder
     */
    public void setThundering(boolean thundering);

    /**
     * Set the time in ticks (~20/sec) for how long it should thunder
     * @param ticks
     */
    public void setThunderTime(int ticks);

    /**
     * Set weather state (true = raining/snowing, false = sunshine)
     * @param downfall
     */
    public void setRaining(boolean downfall);

    /**
     * Set the time in ticks (~20/sec) for how long it should rain/snow
     * @param ticks
     */
    public void setRainTime(int ticks);

    /**
     * Check if it is currently raining/snowing in this dimension.
     * @return true if it's raining, false otherwise
     */
    public boolean isRaining();

    /**
     * Check if it is thundering in this dimension
     * @return true if it is thundering, false otherwise
     */
    public boolean isThundering();

    /**
     * Creates a lightning bolt at the given coordinates
     *
     * @param x
     * @param y
     * @param z
     */
    public void makeLightningBolt(int x, int y, int z);

    /**
     * Creates a lightning bolt at the given position
     * @param position
     */
    public void makeLightningBolt(Position position);

    /**
     * Creates an explosion at the given location and with the given power
     *
     * @param exploder The entity causing the explosion
     * @param x
     * @param y
     * @param z
     * @param power
     * @param damageBlocks true to damage blocks, false for just the boom effect
     */
    public void makeExplosion(Entity exploder, double x, double y, double z, float power, boolean damageBlocks);

    /**
     * Creates an explosion at the given location and with the given power
     *
     * @param exploder The entity causing the explosion
     * @param position
     * @param power
     * @param damageBlocks true to damage blocks, false for just the boom effect
     */
    public void makeExplosion(Entity exploder, Position position, float power, boolean damageBlocks);

    /**
     * Get the number of ticks remaining until it stops raining.
     * @return
     */
    public int getRainTicks();

    /**
     * Get the number of ticks until it stops thundering
     * @return
     */
    public int getThunderTicks();

    /**
     * Returns the random seed for this world
     * @return
     */
    public long getWorldSeed();

    /**
     * Remove a player from this world
     * @param player
     */
    public void removePlayerFromWorld(Player player);

    /**
     * Add a player to this world
     * @param player
     */
    public void addPlayerToWorld(Player player);

    /**
     * Gets a complex block from a block including multiple space spanning like DoubleChests
     * @param block
     * @return complexblock
     */
    public ComplexBlock getComplexBlock(Block block);

    /**
     * Gets a complex block in the dimension including multiple space spanning like DoubleChests
     * @param x
     * @param y
     * @param z
     * @return complexblock
     */
    public ComplexBlock getComplexBlockAt(int x, int y, int z);

    /**
     * Gets a complex block in the dimension
     * @param block
     * @return complexblock
     */
    public ComplexBlock getOnlyComplexBlock(Block block);

    /**
     * Gets a complex block in the dimension
     * @param x
     * @param y
     * @param z
     * @return complexblock
     */
    public ComplexBlock getOnlyComplexBlockAt(int x, int y, int z);

}
