package net.canarymod.api.inventory;

/**
 * Generic item inventory.
 * 
 * @author Chris Ksoll
 * @author Jos Kuijpers
 * 
 */
public interface Inventory {

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
    public Item[] getContent();

    /**
     * Get an item with this type from the inventory. If it's not inside, return
     * null.
     * 
     * @param type
     * @return
     */
    public Item getItem(int type);

    /**
     * Get item by {@link ItemType} if it exists, returns null if inventory
     * doesn't contain this item type
     * 
     * @param type
     * @return
     */
    public Item getItem(ItemType type);

    /**
     * Get item with this amount from the inventory
     * 
     * @param type
     * @param amount
     * @return
     */
    public Item getItem(int type, int amount);

    /**
     * Get item by {@link ItemType} and this amount
     * 
     * @param type
     * @param amount
     * @return
     */
    public Item getItem(ItemType type, int amount);

    /**
     * Check if this inventory contains this item.
     * 
     * @param type
     * @return True if inventory contains this item, false otherwise
     */
    public boolean hasItem(ItemType type);

    /**
     * Check if this inventory contains this item
     * 
     * @param type
     * @return True if inventory contains this item, false otherwise
     */
    public boolean hasItem(int type);

    /**
     * Check if this inventory contains the requested item with the requested
     * quantity
     * 
     * @param type
     * @param amount
     * @return
     */
    public boolean hasItem(ItemType type, int amount);

    /**
     * Check if this inventory contains the requested item with the requested
     * quantity
     * 
     * @param type
     * @param amount
     * @return
     */
    public boolean hasItem(int type, int amount);

    /**
     * Empties the inventory and returns a copy of it for further processing
     * 
     * @return
     */
    public Item[] clearInventory();

    /**
     * Remove this item from the inventory
     * 
     * @param item
     */
    public void removeItem(Item item);

    /**
     * Remove item with this ID from the inventory and return the item reference
     * for further processing
     * 
     * @param itemId
     * @return IItem
     */
    public Item removeItem(int itemId);

    /**
     * Add item with this amount to the inventory
     * 
     * @param itemId
     * @param amount
     */
    public void addItem(int itemId, int amount);

    /**
     * Add this item to he inventory. (amount will be 1)
     * 
     * @param itemId
     */
    public void addItem(int itemId);

    /**
     * Add this item to the inventory
     * 
     * @param item
     */
    public void addItem(Item item);

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
     * @param itemId
     * @param slot
     */
    public void setSlot(int itemId, int slot);

    /**
     * Put the specified item with the specified amount into the specified slot
     * 
     * @param itemId
     * @param amount
     * @param slot
     */
    public void setSlot(int itemId, int amount, int slot);

    /**
     * Re-send inventory information to its owner
     */
    public void updateInventory();

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
