package net.canarymod.hook.world;

import net.canarymod.api.world.Chunk;
import net.canarymod.hook.Hook;

/**
 * Multi-purpose hook for chunk load, unload, and created.
 * @author Jason Jones
 *
 */
public class ChunkHook extends Hook{

    private Chunk chunk;
    
    public ChunkHook(Chunk chunk, Type type){
        this.chunk = chunk;
        this.type = type;
    }
    
    /**
     * Gets the {@link Chunk}
     * @return
     */
    public Chunk getChunk(){
        return chunk;
    }
    
    /**
     * Return the set of Data in this order: CHUNK
     */
    @Override
    public Object[] getDataSet() {
        return new Object[]{ chunk };
    }
}
