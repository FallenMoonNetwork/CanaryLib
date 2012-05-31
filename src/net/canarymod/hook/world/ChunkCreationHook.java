package net.canarymod.hook.world;

import net.canarymod.api.world.World;
import net.canarymod.hook.Hook;

/**
 * Chunk creation hook. Contains information about a new chunk being created.
 * @author Jason Jones
 *
 */
public class ChunkCreationHook extends Hook{

    private int x, z;
    private byte[] blockdata;
    private byte[] biomedata;
    private World world;
    
    public ChunkCreationHook(int x, int z, World world){
        this.x = x;
        this.z = z;
        this.world = world;
        this.type = Type.CHUNK_CREATION;
    }
    
    /**
     * gets the x coordinate of the chunk
     * @return x
     */
    public int getX(){
        return x;
    }
    
    /**
     * gets the z coordinate of the chunk
     * @return z
     */ 
    public int getZ(){
        return z;
    }
    
    /**
     * gets the world the chunk is in
     * @return world
     */
    public World getWorld(){
        return world;
    }
    
    /**
     * Sets the blocks data. Tips: Set a byte[32768] if you want to generate a new chunk.
     * The block index equals (x * 16 + z) * 128 + y where 16>x<=0, 16>z<=0, and 128>y<=0
     */
    public void setBlockData(byte[] blockdata){
        this.blockdata = blockdata;
    }
    
    /**
     * gets the block data of the chunk
     * @return blockdata
     */
    public byte[] getBlockData(){
        return blockdata;
    }
    
    /**
     * sets the biome data of each y column in the chunk
     * @param biomedata
     */
    public void setBiomeData(byte[] biomedata){
        this.biomedata = biomedata;
    }
    
    /**
     * gets the biomedata for the chunk
     * @return biomedata
     */
    public byte[] getBiomeData(){
        return biomedata;
    }
    
    @Override
    public Object[] getDataSet() {
        // TODO Auto-generated method stub
        return null;
    }
}
