package net.canarymod.hook.world;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;
import net.canarymod.plugin.PluginListener;

/**
 * Flow hook. Contains information about a liquid flowing from one block to another
 * @author Jason Jones
 *
 */
public final class FlowHook extends CancelableHook{
    
    private Block from, to;
    
    public FlowHook(Block from, Block to){
        this.from = from;
        this.to = to;
        this.type = Type.FLOW;
    }
    
    /**
     * Gets the {@link Block} flowing from
     * @return
     */
    public Block getBlockFrom(){
        return from;
    }
    
    /**
     * Gets the {@link Block} flowing to
     * @return
     */
    public Block getBlockTo(){
        return to;
    }
    
    /**
     * Return the set of Data in this order: BLOCKFROM BLOCKTO ISCANCELLED
     */
    @Override
    public Object[] getDataSet(){
        return new Object[]{ from, to, isCanceled };
    }
    
    /**
     * Dispatches the hook to the given listener.
     * @param listener The listener to dispatch the hook to.
     */
    @Override
    public void dispatch(PluginListener listener) {
        listener.onFlow(this);
    }

}
