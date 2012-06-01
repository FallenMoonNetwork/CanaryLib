package net.canarymod.hook.world;

import java.util.List;

import net.canarymod.api.entity.Entity;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;

/**
 * Explosion hook. Contains information about an explosion.
 * @author Jason Jones
 *
 */
public class ExplosionHook extends CancelableHook{
    
    private Block block;
    private Entity entity;
    private List<Block> blocksaffected;
    
    public ExplosionHook(Block block, Entity entity, List<Block>blocksaffected){
        this.block = block;
        this.entity = entity;
        this.blocksaffected = blocksaffected;
        this.type = Type.EXPLOSION;
    }
    
    /**
     * gets the base affected block
     * @return block
     */
    public Block getBlock(){
        return block;
    }
    
    /**
     * gets the entity causing the explosion
     * @return entity
     */
    public Entity getEntity(){
        return entity;
    }
    
    /**
     * gets the list of affected blocks
     * @return blocksaffected
     */
    public List<Block> getAffectedBlocks(){
        return blocksaffected;
    }
    
    /**
     * Return the set of Data in this order: BLOCK ENTITY BLOCKSAFFECTED ISCANCELLED
     */
    @Override
    public Object[] getDataSet(){
        return new Object[]{ block, entity, blocksaffected, isCancelled };
    }

}
