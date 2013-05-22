package net.canarymod.hook.world;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;

/**
 * Event thrown when a block that drop experience (spawner, ore, redstone ore) is destroyed
 */
public class BlockDropXpHook extends CancelableHook {
    private final Block block;
    private int xp;

    public BlockDropXpHook(Block block, int xp) {
        this.block = block;
    }

    /**
     * Get the block that gives the dropped experience
     * @return The block
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Get the experience amount that will be dropped.
     */
    public int getXp() {
        return xp;
    }

    /**
     * Set the experience amount that will be dropped.
     * @param xp The experience amount that will be dropped
     */
    public void setXp(int xp) {
        //Be sure to not put any invalid values
        if (xp > 0) {
            this.xp = xp;
        }
    }
}
