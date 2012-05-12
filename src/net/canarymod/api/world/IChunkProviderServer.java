package net.canarymod.api.world;

public interface IChunkProviderServer {

    /**
     * Check if this chunk provider is allowed to save chunks
     * @return true if chunks can be saved, false otherwise
     */
    public boolean canSave();
    
    /**
     * Check if a chunk at the given coords exist
     * @param x
     * @param z
     * @return
     */
    public boolean chunkExists(int x, int z);
    
    /**
     * Load chunk at that x-z coordinate
     * @param x
     * @param z
     * @return IChunk that has been loaded
     */
    public IChunk loadChunk(int x, int z);
    
    /**
     * Provides an IChunk. If that chunk didn't exist in any case it will generate a new one
     * @param x
     * @param z
     * @return
     */
    public IChunk provideChunk(int x, int z);
    
    /**
     * Save up to two chunks or if saveAll is true, save all chunks
     * @param saveAll
     * @return true on success, false otherwise
     */
    public boolean saveChunk(boolean saveAll);
    
    /**
     * Remove all chunks from memory. <b>USE WITH EXTREME CAUTION!!!</b>
     */
    public void unloadAllChunks();
    
}
