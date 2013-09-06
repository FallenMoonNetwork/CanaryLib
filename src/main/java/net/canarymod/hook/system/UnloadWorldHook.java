package net.canarymod.hook.system;

import net.canarymod.api.world.World;
import net.canarymod.hook.Hook;

/**
 * Called before a world is unloaded.<br>
 * Before a world is unloaded, it will be saved!
 * After a world is unloaded it will not be processed in the tick loop anymore.
 * A world can not be unloaded if it still has players on it.
 * For informational use - to give plugins a chance to null their references to that world, if they have any.
 *
 * @author Chris (damagefilter)
 */
public final class UnloadWorldHook extends Hook {

    private World world;

    public UnloadWorldHook(World world) {
        this.world = world;
    }

    /**
     * Returns the world that is about to be unloaded
     *
     * @return the world
     */
    public World getWorld() {
        return world;
    }

    @Override
    public final String toString() {
        return "UnloadWorldHook[World=" + world.getFqName() + "]";
    }
}
