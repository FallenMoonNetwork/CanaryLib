package net.canarymod.hook.world;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;

/**
 * Block physics hook. Contains information about a block's physics being updated.
 *
 * @author Jason (darkdiplomat)
 */
public final class BlockPhysicsHook extends CancelableHook {

    private Block block;
    private boolean placed;

    public BlockPhysicsHook(Block block, boolean placed) {
        this.block = block;
        this.placed = placed;
    }

    /**
     * Gets the {@link Block}
     *
     * @return block
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Gets whether this {@link Block} was just placed
     *
     * @return true is was placed
     */
    public boolean wasPlaced() {
        return placed;
    }

    @Override
    public final String toString() {
        return String.format("%s[Block=%s, Was Placed=%s]", getName(), block, placed);
    }
}
