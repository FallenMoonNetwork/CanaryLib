package net.canarymod.api.world;

public interface ChunkProviderServer {

    /**
     * Check if this chunk provider is allowed to save chunks
     * 
     * @return true if chunks can be saved, false otherwise
     */
    public boolean canSave();

    /**
     * Check if a chunk at the given coords exist
     * 
     * @param x
     * @param z
     * @return
     */
    public boolean chunkExists(int x, int z);

    /**
     * Load chunk at that x-z coordinate
     * 
     * @param x
     * @param z
     * @return Chunk that has been loaded
     */
    public Chunk loadChunk(int x, int z);

    /**
     * Load the given chunk if it is not loaded
     * 
     * @return true when successful, false otherwise
     */
    public void reloadChunk(int x, int z);

    /**
     * Drop the chunk at the given coordinates
     * 
     * @return true when successful, false otherwise
     */
    public void dropChunk(int x, int z);

    /**
     * Provides an Chunk. If that chunk didn't exist in any case it will
     * generate a new one
     * 
     * @param x
     * @param z
     * @return
     */
    public Chunk provideChunk(int x, int z);

    /**
     * Save up to two chunks or if saveAll is true, save all chunks
     * 
     * @param saveAll
     * @return true on success, false otherwise
     */
    public boolean saveChunk(boolean saveAll);
    
    /**
     * Regenerate the whole chunk at the given x/z coordinate
     * @param x
     * @param z
     * @return
     */
    public Chunk regenerateChunk(int x, int z);
    
    /**
     * Check if a chunk at the specified coordinates is loaded
     * @param x
     * @param z
     * @return
     */
    public boolean isChunkLoaded(int x, int z);

}
