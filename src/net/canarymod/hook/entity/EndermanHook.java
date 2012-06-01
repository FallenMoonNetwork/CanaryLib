package net.canarymod.hook.entity;

import net.canarymod.api.entity.Enderman;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;

/**
 * Enderman hook. Contains information about an Enderman dropping or picking up a block
 * @author Jason Jones
 *
 */
public class EndermanHook extends CancelableHook{
    
    private Enderman enderman;
    private Block block;
    
    public EndermanHook(Enderman enderman, Block block, boolean dropping){
        this.enderman = enderman;
        this.block = block;
        this.type = dropping ? Type.ENDERMAN_DROP : Type.ENDERMAN_PICKUP;
    }
    
    /**
     * gets the enderman
     * @return
     */
    public Enderman getEnderman(){
        return enderman;
    }
    
    /**
     * gets the block
     * @return
     */
    public Block getBlock(){
        return block;
    }
    
    /**
     * Return the set of Data in this order: ENDERMAN BLOCK ISCANCELLED
     */
    @Override
    public Object[] getDataSet(){
        return new Object[]{ enderman, block, isCancelled };
    }
}
