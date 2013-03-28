package net.canarymod.hook.world;


import net.canarymod.api.world.Chunk;
import net.canarymod.api.world.World;
import net.canarymod.hook.Hook;


/**
 * Chunk created hook
 * @author Chris Ksoll
 *
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
     * @return
     */
    public Chunk getChunk() {
        return chunk;
    }

    /**
     * @return the world
     */
    public World getWorld() {
        return world;
    }
}
