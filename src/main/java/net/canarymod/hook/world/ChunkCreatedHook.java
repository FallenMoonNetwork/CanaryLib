package net.canarymod.hook.world;

import net.canarymod.api.world.Chunk;
import net.canarymod.api.world.World;
import net.canarymod.hook.Hook;

/**
 * Chunk created hook
 *
 * @author Chris (damagefilter)
 */
public final class ChunkCreatedHook extends Hook {

    private World world;
    private Chunk chunk;

    public ChunkCreatedHook(Chunk chunk, World world) {
        this.world = world;
        this.chunk = chunk;
    }

    /**
     * Gets the {@link Chunk}
     *
     * @return {@link Chunk} created.
     */
    public Chunk getChunk() {
        return chunk;
    }

    /**
     * Gets the world that this chunk is a part of.
     *
     * @return the {@link World}
     */
    public World getWorld() {
        return world;
    }

    @Override
    public final String toString() {
        return String.format("%s[Chunk=%s, World=%s]", getName(), chunk, world);
    }
}
