package net.canarymod.api.inventory;

import net.canarymod.api.Enchantment;

/**
 * This wraps an item stack
 *
 * @author Chris
 * @author Jason
 * @author Jos
 *
 */
public interface Item {

    /**
     * gets this item's id
     *
     * @return
     */
    public int getId();

    /**
     * Set this Items Id
     */
    public void setId(int id);

    /**
     * gets this item's damage value
     *
     * @return
     */
    public int getDamage();

    /**
     * sets this item's damage value
     *
     * @param damage
     */
    public void setDamage(int damage);

    /**
     * gets this item's quantity
     *
     * @return
     */
    public int getAmount();

    /**
     * sets this item's quantity
     *
     * @param amount
     */
    public void setAmount(int amount);

    /**
     * gets this item's max amount in a stack
     *
     * @return
     */
    public int getMaxAmount();

    /**
     * sets this item's max amount in a stack
     *
     * @param amount
     */
    public void setMaxAmount(int amount);

    /**
     * Get the inventory slot for this item
     *
     * @return
     */
    public int getSlot();

    /**
     * Return the Type of this item.
     * @return
     */
    public ItemType getType();

    /**
     * Set the inventory slot of this item
     *
     * @param slot
     */
    public void setSlot(int slot);

    /**
     * Return the BaseItem for this ItemStack, containing item statistics like
     * max stack size, and max amount of damage
     *
     * @return
     */
    public BaseItem getBaseItem();

    /**
     * gets whether this item is enchanted
     *
     * @return
     */
    public boolean isEnchanted();

    /**
     * gets the first enchantment of this item if exists
     *
     * @return
     */
    public Enchantment getEnchantment();

    /**
     * gets the enchantment at the specified index if exists
     *
     * @param index
     * @return
     */
    public Enchantment getEnchantment(int index);

    /**
     * gets an array of enchantments for this item if they exist
     *
     * @return
     */
    public Enchantment[] getEnchantments();

    /**
     * adds an enchantment to this item
     *
     * @param enchantment
     */
    public void addEnchantment(Enchantment enchantment);

    /**
     * adds enchantments to this item
     *
     * @param enchantments
     */
    public void addEnchantments(Enchantment[] enchantments);

    /**
     * sets this items enchantment (removes all others)
     *
     * @param enchantment
     */
    public void setEnchantment(Enchantment enchantment);

    /**
     * sets this item's enchantments (removes all others)
     *
     * @param enchantment
     */
    public void setEnchantments(Enchantment[] enchantment);

    /**
     * removes specified enchantment from this item
     *
     * @param enchantment
     */
    public void removeEnchantment(Enchantment enchantment);

    /**
     * removes all enchantments from this item
     */
    public void removeAllEnchantments();

}
