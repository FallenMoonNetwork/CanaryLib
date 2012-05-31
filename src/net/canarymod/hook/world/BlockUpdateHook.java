package net.canarymod.hook.world;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;

/**
 * 
 * @author Jason Jones
 *
 */
public class BlockUpdateHook extends CancelableHook{
    
    private Block block;
    private int newBlockId;
    
    public BlockUpdateHook(Block block, int newBlockId){
        this.block = block;
        this.newBlockId = newBlockId;
        this.type = Type.BLOCK_UPDATE;
    }
    
    public Block getBlock(){
        return block;
    }
    
    public int getNewBlockId(){
        return newBlockId;
    }
    
    public Object[] getDataSet(){
        return new Object[]{ block, newBlockId, isCancelled };
    }
}
