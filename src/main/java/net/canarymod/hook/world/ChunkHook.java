package net.canarymod.hook.world;

import net.canarymod.api.world.Chunk;
import net.canarymod.hook.Hook;
import net.canarymod.plugin.PluginListener;

/**
 * Multi-purpose hook for chunk load, unload, and created.
 * @author Jason Jones
 *
 */
public final class ChunkHook extends Hook{

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
    
    /**
     * Dispatches the hook to the given listener.
     * @param listener The listener to dispatch the hook to.
     */
    @SuppressWarnings("incomplete-switch")
    @Override
    public void dispatch(PluginListener listener) {
        switch (this.type) {
            case CHUNK_CREATED: {
                listener.onChunkCreated(this);
                break;
            }
            case CHUNK_LOADED: {
                listener.onChunkLoaded(this);
                break;
            }
            case CHUNK_UNLOADED: {
                listener.onChunkUnloaded(this);
                break;
            }
        }
    }
}
