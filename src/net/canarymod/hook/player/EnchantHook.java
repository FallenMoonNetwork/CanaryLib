package net.canarymod.hook.player;

import net.canarymod.api.Enchantment;
import net.canarymod.api.entity.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.hook.CancelableHook;

/**
 * Enchant hook. Contains information about a player enchanting an item.
 * @author Jason Jones
 *
 */
public class EnchantHook extends CancelableHook{
    
    private Player player;
    private Item item;
    private Enchantment enchantment;
    
    public EnchantHook(Player player, Item item, Enchantment enchantment){
        this.player = player;
        this.item = item;
        this.enchantment = enchantment;
        this.type = Type.ENCHANT;
    }
    
    /**
     * gets the player
     * @return
     */
    public Player getPlayer(){
        return player;
    }
    
    /**
     * gets the item
     * @return
     */
    public Item getItem(){
        return item;
    }
    
    /**
     * gets the new enchantment
     * @return enchantment
     */
    public Enchantment enchantment(){
        return enchantment;
    }
    
    /**
     * Return the set of Data in this order: PLAYER ITEM ENCHANTMENT ISCANCELLED
     */
    @Override
    public Object[] getDataSet(){
        return new Object[]{ player, item, enchantment, isCancelled };
    }

}
