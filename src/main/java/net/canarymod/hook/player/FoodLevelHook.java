package net.canarymod.hook.player;

import net.canarymod.api.entity.Player;
import net.canarymod.hook.Hook;
import net.canarymod.plugin.PluginListener;

/**
 * Food level hook. Contains information about a player's food level/saturation/exhaustion changes
 * @author Jason Jones
 *
 */
public final class FoodLevelHook extends Hook{
    
    private Player player;
    private int oldval, newval;
    
    public FoodLevelHook(Player player, int oldval, int newval, Type type){
        this.player = player;
        this.oldval = oldval;
        this.newval = newval;
        this.type = type;
    }
    
    /**
     * Gets the {@link Player}
     * @return
     */
    public Player getPlayer(){
        return player;
    }
    
    /**
     * Gets the old value
     * @return
     */
    public int getOldValue(){
        return oldval;
    }
    
    /**
     * Gets the new value
     * @return
     */
    public int getNewValue(){
        return newval;
    }
    
    /**
     * Sets the new value
     * @param value
     */
    public void setNewValue(int value){
        this.newval = value;
    }
    
    /**
     * Return the set of Data in this order: PLAYER OLDVAL NEWVAL
     */
    @Override
    public Object[] getDataSet(){
        return new Object[]{ player, oldval, newval };
    }
    
    /**
     * Dispatches the hook to the given listener.
     * @param listener The listener to dispatch the hook to.
     */
    @SuppressWarnings("incomplete-switch")
    @Override
    public void dispatch(PluginListener listener) {
        switch(this.type){
            case FOODEXHAUSTION_CHANGE: {
               listener.onFoodExhaustionChange(this);
               break;
            }
            case FOODLEVEL_CHANGE: {
                listener.onFoodLevelChange(this);
                break;
            }
            case FOODSATURATION_CHANGE: {
                listener.onFoodSaturationChange(this);
                break;
            }
        }
    }
}
