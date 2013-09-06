package net.canarymod.api.world;

import java.util.List;
import java.util.Map;

import net.canarymod.api.entity.Entity;
import net.canarymod.api.world.blocks.TileEntity;
import net.canarymod.api.world.position.Position;

/**
 * Handles Chunks
 *
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 * @author Jos Kuijpers
 */
public interface Chunk {

    /**
     * Get the Chunks X coordinate
     *
     * @return x coordinate
     */
    public int getX();

    /**
     * Get the Chunks Z coordinate
     *
     * @return z coordinate
     */
    public int getZ();

    /**
     * Get type of block at this position in this chunk
     *
     * @param x
     *         the block x
     * @param y
     *         the block y
     * @param z
     *         the block z
     *
     * @return the block type id
     */
    public int getBlockTypeAt(int x, int y, int z);

    /**
     * Get type of block at this position in this chunk
     *
     * @param x
     *         the block x
     * @param y
     *         the block y
     * @param z
     *         the block z
     * @param type
     *         the block type id
     */
    public void setBlockTypeAt(int x, int y, int z, int type);

    /**
     * Get data of block at this position in this chunk
     *
     * @param x
     *         the block x
     * @param y
     *         the block y
     * @param z
     *         the block z
     *
     * @return the block data
     */
    public int getBlockDataAt(int x, int y, int z);

    /**
     * Get data of block at this position in this chunk
     *
     * @param x
     *         the block x
     * @param y
     *         the block y
     * @param z
     *         the block z
     * @param data
     *         the block data
     */
    public void setBlockDataAt(int x, int y, int z, int data);

    /**
     * Get the max height of any chunk
     *
     * @return max height
     */
    public int getMaxHeigth();

    /**
     * Is that chunk loaded?
     *
     * @return {@code true} if loaded; {@code false} if not
     */
    public boolean isLoaded();

    /**
     * Get this chunks world (dimension)
     *
     * @return world
     */
    public World getDimension();

    /**
     * Gets this chunk's biome data as a BiomeType array
     *
     * @return biomedata
     */
    public BiomeType[] getBiomeData();

    /**
     * Gets this chunk's biome data as a byte array
     *
     * @return biomedata
     */
    public byte[] getBiomeByteData();

    /**
     * Sets this chunks biome data from a BiomeType array
     *
     * @param data
     *         the biome data to set
     */
    public void setBiomeData(BiomeType[] data);

    /**
     * Sets this chunk's biome data from a byte array
     *
     * @param data
     *         the biome data to set
     */
    public void setBiomeData(byte[] data);

    /**
     * The block coordinates for the column to get the biome for.<br>
     * <b>NOTE:</b> Here we want a number between 0 and 16 for both x and z
     *
     * @param x
     *         x plane coordinate
     * @param z
     *         z plane coordinate
     *
     * @return the { @link Biome }.
     */
    public Biome getBiome(int x, int z);

    /**
     * Gets a map of ChunkPosition(Position),TileEntity(TileEntity) within the chunk
     *
     * @return TileEntityMap
     */
    public Map<Position, TileEntity> getTileEntityMap();

    /**
     * Gets whether the Chunk contains Entities
     *
     * @return {@code true} if contains entities, {@code false} otherwise
     */
    public boolean hasEntities();

    /**
     * Array of Lists containing the entities in the Chunk. Each List represents a 16 block subchunk.
     *
     * @return List array of Entities
     */
    public List<Entity>[] getEntityLists();

    /**
     * Height Map for the chunk
     *
     * @return height map
     */
    public int[] getHeightMap();

    /**
     * Similar to heightMap, that tracks how far down precipitation can fall.
     *
     * @return precipitation height map
     */
    public int[] getPrecipitationHeightMap();

    /**
     * The time according to WorldTime when this chunk was last saved
     *
     * @return last save time
     */
    public long getLastSaveTime();

    /**
     * Boolean value indicating if the terrain is populated
     *
     * @return {@code true} if populated; {@code false} otherwise
     */
    public boolean isTerrainPopulated();

    /**
     * Gets if the chunk has been modified and needs to be updated internally
     *
     * @return {@code true} if modified; {@code false} otherwise
     */
    public boolean isModified();

    /** Generates the skylight map for this Chunk. */
    public void generateSkyLightMap();

    /**
     * Updates the skylight map for this chunk
     *
     * @param force
     *         Force light update
     */
    public void updateSkyLightMap(boolean force);

    /**
     * Re-calculates the light at the given coordinates within this chunk.
     * Coordinates are world coordinates
     *
     * @param x
     *         the X coordinate
     * @param y
     *         the Y coordinate
     * @param z
     *         the Z coordinate
     */
    public void relightBlock(int x, int y, int z);
}
