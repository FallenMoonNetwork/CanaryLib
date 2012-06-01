package net.canarymod.hook.world;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;

/**
 * Flow hook. Contains information about a liquid flowing from one block to another
 * @author Jason Jones
 *
 */
public class FlowHook extends CancelableHook{
    
    private Block from, to;
    
    public FlowHook(Block from, Block to){
        this.from = from;
        this.to = to;
        this.type = Type.FLOW;
    }
    
    /**
     * gets the block flowing from
     * @return
     */
    public Block getBlockFrom(){
        return from;
    }
    
    /**
     * gets the block flowing to
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
        return new Object[]{ from, to, isCancelled };
    }

}
