package net.canarymod.api.inventory;


import net.canarymod.api.nbt.CompoundTag;


/**
 * This wraps an item stack
 * 
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 * @author Jos
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
     * adds enchantments to this item
     *
     * @param enchantments
     */
    public void addEnchantments(Enchantment... enchantments);

    /**
     * sets this item's enchantments (removes all others)
     *
     * @param enchantment
     */
    public void setEnchantments(Enchantment... enchantment);

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

    /**
     * Checks if this item has a display name.
     * 
     * @return {@code true} if has name; {@code false} if not
     */
    public boolean hasDisplayName();

    /**
     * Gets the visible name of this item.
     * Names can be set using an anvil or {@link #setName(java.lang.String)}.
     * 
     * @return The item name
     */
    public String getDisplayName();

    /**
     * Sets the visible name of this item.
     * Equivalent to renaming this item using an anvil.
     * 
     * @param name
     *            The item's new name
     */
    public void setDisplayName(String name);

    /**
     * Removes the display name if present
     */
    public void removeDisplayName();

    /**
     * Gets the cost to repair the Item
     * 
     * @return repair cost
     */
    public int getRepairCost();

    /**
     * Sets the cost to repair the Item
     * 
     * @param cost
     *            the repair cost
     */
    public void setRepairCost(int cost);

    /**
     * Returns the text that shows up under this item's name in the player's inventory.
     * 
     * @return the lore or {@code null} if no lore
     */
    public String[] getLore();

    /**
     * Sets the text that shows up under the item's name in the player's inventory
     * 
     * @param lore
     *            The lore to set, each line should be in a separate string in the array
     */
    public void setLore(String... lore);

    /**
     * Checks if the Item has lore
     * 
     * @return {@code true} if has lore; {@code false} if not
     */
    public boolean hasLore();

    /**
     * Returns an CompoundTag that is saved with this object.
     * This tag is for plugin use only.
     * Changing values in this tag will not affect the behavior of the object.
     * 
     * @return the CompoundTag of special metadata
     */
    public CompoundTag getMetaTag();

    /**
     * Checks if the Item has a DataTag
     * 
     * @return {@code true} if has DataTag; {@code false} if not
     */
    public boolean hasDataTag();

    /**
     * Sets the tag containing data for this item.
     * Should be named 'tag'.
     * Setting this to null removes name and lore data.
     * 
     * @param tag
     *            the data tag
     */
    public CompoundTag getDataTag();

    /**
     * Sets the tag containing data for this item.
     * Should be named 'tag'.
     * Setting this to null removes name and lore data.
     * 
     * @param tag
     *            the data tag
     */
    public void setDataTag(CompoundTag tag);

    /**
     * Writes this item's data to an NBTTagCompound.
     * 
     * @param tag
     *            The tag to write to
     * @return CompoundTag
     */
    public CompoundTag writeToTag(CompoundTag tag);

    /**
     * Sets this item's data to that in an CompoundTag.
     * 
     * @param tag
     *            The tag to read from
     */
    public void readFromTag(CompoundTag tag);

}
