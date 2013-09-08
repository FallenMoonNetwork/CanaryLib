package net.canarymod.api.inventory;

import net.canarymod.api.nbt.CompoundTag;

/**
 * This wraps an item stack
 *
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 * @author Jos
 */
public interface Item extends Cloneable {

    /**
     * gets this item's id
     *
     * @return item id
     */
    public int getId();

    /**
     * Set this Items Id
     *
     * @param id
     *         the id to set
     */
    public void setId(int id);

    /**
     * Gets this item's damage value
     *
     * @return item damage
     */
    public int getDamage();

    /**
     * Sets this item's damage value
     *
     * @param damage
     *         the damage to set
     */
    public void setDamage(int damage);

    /**
     * Gets this item's quantity
     *
     * @return amount
     */
    public int getAmount();

    /**
     * Sets this item's quantity
     *
     * @param amount
     *         the amount to set
     */
    public void setAmount(int amount);

    /**
     * Gets this item's max amount in a stack
     *
     * @return max stack amount
     */
    public int getMaxAmount();

    /**
     * Sets this item's max amount in a stack
     *
     * @param amount
     *         the max stack amount
     */
    public void setMaxAmount(int amount);

    /**
     * Get the inventory slot for this item
     *
     * @return the slot id
     */
    public int getSlot();

    /**
     * Return the Type of this item.
     *
     * @return the {@link ItemType}
     */
    public ItemType getType();

    /**
     * Set the inventory slot of this item
     *
     * @param slot
     *         the slot id to set
     */
    public void setSlot(int slot);

    /**
     * Return the BaseItem for this ItemStack, containing item statistics like
     * max stack size, and max amount of damage
     *
     * @return the {@link BaseItem}
     */
    public BaseItem getBaseItem();

    /**
     * gets whether this item is enchanted
     *
     * @return {@code true} if enchanted; {@code false} if not
     */
    public boolean isEnchanted();

    /**
     * Returns whether this item is enchantable.
     * Uses the same logic as anvils to determine whether an item is enchantable.
     *
     * @return {@code true} if this item is enchantable, {@code false} otherwise
     */
    public boolean isEnchantable();

    /**
     * gets the first enchantment of this item if exists
     *
     * @return the first {@link Enchantment}
     */
    public Enchantment getEnchantment();

    /**
     * gets the enchantment at the specified index if exists
     *
     * @param index
     *         the index of the enchantment to get
     *
     * @return the {@link Enchantment} at the index or {@code null}
     */
    public Enchantment getEnchantment(int index);

    /**
     * gets an array of enchantments for this item if they exist
     *
     * @return an {@link Enchantment} array
     */
    public Enchantment[] getEnchantments();

    /**
     * adds enchantments to this item
     *
     * @param enchantments
     *         the {@link Enchantment}(s) to add
     */
    public void addEnchantments(Enchantment... enchantments);

    /**
     * sets this item's enchantments (removes all others)
     *
     * @param enchantments
     *         the {@link Enchantment}(s) to set
     */
    public void setEnchantments(Enchantment... enchantments);

    /**
     * removes specified enchantment from this item
     *
     * @param enchantment
     *         the {@link Enchantment} to remove
     */
    public void removeEnchantment(Enchantment enchantment);

    /** removes all enchantments from this item */
    public void removeAllEnchantments();

    /**
     * Checks if this item has a display name.
     *
     * @return {@code true} if has name; {@code false} if not
     */
    public boolean hasDisplayName();

    /**
     * Gets the visible name of this item.
     * Names can be set using an anvil or {@link #setDisplayName(String)}.
     *
     * @return The item name
     */
    public String getDisplayName();

    /**
     * Sets the visible name of this item.
     * Equivalent to renaming this item using an anvil.
     *
     * @param name
     *         The item's new name
     */
    public void setDisplayName(String name);

    /** Removes the display name if present */
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
     *         the repair cost
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
     *         The lore to set, each line should be in a separate string in the array
     */
    public void setLore(String... lore);

    /**
     * Checks if the Item has lore
     *
     * @return {@code true} if has lore; {@code false} if not
     */
    public boolean hasLore();

    /**
     * Checks if the Item has a MetaTag or not
     *
     * @return {@code true} if MetaTag is present; {@code false} otherwise
     *
     * @see #getMetaTag()
     */
    public boolean hasMetaTag();

    /**
     * Returns an CompoundTag that is saved with this object.<br>
     * This tag is for plugin use only.<br>
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
     * Gets the tag containing data for this item.
     * Should be named 'tag'.
     * Setting this to null removes name and lore data.
     *
     * @return the data tag
     */
    public CompoundTag getDataTag();

    /**
     * Sets the tag containing data for this item.
     * Should be named 'tag'.
     * Setting this to null removes name and lore data.
     *
     * @param tag
     *         the data tag
     */
    public void setDataTag(CompoundTag tag);

    /**
     * Writes this item's data to an NBTTagCompound.
     *
     * @param tag
     *         The tag to write to
     *
     * @return CompoundTag
     */
    public CompoundTag writeToTag(CompoundTag tag);

    /**
     * Sets this item's data to that in an CompoundTag.
     *
     * @param tag
     *         The tag to read from
     */
    public void readFromTag(CompoundTag tag);

    /**
     * Clones the Item
     *
     * @return clone of the Item
     */
    public Item clone();

}
