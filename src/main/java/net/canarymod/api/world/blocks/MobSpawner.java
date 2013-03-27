package net.canarymod.api.world.blocks;


/**
 * MobSpawner Wrapper
 *
 * @author Jason (darkdiplomat)
 */
public interface MobSpawner extends ComplexBlock {

    /**
     * Get the Logic instance for this MobSpawner.
     * @return The Logic Instance.
     */
    public MobSpawnerLogic getLogic();
}
