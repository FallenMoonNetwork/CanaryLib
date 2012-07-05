package net.canarymod.hook.player;

import net.canarymod.api.entity.Player;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;
import net.canarymod.plugin.PluginListener;

/**
 * Left click hook. Contains information about a Player left clicking.
 * @author Jason Jones
 * 
 */
public final class LeftClickHook extends CancelableHook{
    
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
    
    /**
     * Dispatches the hook to the given listener.
     * @param listener The listener to dispatch the hook to.
     */
    @Override
    public void dispatch(PluginListener listener) {
        switch (this.type) {
            case ARM_SWING: {
                listener.onArmSwing(this);
                break;
            }
            case BLOCK_LEFTCLICKED: {
                listener.onBlockLeftClicked(this);
                break;
            }
            case BLOCK_BREAK: {
                listener.onBlockBreak(this);
                break;
            }
        }
    }
}
