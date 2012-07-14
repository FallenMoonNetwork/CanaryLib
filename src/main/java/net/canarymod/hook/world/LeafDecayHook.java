package net.canarymod.hook.world;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;
import net.canarymod.plugin.PluginListener;

/**
 * Block update hook. Contains information about a block updating.
 * @author Jason Jones
 *
 */
public final class LeafDecayHook extends CancelableHook{
    
    private Block block;
    
    public LeafDecayHook(Block block){
        this.block = block;
        this.type = Type.LEAF_DECAY;
    }
    
    /**
     * Get the leaf block that is about to decay
     * @return
     */
    public Block getBlock(){
        return block;
    }

    /**
     * Return the set of Data in this order: BLOCK ISCANCELLED
     */
    @Override
    public Object[] getDataSet(){
        return new Object[]{ block, isCanceled };
    }
    
    /**
     * Dispatches the hook to the given listener.
     * @param listener The listener to dispatch the hook to.
     */
    @Override
    public void dispatch(PluginListener listener) {
        listener.onLeafDecay(this);
    }
}
