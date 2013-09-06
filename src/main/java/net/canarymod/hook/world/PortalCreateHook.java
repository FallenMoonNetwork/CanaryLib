package net.canarymod.hook.world;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;

/**
 * PortalCreateHook<br>
 * Contains information about a portal being created
 *
 * @author Chris (damagefilter)
 */
public final class PortalCreateHook extends CancelableHook {

    private Block[][] blocks;

    /**
     * Constructs a new PortalCreateHook
     *
     * @param blocks
     *         the {@link Block} set the make up the portal
     */
    public PortalCreateHook(Block[][] blocks) {
        this.blocks = blocks;
    }

    /**
     * Get the set of blocks that are used to create this portal
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
