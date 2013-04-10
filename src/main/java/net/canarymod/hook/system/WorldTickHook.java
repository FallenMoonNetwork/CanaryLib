package net.canarymod.hook.system;


import net.canarymod.api.world.World;
import net.canarymod.hook.Hook;


/**
 * Called in the servers tick loop, for every ticked world
 *
 * For informational use
 * @author Chris (damagefilter)
 *
 */
public final class WorldTickHook extends Hook {

    private World world;
    private long deltaTime;

    public WorldTickHook(World world, long deltaTime) {
        this.world = world;
        this.deltaTime = deltaTime;
    }

    /**
     * Returns the world that is about to be ticked
     * @return the world
     */
    public World getWorld() {
        return world;
    }

    /**
     * Returns the time the last world tick needed to complete
     * @return the deltaTime
     */
    public long getDeltaTime() {
        return deltaTime;
    }

    @Override
    public final String toString() {
        return getName();
    }
}
