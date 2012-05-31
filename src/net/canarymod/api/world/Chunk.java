package net.canarymod.api.world;

/**
 * Handles Chunks
 * 
 * @author Chris Ksoll
 * @author Jos Kuijpers
 * @author Jason Jones
 * 
 */
public interface Chunk {

    /**
     * Get the Chunks X ordinate
     * 
     * @return
     */
    public int getX();

    /**
     * Get the Chunks Z ordinate
     * 
     * @return
     */
    public int getZ();

    /**
     * Get type of block at this position in this chunk
     * 
     * @param x
     * @param y
     * @param z
     * @return
     */
    public int getBlockTypeAt(int x, int y, int z);

    /**
     * Get type of block at this position in this chunk
     * 
     * @param x
     * @param y
     * @param z
     * @param type
     */
    public void setBlockTypeAt(int x, int y, int z, int type);

    /**
     * Get data of block at this position in this chunk
     * 
     * @param x
     * @param y
     * @param z
     * @return
     */
    public int getBlockDataAt(int x, int y, int z);

    /**
     * Get data of block at this position in this chunk
     * 
     * @param x
     * @param y
     * @param z
     * @param data
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
     * @return
     */
    public boolean isLoaded();
    /**
     * Regenerate this chunk
     * 
     * @return true when successful, false otherwise
     */
    public boolean regenerateChunk();

    /**
     * Get this chunks world (dimension)
     * 
     * @return
     */
    public Dimension getDimension();
    
    /**
     * Gets this chunk's biome data as a BiomeType array
     * 
     * @return biomedata
     */
    public BiomeType[] getBiomeData();
    
    /**
     * Gets this chunk's biome data as a byte array
     * @return biomedata
     */
    public byte[] getBiomeByteData();
    
    /**
     * Sets this chunks biome data from a BiomeType array
     * @param data
     */
    public void setBiomeData(BiomeType[] data);
    
    /**
     * Sets this chunk's biome data from a byte array
     * @param data
     */
    public void setBiomeData(byte[] data);
}
