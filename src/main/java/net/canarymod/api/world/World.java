package net.canarymod.api.world;

import net.canarymod.api.EntityTracker;
import net.canarymod.api.GameMode;
import net.canarymod.api.PlayerManager;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.EntityItem;
import net.canarymod.api.entity.living.EntityLiving;
import net.canarymod.api.entity.living.animal.EntityAnimal;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.entity.living.monster.EntityMob;
import net.canarymod.api.entity.vehicle.Boat;
import net.canarymod.api.entity.vehicle.Minecart;
import net.canarymod.api.entity.vehicle.Vehicle;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.TileEntity;
import net.canarymod.api.world.effects.AuxiliarySoundEffect;
import net.canarymod.api.world.effects.Particle;
import net.canarymod.api.world.effects.SoundEffect;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a container for all of the dimensions containing a world
 *
 * @author Chris (damagefilter)
 * @author Jos Kuijpers
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

    /**
     * Set the nano tick time for this world at the specified index.
     *
     * @param tickIndex
     * @param tick
     */
    public void setNanoTick(int tickIndex, long tick);

    /**
     * Get the the nano ticks for this world at the specified index
     *
     * @param tickIndex
     *
     * @return long nano tick time
     */
    public long getNanoTick(int tickIndex);

    /**
     * Get this worlds {@link EntityTracker}
     *
     * @return {@link EntityTracker}
     */
    public EntityTracker getEntityTracker();

    /**
     * Get the type of this world (normal, nether, end)
     *
     * @return
     */
    public DimensionType getType();

    /**
     * Drop an item with this id into the world at the given coords
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
     *
     * @param position
     * @param item
     *
     * @return
     */
    public EntityItem dropItem(Position position, Item item);

    /**
     * Get list of {@link EntityAnimal}s that are tracked for this world
     *
     * @return
     */
    public ArrayList<EntityAnimal> getAnimalList();

    /**
     * Get list of all mobs that are tracked for this world
     *
     * @return
     */
    public ArrayList<EntityMob> getMobList();

    /**
     * gets a list of all EntityLiving that are tracked for this world
     *
     * @return
     */
    public ArrayList<EntityLiving> getEntityLivingList();

    /**
     * get ALL entities that are tracked in this world
     *
     * @return
     */
    public ArrayList<Entity> getTrackedEntities();

    /**
     * Get list of all players currently in this world
     *
     * @return
     */
    public ArrayList<Player> getPlayerList();

    /**
     * Get list of all boats currently in this world
     *
     * @return
     */
    public ArrayList<Boat> getBoatList();

    /**
     * Get list of all {@link Minecart} currently in this world
     *
     * @return {@link ArrayList} of {@link Minecart}
     */
    public ArrayList<Minecart> getMinecartList();

    /**
     * Get list of all {@link Vehicle} currently in this world
     *
     * @return {@link ArrayList} of {@link Vehicle}
     */
    public ArrayList<Vehicle> getVehicleList();

    public ArrayList<EntityItem> getItemList();

    /**
     * Get the block at this coordinates
     *
     * @param x
     * @param y
     * @param z
     *
     * @return
     */
    public Block getBlockAt(int x, int y, int z);

    /**
     * Get the block at this position
     *
     * @param position
     *
     * @return
     */
    public Block getBlockAt(Position position);

    /**
     * Get only block data at this coordinates
     *
     * @param x
     * @param y
     * @param z
     *
     * @return
     */
    public short getDataAt(int x, int y, int z);

    /**
     * Get only block data at this position
     *
     * @param position
     *
     * @return
     */
    public short getDataAt(Position position);

    /**
     * Returns the spawn position of this world
     *
     * @return
     */
    public Location getSpawnLocation();

    /**
     * Set this worlds spawn position
     *
     * @param position
     */
    public void setSpawnLocation(Location position);

    /**
     * Get lightlevel at this point
     *
     * @param x
     * @param y
     * @param z
     *
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
     *         the new light level
     *
     * @return
     */
    public void setLightLevelOnBlockMap(int x, int y, int z, int newLevel);

    /**
     * Set lightlevel at this point in the sky map (the sky light)
     *
     * @param x
     * @param y
     * @param z
     * @param newLevel
     *         the new light level
     *
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
     *
     * @param vector
     * @param block
     */
    public void setBlockAt(Position vector, Block block);

    /**
     * Set the block type given at the position specified
     *
     * @param lastPosition
     * @param type
     */
    public void setBlockAt(Position position, short type);

    /**
     * Set the block type/data given at the position specified
     *
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
     *         the maximum search distance
     *
     * @return Player or null if there is no one within the search radius
     */
    public Player getClosestPlayer(double x, double y, double z, double distance);

    /**
     * Get the player closest to this living entity
     *
     * @param entity
     * @param distance
     *         the maximum search distance
     *
     * @return Player or null if there is no one within the search radius
     */
    public Player getClosestPlayer(Entity entity, int distance);

    /**
     * Return this worlds {@link ChunkProvider}
     *
     * @return
     */
    public ChunkProvider getChunkProvider();

    /**
     * Check if the chunk where that block is, is loaded
     *
     * @param block
     *
     * @return true if chunk is loaded, false otherwise
     */
    public boolean isChunkLoaded(Block block);

    /**
     * Check if the chunk at this position is loaded
     *
     * @param x
     * @param y
     * @param z
     *
     * @return true if a chunk at these coordinates is loaded, false otherwise
     */
    public boolean isChunkLoaded(int x, int y, int z);

    /**
     * Check if the chunk at this position is loaded
     *
     * @param x
     *         the Chunk X (shift Block coords as blockX >> 4)
     * @param z
     *         the Chunk Z (shift Block coords as blockZ >> 4)
     *
     * @return true if a chunk at these coordinates is loaded, false otherwise
     */
    public boolean isChunkLoaded(int x, int z);

    /**
     * Load a chunk
     *
     * @param x
     *         the Chunk X (shift Block coords as blockX >> 4)
     * @param z
     *         the Chunk Z (shift Block coords as blockZ >> 4)
     *
     * @return the loaded Chunk at the given Chunk coordinates
     */
    public Chunk loadChunk(int x, int z);

    /**
     * Load a chunk
     *
     * @param location
     *
     * @return the loaded Chunk at the given location
     */
    public Chunk loadChunk(Location location);

    /**
     * Load a chunk
     *
     * @param vec3d
     *
     * @return the loaded Chunk at the given Position
     */
    public Chunk loadChunk(Position vec3d);

    /**
     * Get a chunk from the chunk provider.
     * If the chunk isn't loaded, this will return null
     *
     * @param x
     *         the Chunk X (shift Block coords as blockX >> 4)
     * @param z
     *         the Chunk Z (shift Block coords as blockZ >> 4)
     *
     * @return the Chunk at the given chunk coordinates
     */
    public Chunk getChunk(int x, int z);

    /**
     * Get a List of Chunks that are currently loaded in this world.
     * It is not advisable to keep hold of these Chunks for a long period of time,
     * as that can cause severe problems with automatically unloaded worlds.
     * The returned List is always fresh.
     *
     * @return a List of Chunk objects.
     */
    public List<Chunk> getLoadedChunks();

    /**
     * Get the biome type at the given block column coordinates, not chunk
     *
     * @param x
     * @param z
     *
     * @return
     */
    public BiomeType getBiomeType(int x, int z);

    /**
     * Get the biome at the given block column coordinates, not chunk
     *
     * @param x
     * @param z
     *
     * @return
     */
    public Biome getBiome(int x, int z);

    /**
     * Set the biome type at the given block column coordinates, not chunk
     *
     * @param x
     * @param z
     * @param biome
     */
    public void setBiome(int x, int z, BiomeType biome);

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
     *
     * @return int heighest Y
     */
    public int getHighestBlockAt(int x, int z);

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
     * @return relative time (between 0 and 24000)
     */
    public long getRelativeTime();

    /**
     * Get raw time for this world (really long number)
     *
     * @return raw time
     */
    public long getRawTime();

    /**
     * Gets the world's Total Time
     *
     * @return total time
     */
    public long getTotalTime();

    /** Spawns the given particle in the world */
    public void spawnParticle(Particle particle);

    /**
     * Plays a {@link SoundEffect} in the world
     *
     * @param effect
     *         the {@link SoundEffect} to play
     */
    public void playSound(SoundEffect effect);

    /**
     * Plays an {@link AuxiliarySoundEffect} in the world
     *
     * @param effect
     *         the {@link AuxiliarySoundEffect} to play
     */
    public void playAUXEffect(AuxiliarySoundEffect effect);

    /**
     * Plays an {@link AuxiliarySoundEffect} at a {@link Player}
     *
     * @param player
     *         the {@link Player} to play an effect at
     * @param effect
     *         the {@link AuxiliarySoundEffect} to play
     */
    public void playAUXEffectAt(Player player, AuxiliarySoundEffect effect);

    /**
     * Get the name of the world for this dimension
     *
     * @return
     */
    public String getName();

    /**
     * Get unique fully qualified name for this world (usually the folder name)
     *
     * @return
     */
    public String getFqName();

    /**
     * Get this worlds player manager
     *
     * @return
     */
    public PlayerManager getPlayerManager();

    /**
     * Check if this block is powered by redstone
     *
     * @param block
     *
     * @return
     */
    public boolean isBlockPowered(Block block);

    /**
     * Check if the block at the given vector position is powered by redstone
     *
     * @param position
     *
     * @return
     */
    public boolean isBlockPowered(Position position);

    /**
     * Check if a block is powered at the given coordinates
     *
     * @param x
     * @param y
     * @param z
     *
     * @return
     */
    public boolean isBlockPowered(int x, int y, int z);

    /**
     * Check if this block is indirectly powered by redstone
     *
     * @param block
     *
     * @return
     */
    public boolean isBlockIndirectlyPowered(Block block);

    /**
     * Check if the block at the given vector position is indirectly powered by redstone
     *
     * @param position
     *
     * @return
     */
    public boolean isBlockIndirectlyPowered(Position position);

    /**
     * Check if a block is indirectly powered at the given coordinates
     *
     * @param x
     * @param y
     * @param z
     *
     * @return
     */
    public boolean isBlockIndirectlyPowered(int x, int y, int z);

    /**
     * Set the thunder state
     *
     * @param thundering
     *         whether it should thunder
     */
    public void setThundering(boolean thundering);

    /**
     * Set the time in ticks (~20/sec) for how long it should thunder
     *
     * @param ticks
     */
    public void setThunderTime(int ticks);

    /**
     * Set weather state (true = raining/snowing, false = sunshine)
     *
     * @param downfall
     */
    public void setRaining(boolean downfall);

    /**
     * Set the time in ticks (~20/sec) for how long it should rain/snow
     *
     * @param ticks
     */
    public void setRainTime(int ticks);

    /**
     * Check if it is currently raining/snowing in this dimension.
     *
     * @return true if it's raining, false otherwise
     */
    public boolean isRaining();

    /**
     * Check if it is thundering in this dimension
     *
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
     *
     * @param position
     */
    public void makeLightningBolt(Position position);

    /**
     * Creates an explosion at the given location and with the given power
     *
     * @param exploder
     *         The entity causing the explosion
     * @param x
     * @param y
     * @param z
     * @param power
     * @param smoke
     *         {@code true} for smoke; {@code false} otherwise
     */
    public void makeExplosion(Entity exploder, double x, double y, double z, float power, boolean smoke);

    /**
     * Creates an explosion at the given location and with the given power
     *
     * @param exploder
     *         The entity causing the explosion
     * @param position
     * @param power
     * @param smoke
     *         {@code true} for smoke; {@code false} otherwise
     */
    public void makeExplosion(Entity exploder, Position position, float power, boolean smoke);

    /**
     * Get the number of ticks remaining until it stops raining.
     *
     * @return
     */
    public int getRainTicks();

    /**
     * Get the number of ticks until it stops thundering
     *
     * @return
     */
    public int getThunderTicks();

    /**
     * Returns the random seed for this world
     *
     * @return
     */
    public long getWorldSeed();

    /**
     * Remove a player from this world
     *
     * @param player
     */
    public void removePlayerFromWorld(Player player);

    /**
     * Add a player to this world
     *
     * @param player
     */
    public void addPlayerToWorld(Player player);

    /**
     * Gets a tile entity from a block including multiple space spanning like DoubleChests
     *
     * @param block
     *
     * @return {@link TileEntity}
     */
    public TileEntity getTileEntity(Block block);

    /**
     * Gets a tile entity in the dimension including multiple space spanning like DoubleChests
     *
     * @param x
     * @param y
     * @param z
     *
     * @return {@link TileEntity}
     */
    public TileEntity getTileEntityAt(int x, int y, int z);

    /**
     * Gets a tile entity in the dimension
     *
     * @param block
     *
     * @return {@link TileEntity}
     */
    public TileEntity getOnlyTileEntity(Block block);

    /**
     * Gets a tile entity in the dimension
     *
     * @param x
     * @param y
     * @param z
     *
     * @return {@link TileEntity}
     */
    public TileEntity getOnlyTileEntityAt(int x, int y, int z);

    /**
     * Get this worlds game mode
     *
     * @return
     */
    public GameMode getGameMode();

    /**
     * Override this worlds game mode
     *
     * @param mode
     */
    public void setGameMode(GameMode mode);

    /** Save this world to disk */
    public void save();

    /**
     * Broadcasts a message to all {@link Player}s in the world
     *
     * @param msg
     *         the message to broadcast
     */
    public void broadcastMessage(String msg);

}
