package net.canarymod.hook.world;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;

/**
 * PortalDestroyHook<br>
 * Contains information about a portal being destroyed
 *
 * @author Chris (damagefilter)
 */
public final class PortalDestroyHook extends CancelableHook {

    private Block[][] blocks;

    /**
     * Constructs a new PortalDestroyHook
     *
     * @param blocks
     *         the {@link Block} set the make up the portal
     */
    public PortalDestroyHook(Block[][] blocks) {
        this.blocks = blocks;
    }

    /**
     * Get the set of blocks that make up the Portal
     *
     * @return block
     */
    public Block[][] getBlockSet() {
        return blocks;
    }

    @Override
    public final String toString() {
        return String.format("%s[Blocks=%s]", getName(), blocks);
    }
}
