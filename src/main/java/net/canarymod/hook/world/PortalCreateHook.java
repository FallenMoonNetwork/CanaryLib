package net.canarymod.hook.world;


import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;


/**
 * Explosion hook. Contains information about an explosion.
 * @author Chris Ksoll
 *
 */
public final class PortalCreateHook extends CancelableHook {

    private Block[][] blocks;

    public PortalCreateHook(Block[][] blocks) {
        this.blocks = blocks;
    }

    /**
     * Get the set of blocks that are used to create this portal
     * @return block
     */
    public Block[][] getBlockSet() {
        return blocks;
    }
}
