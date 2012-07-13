package net.canarymod.hook.world;

import net.canarymod.api.world.Dimension;
import net.canarymod.hook.Hook;
import net.canarymod.plugin.PluginListener;

/**
 * Chunk creation hook. Contains information about a new chunk being created.
 * @author Jason Jones
 *
 */
public final class ChunkCreationHook extends Hook{

    private int x, z;
    private byte[] blockdata;
    private byte[] biomedata;
    private Dimension dimension;
    
    public ChunkCreationHook(int x, int z, Dimension dimension){
        this.x = x;
        this.z = z;
        this.dimension = dimension;
        this.type = Type.CHUNK_CREATION;
    }
    
    /**
     * Gets the x coordinate of the chunk
     * @return x
     */
    public int getX(){
        return x;
    }
    
    /**
     * Gets the z coordinate of the chunk
     * @return z
     */ 
    public int getZ(){
        return z;
    }
    
    /**
     * Gets the dimension the chunk is in
     * @return dimension
     */
    public Dimension getDimension(){
        return dimension;
    }
    
    /**
     * Sets the blocks data. Tips: Set a byte[32768] if you want to generate a new chunk.
     * The block index equals (x * 16 + z) * 128 + y where 16>x<=0, 16>z<=0, and 128>y<=0
     */
    public void setBlockData(byte[] blockdata){
        this.blockdata = blockdata;
    }
    
    /**
     * Gets the block data of the chunk
     * @return blockdata
     */
    public byte[] getBlockData(){
        return blockdata;
    }
    
    /**
     * Sets the biome data of each y column in the chunk
     * @param biomedata
     */
    public void setBiomeData(byte[] biomedata){
        this.biomedata = biomedata;
    }
    
    /**
     * Gets the biomedata for the chunk
     * @return biomedata
     */
    public byte[] getBiomeData(){
        return biomedata;
    }
    
    @Override
    public Object[] getDataSet() {
        return new Object[]{x, z, blockdata, biomedata, dimension};
    }
    
    /**
     * Dispatches the hook to the given listener.
     * @param listener The listener to dispatch the hook to.
     */
    @Override
    public void dispatch(PluginListener listener) {
        listener.onChunkCreation(this);
    }
}
