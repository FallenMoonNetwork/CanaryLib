package net.canarymod.hook.player;

import net.canarymod.api.entity.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.hook.Hook;
import net.canarymod.plugin.PluginListener;

/**
 * Craft hook. Contains plaer, item and if this item was crafted or smelted
 * @author Jason Jones
 *
 */
public final class CraftHook extends Hook{
    private Item result;
    private Player player;
    
    public CraftHook(Player player, Item result, boolean smelting){
        this.player = player;
        this.result = result;
        this.type = smelting ? /*Type.SMELT*/ null : Type.CRAFT;
    }
    
    /**
     * Get the {@link Player} crafting
     * @return
     */
    public Player getPlayer(){
        return player;
    }
    
    /**
     * Get the {@link Item} that is the craft result
     * @return
     */
    public Item getCraftResult(){
        return result;
    }
    
    /**
     * Sets the {@link Item} to be the craft result
     * @param result
     */
    public void setCraftResult(Item result){
        this.result = result;
    }

    /**
     * Return the set of Data in this order: PLAYER RESULT 
     */
    @Override
    public Object[] getDataSet() {
        return new Object[]{player, result};
    }
    
    /**
     * Dispatches the hook to the given listener.
     * @param listener The listener to dispatch the hook to.
     */
    @SuppressWarnings("incomplete-switch")
    @Override
    public void dispatch(PluginListener listener) {
        switch (this.type) {
            case CRAFT: {
                listener.onCraft(this);
                break;
            }
        }
    }

}
