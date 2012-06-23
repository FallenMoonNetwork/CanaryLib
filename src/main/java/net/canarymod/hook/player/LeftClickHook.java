package net.canarymod.hook.player;

import net.canarymod.api.entity.Player;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;

/**
 * Left click hook. Contains information about a Player left clicking.
 * @author Jason Jones
 * 
 */
public class LeftClickHook extends CancelableHook{
    
    private Player player;
    private Block block;
    
    public LeftClickHook(Player player, Block block){
        this.player = player;
        this.block = block;
        this.type = block == null ? Type.ARM_SWING : block.getStatus() == 0 ? Type.BLOCK_LEFTCLICKED : Type.BLOCK_BREAK;
    }
    
    /**
     * Gets the {@link Player} clicking
     * @return player
     */
    public Player getPlayer(){
        return player;
    }
    
    /**
     * Get the {@link Block} the {@link Player} is left clicking
     * @return block if player is clicking a block, null otherwise
     */
    public Block getBlock(){
        return block;
    }
    
    /**
     * Return the set of Data in this order: PLAYER BLOCK ISCANCELLED
     */
    public Object[] getDataSet(){
        return new Object[]{ player, block, isCanceled};
    }
}
