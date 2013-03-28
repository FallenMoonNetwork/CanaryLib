package net.canarymod.hook.world;


import net.canarymod.api.world.Chunk;
import net.canarymod.api.world.World;
import net.canarymod.hook.CancelableHook;


/**
 * Chunk unload hook
 * @author Chris Ksoll
 *
 */
public final class ChunkUnloadHook extends CancelableHook {

    private World world;
    private Chunk chunk;

    public ChunkUnloadHook(Chunk chunk, World world) {
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
