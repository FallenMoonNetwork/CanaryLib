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
     * Gets the {@link Player}
     * @return
     */
    public Player getPlayer(){
        return player;
    }
    
    /**
     * Gets the {@link Item}
     * @return
     */
    public Item getItem(){
        return item;
    }
    
    /**
     * Gets the new {@link Enchantment}
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
