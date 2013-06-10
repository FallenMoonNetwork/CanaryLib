package net.canarymod.hook.world;

import net.canarymod.api.world.Chunk;
import net.canarymod.hook.Hook;

/**
 * Multi-purpose hook for chunk load, unload, and created.
 * 
 * @author Jason Jones
 */
public final class ChunkHook extends Hook {

    private Chunk chunk;

    public ChunkHook(Chunk chunk) {
        this.chunk = chunk;
    }

    /**
     * Gets the {@link Chunk}
     * 
     * @return
     */
    public Chunk getChunk() {
        return chunk;
    }

    @Override
    public final String toString() {
        return String.format("%s[Chunk=%s]", getName(), chunk);
    }
}
