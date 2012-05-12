package net.canarymod.api.world;

/**
 * Handles Chunks
 * @author Chris Ksoll
 * @author Jos Kuijpers
 *
 */
public interface IChunk {
    
    /**
     * Get the Chunks X ordinate
     * @return
     */
    public int getX();
    
    /**
     * Get the Chunks Z ordinate
     * @return
     */
    public int getZ();
    
    /**
     * Get type of block at this position in this chunk
     * @param x
     * @param y
     * @param z
     * @return
     */
    public int getBlockTypeAt(int x, int y, int z);

    /**
     * Get type of block at this position in this chunk
     * @param x
     * @param y
     * @param z
     * @param type
     */
    public void setBlockTypeAt(int x, int y, int z, int type);
   
    /**
     * Get data of block at this position in this chunk
     * @param x
     * @param y
     * @param z
     * @return
     */
    public int getBlockDataAt(int x, int y, int z);
    
    /**
     * Get data of block at this position in this chunk
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
     * @return
     */
    public boolean isLoaded();
	
	/**
	 * Load the given chunk if it is not loaded
	 *
	 * @return true when successful, false otherwise
	 */
	public boolean load();

	/**
	 * Load the given chunk if it is not loaded
	 *
	 * @return true when successful, false otherwise
	 */
	public boolean reload();
	
	/**
	 * Load the given chunk if it is not loaded
	 *
	 * @return true when successful, false otherwise
	 */
	public boolean unload();
    
    /**
     * Regenerate this chunk
	 *
	 * @return true when successful, false otherwise
     */
    public boolean regenerateChunk();
    
    /**
     * Get this chunks world (dimension)
     * @return
     */
    public IDimension getDimension();
}
