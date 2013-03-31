package net.canarymod.api.inventory;


/**
 * Generic item inventory.
 *
 * @author Chris Ksoll
 * @author Jos Kuijpers
 *
 */
public interface Inventory extends Container<Item> {

    /**
     * Get the size of this inventory
     *
     * @return
     */
    public int getSize();

    /**
     * Get the inventory contents
     *
     * @return
     */
    @Override
    public Item[] getContents();

    /**
     * Get item by {@link ItemType} if it exists, returns null if inventory
     * doesn't contain this item type
     *
     * @param type
     * @return
     */
    public Item getItem(ItemType type);

    /**
     * Get item by {@link ItemType} and this amount
     *
     * @param type The ItemType of this item.
     * @param amount The amount of this item.
     * @return
     */
    public Item getItem(ItemType type, int amount);

    /**
     * Check if this inventory contains this item.
     *
     * @param type The ItemType of this item.
     * @return True if inventory contains this item, false otherwise
     */
    public boolean hasItem(ItemType type);

    /**
     * Check if this inventory contains the requested item with the requested
     * quantity
     *
     * @param type The ItemType of this item.
     * @param amount The amount of this item.
     * @return
     */
    public boolean hasItem(ItemType type, int amount);

    /**
     * Empties the inventory and returns a copy of it for further processing
     *
     * @return
     */
    public Item[] clearInventory();

    /**
     * Add this item to he inventory. (amount will be 1)
     *
     * @param itemId The ID number for this item.
     */
    public void addItem(int itemId);

    /**
     * Add an item to this container
     * @param type ItemType for this item.
     * @param amount Amount of this item.
     * @param damage
     */
    public void addItem(ItemType type, int amount, int damage);


    /**
     * Set a slot in the inventory with this item. Item contains slot
     * information.
     *
     * @param item
     */
    public void setSlot(Item item);

    /**
     * Set item with this id at the provided slot.
     *
     * @param itemId The ID number for this item.
     * @param slot The slot for this item.
     */
    public void setSlot(int itemId, int slot);

    /**
     * Get the item in the given slot
     *
     * @param slot The slot for this item.
     * @return
     */
    @Override
    public Item getSlot(int slot);

    /**
     * Put the specified item with the specified amount into the specified slot
     *
     * @param itemId The ID number for this item.
     * @param amount The amount of this item.
     * @param slot The slot for this item.
     */
    public void setSlot(int itemId, int amount, int slot);

    /**
     * Put the specified item with the specified amount into the specified slot
     *
     * @param type The ItemType of this item.
     * @param amount The amount of this item.
     * @param slot The slot for this item.
     */
    public void setSlot(ItemType type, int amount, int slot);

    /**
     * Put the specified item with the specified amount into the specified slot
     *
     * @param itemId The ID number for this item.
     * @param amount The amount of this item.
     * @param slot The slot for this item.
     */
    public void setSlot(int itemId, int amount, int damage, int slot);

    /**
     * Adds the item to the set, appending to stacks or with no or full stack,
     * adds a new stack. Stack sizes correspond with the max of the item
     *
     * @param item
     * @return true if all items are in the inventory, false when items are left
     *         over. item is updated to the leftover-amount.
     */
    public boolean insertItem(Item item);

}
