package net.canarymod.hook.world;

import net.canarymod.api.world.Chunk;
import net.canarymod.api.world.World;
import net.canarymod.hook.Hook;

/**
 * Chunk created hook
 *
 * @author Chris (damagefilter)
 */
public final class ChunkLoadedHook extends Hook {

    private World world;
    private Chunk chunk;

    public ChunkLoadedHook(Chunk chunk, World world) {
        this.world = world;
        this.chunk = chunk;
    }

    /**
     * Gets the {@link Chunk}
     *
     * @return The {@link Chunk}.
     */
    public Chunk getChunk() {
        return chunk;
    }

    /**
     * Gets the world this chunk is a part of.
     *
     * @return the world
     */
    public World getWorld() {
        return world;
    }

    @Override
    public final String toString() {
        return String.format("%s[World=%s, Chunk=%s]", getName(), world, chunk);
    }
}
