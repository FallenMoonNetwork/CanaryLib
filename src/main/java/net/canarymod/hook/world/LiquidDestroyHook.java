package net.canarymod.hook.world;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;
import net.canarymod.plugin.PluginListener;

/**
 * Flow hook. Contains information about a liquid flowing from one block to another
 * @author Chris Ksoll
 *
 */
public final class LiquidDestroyHook extends CancelableHook{
    
    private Block block;
    private boolean forceDestroy = false;
    
    public LiquidDestroyHook(Block block){
        this.block = block;
        this.type = Type.LIQUID_DESTROY;
    }
    
    /**
     * Get the block that would be destroyed by the liquid flow
     * @return
     */
    public Block getBlock(){
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
    public Object[] getDataSet(){
        return new Object[]{ block, forceDestroy, isCanceled };
    }
    
    /**
     * Dispatches the hook to the given listener.
     * @param listener The listener to dispatch the hook to.
     */
    @Override
    public void dispatch(PluginListener listener) {
        listener.onLiquidDestroy(this);
    }
}
