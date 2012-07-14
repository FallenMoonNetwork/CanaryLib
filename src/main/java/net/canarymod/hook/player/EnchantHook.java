package net.canarymod.hook.player;

import java.util.List;

import net.canarymod.api.Enchantment;
import net.canarymod.api.entity.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.hook.CancelableHook;
import net.canarymod.plugin.PluginListener;

/**
 * Enchant hook. Contains information about a player enchanting an item.
 * @author Jason Jones
 * @author Chris Ksoll
 *
 */
public final class EnchantHook extends CancelableHook{
    
    private Player player;
    private Item item;
    private List<Enchantment> enchantments;
    
    public EnchantHook(Player player, Item item, List<Enchantment> enchantments){
        this.player = player;
        this.item = item;
        this.enchantments = enchantments;
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
     * Gets the new {@link Enchantment} as list
     * @return enchantment
     */
    public List<Enchantment> getEnchantmentList(){
        return enchantments;
    }
    
    /**
     * Override the whole list of enchantments
     * @param newList
     */
    public void setEnchantmentList(List<Enchantment> newList) {
        this.enchantments = newList;
    }
    
    /**
     * Add a new enchantment to the list of existing enchantments
     * @param enchantment
     */
    public void addEnchantment(Enchantment enchantment) {
        enchantments.add(enchantment);
    }
    
    /**
     * Remove an enchantment from the list
     * @param enchantment
     */
    public void removeEnchantment(Enchantment enchantment) {
        enchantments.remove(enchantment);
    }
    
    /**
     * Return the set of Data in this order: PLAYER ITEM ENCHANTMENTLIST ISCANCELLED
     */
    @Override
    public Object[] getDataSet(){
        return new Object[]{ player, item, enchantments, isCanceled };
    }
    
    /**
     * Validate the enchantments 
     * @param checkStackable
     * @return
     */
    public boolean isValid(boolean checkStackable)
    {
        Enchantment[] enchantmentsArray = this.enchantments.toArray(new Enchantment[this.enchantments.size()]);
        for (int i = 0; i < enchantmentsArray.length; i += 1)
        {
            if (enchantmentsArray[i].isValid())
            {
                for (int j = i + 1; j < enchantmentsArray.length; j += 1)
                {
                    if (enchantmentsArray[j].isValid())
                    {
                        if (checkStackable && !enchantmentsArray[i].canStack(enchantmentsArray[j]))
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
            }
            else
            {
                return false;
            }
        }
        return enchantmentsArray.length > 0;
    }
    
    /**
     * Dispatches the hook to the given listener.
     * @param listener The listener to dispatch the hook to.
     */
    @Override
    public void dispatch(PluginListener listener) {
        listener.onEnchant(this);
    }

}
