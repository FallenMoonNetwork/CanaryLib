package net.canarymod.hook.world;


import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;


/**
 * Block physics hook. Contains information about a block's physics being updated.
 * @author Jason Jones
 *
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
     * @return block
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Gets whether this {@link Block} was just placed
     * @return true is was placed
     */
    public boolean wasPlaced() {
        return placed;
    }

    /**
     * Return the set of Data in this order: BLOCK PLACED ISCANCELLED
     */
    @Override
    public Object[] getDataSet() {
        return new Object[] { block, placed, isCanceled };
    }
}
