package net.canarymod.hook.player;

import net.canarymod.api.entity.Player;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.hook.CancelableHook;

/**
 * Inventory hook. Contains information about a player either opening or closing an inventory
 * @author Jason Jones
 *
 */
public class InventoryHook extends CancelableHook {
    
    private Player player;
    private Inventory inventory;
    
    public InventoryHook(Player player, Inventory inventory, boolean closing){
        this.player = player;
        this.inventory = inventory;
        this.type = closing ? Type.CLOSE_INVENTORY : Type.OPEN_INVENTORY;
    }
    
    /**
     * gets the player
     * @return player
     */
    public Player getPlayer(){
        return player;
    }
    
    /**
     * gets the inventory
     * @return inventory
     */
    public Inventory getInventory(){
        return inventory;
    }
    
    /**
     * Return the set of Data in this order: PLAYER INVENTORY ISCANCELLED
     */
    public Object[] getDataSet(){
        return new Object[]{ player, inventory, isCancelled};
    }
}
