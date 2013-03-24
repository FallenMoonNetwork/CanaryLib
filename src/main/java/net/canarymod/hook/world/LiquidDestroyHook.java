package net.canarymod.hook.world;


import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;


/**
 * Is called when a liquid is about to destroy something (crops, popping off torches etc)
 * @author Chris Ksoll
 *
 */
public final class LiquidDestroyHook extends CancelableHook {

    private Block block;
    private boolean forceDestroy = false;

    public LiquidDestroyHook(Block block) {
        this.block = block;
    }

    /**
     * Get the block that would be destroyed by the liquid flow
     * @return
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Check if the block in question will be destroyed regardless of what it is.
     * @return
     */
    public boolean isForceDestroy() {
        return forceDestroy;
    }

    /**
     * Set this to true to force the block to be destroyed.
     * This will not work if the hook is canceled already!
     * @param forceDestroy
     */
    public void setForceDestroy(boolean forceDestroy) {
        this.forceDestroy = forceDestroy;
    }

    /**
     * Return the set of Data in this order: BLOCK ISCANCELLED
     */
    @Override
    public Object[] getDataSet() {
        return new Object[] { block, forceDestroy, isCanceled };
    }
}
