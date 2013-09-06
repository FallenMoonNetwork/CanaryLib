package net.canarymod.hook.world;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;

/**
 * Tree Grow Hook
 * <p/>
 * Called with a sapling attempts to become a Tree<br>
 * This does not signal that a tree can grow or how it would grow.
 *
 * @author Jason (darkdiplomat)
 */
public final class TreeGrowHook extends CancelableHook {
    private Block sapling;

    /**
     * Constructs a new TreeGrowHook
     *
     * @param sapling
     *         the sapling trying to grow
     */
    public TreeGrowHook(Block sapling) {
        this.sapling = sapling;
    }

    /**
     * Gets the sapling trying to grow
     *
     * @return the sapling
     */
    public Block getSapling() {
        return sapling;
    }
}
