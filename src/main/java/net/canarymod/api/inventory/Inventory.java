package net.canarymod.api.inventory;

/**
 * Generic item inventory.
 *
 * @author Chris (damagefilter)
 * @author Jos Kuijpers
 */
public interface Inventory {

    /**
     * Add an item to this container
     *
     * @param item
     *         the {@link Item} to add
     */
    public void addItem(Item item);

    /**
     * Add this item to he inventory. (amount will be 1)
     *
     * @param type
     *         The ItemType for this item.
     */
    public void addItem(ItemType type);

    /**
     * Add this item to he inventory. (amount will be 1)
     *
     * @param itemId
     *         The ID number for this item.
     */
    public void addItem(int itemId);

    /**
     * Add this item to he inventory. (amount will be 1)
     *
     * @param itemId
     *         the Item ID
     * @param damage
     *         the Item damage
     */
    public void addItem(int itemId, short damage);

    /**
     * Add an item to this container
     *
     * @param itemId
     *         ID value for this item.
     * @param amount
     *         Amount of this item.
     */
    public void addItem(int itemId, int amount);

    /**
     * Add an item to this container
     *
     * @param type
     *         The ItemType for this item.
     * @param amount
     *         Amount of this item.
     */
    public void addItem(ItemType type, int amount);

    /**
     * Add an item to this container
     *
     * @param itemId
     *         ID value for this item.
     * @param amount
     *         Amount of this item.
     * @param damage
     *         the item damage
     */
    public void addItem(int itemId, int amount, short damage);

    /**
     * Add an item to this container
     *
     * @param type
     *         ItemType for this item.
     * @param amount
     *         Amount of this item.
     * @param damage
     *         the item damage
     */
    public void addItem(ItemType type, int amount, short damage);

    /** Remove all items from this container */
    public void clearContents();

    /**
     * Empties the inventory and returns a copy of it for further processing
     *
     * @return a {@link Item} array of what the inventory had
     */
    public Item[] clearInventory();

    /**
     * Remove from the amount from the next available item stack with the given
     * ID
     *
     * @param itemId
     *         ID value for this item.
     * @param amount
     *         Amount of this item.
     */
    public void decreaseItemStackSize(int itemId, int amount);

    /**
     * Remove from the amount from the next available item stack with the given
     * ID
     *
     * @param itemId
     *         ID value for this item.
     * @param amount
     *         Amount of this item.
     * @param damage
     *         Damage value for this item.
     */
    public void decreaseItemStackSize(int itemId, int amount, short damage);

    /**
     * Remove from the amount from the next available item stack that matches the Item
     *
     * @param item
     *         the {@link Item}
     */
    public void decreaseItemStackSize(Item item);

    /**
     * Get the inventory contents
     *
     * @return a {@link Item} array of what the inventory has
     */
    public Item[] getContents();

    /**
     * Get the next empty slot or -1 if there are no more empty slots
     *
     * @return the slot id of an empty slot or -1 if no empty slot
     */
    public int getEmptySlot();

    /**
     * Get the name of this container (something like inventory.chest)
     *
     * @return t
     */
    public String getInventoryName();

    /**
     * Get the stack limit for this inventory. That is: How big cna an item
     * stack be
     *
     * @return stack limit
     */
    public int getInventoryStackLimit();

    /**
     * Get item by {@link ItemType} if it exists, returns null if inventory
     * doesn't contain this item type
     *
     * @param type
     *         the {@link ItemType} to match
     *
     * @return the {@link Item} match or {@code null} if not found
     */
    public Item getItem(ItemType type);

    /**
     * Get the next available item stack that has the specified ID
     *
     * @param id
     *         ID value for this item.
     *
     * @return the {@link Item} match or {@code null} if not found
     */
    public Item getItem(int id);

    /**
     * Get item by {@link ItemType} and this amount
     *
     * @param type
     *         The ItemType of this item.
     * @param amount
     *         The amount of this item.
     *
     * @return the {@link Item} match or {@code null} if not found
     */
    public Item getItem(ItemType type, int amount);

    /**
     * Get an item with the specified amount from this container. This will
     * remove the item from the container if the specified amount is available
     * and an Item will be returned (null if not available)
     *
     * @param id
     *         ID value for this item.
     * @param amount
     *         Amount of this item.
     *
     * @return the {@link Item} match or {@code null} if not found
     */
    public Item getItem(int id, int amount);

    /**
     * Get an item with the specified amount from this container. This will
     * remove the item from the container if the specified amount is available
     * and an Item will be returned (null if not available)
     *
     * @param id
     *         ID value for this item.
     * @param amount
     *         Amount of this item.
     * @param damage
     *         The damage value of this item.
     *
     * @return the {@link Item} match or {@code null} if not found
     */
    public Item getItem(int id, int amount, short damage);

    /**
     * Get an item with the specified amount from this container. This will
     * remove the item from the container if the specified amount is available
     * and an Item will be returned (null if not available)
     *
     * @param type
     *         The ItemType of this item.
     * @param amount
     *         Amount of this item.
     * @param damage
     *         The damage value of this item.
     *
     * @return the {@link Item} match or {@code null} if not found
     */
    public Item getItem(ItemType type, int amount, short damage);

    /**
     * Get the size of this inventory
     *
     * @return the inventory size
     */
    public int getSize();

    /**
     * Get the item in the given slot
     *
     * @param slot
     *         The slot for this item.
     *
     * @return the {@link Item} in the slot or {@code null} if no Item
     */
    public Item getSlot(int slot);

    /**
     * Check if this container contains any item stack with the specified ID
     *
     * @param itemId
     *         ID value for this item.
     *
     * @return {@code true} if has item; {@code false} if not
     */
    public boolean hasItem(int itemId);

    /**
     * Check if this inventory contains this item.
     *
     * @param type
     *         The ItemType of this item.
     *
     * @return {@code true} if inventory contains this item, {@code false} otherwise
     */
    public boolean hasItem(ItemType type);

    /**
     * Check if this inventory contains this item.
     *
     * @param type
     *         The ItemType of this item.
     * @param damage
     *         the damage value
     *
     * @return {@code true} if inventory contains this item, {@code false} otherwise
     */
    public boolean hasItem(ItemType type, short damage);

    /**
     * Check if this container contains any item stack with the specified ID
     *
     * @param itemId
     *         ID value for this item.
     * @param damage
     *         Damage value for this item.
     *
     * @return {@code true} if inventory contains this item, {@code false} otherwise
     */
    public boolean hasItem(int itemId, short damage);

    /**
     * Check if this inventory contains the requested item with the requested
     * quantity
     *
     * @param type
     *         The ItemType of this item.
     * @param amount
     *         The amount of this item.
     *
     * @return {@code true} if inventory contains this item, {@code false} otherwise
     */
    public boolean hasItemStack(ItemType type, int amount);

    /**
     * Check if the container has an item stack with the specified ID and the
     * specified amount.
     *
     * @param itemId
     *         ID value for this item.
     * @param amount
     *         Amount of this item.
     *
     * @return {@code true} if inventory contains this item, {@code false} otherwise
     */
    public boolean hasItemStack(int itemId, int amount);

    /**
     * Check if this inventory contains the requested item with the requested
     * quantity
     *
     * @param type
     *         The ItemType of this item.
     * @param amount
     *         The amount of this item.
     * @param damage
     *         Damage value for this item.
     *
     * @return {@code true} if inventory contains this item, {@code false} otherwise
     */
    public boolean hasItemStack(ItemType type, int amount, int damage);

    /**
     * Check if the container has an item stack with the specified ID and the
     * specified amount.
     *
     * @param itemId
     *         ID value for this item.
     * @param amount
     *         Amount of this item.
     * @param damage
     *         Damage value for this item.
     *
     * @return {@code true} if inventory contains this item, {@code false} otherwise
     */
    public boolean hasItemStack(int itemId, int amount, int damage);

    /**
     * Check if this container has an itemstack with the specified ID, a minimum
     * amount and the maximum amount specified
     *
     * @param itemId
     *         ID value for this item.
     * @param minAmount
     *         the minimum amount
     * @param maxAmount
     *         the maximum amount
     * @param damage
     *         Damage value for this item.
     *
     * @return {@code true} if inventory contains this item, {@code false} otherwise
     */
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount, int damage);

    /**
     * Adds the item to the set, appending to stacks or with no or full stack,
     * adds a new stack. Stack sizes correspond with the max of the item
     *
     * @param item
     *         the {@link Item} to insert
     *
     * @return true if all items are in the inventory, false when items are left
     *         over. item is updated to the leftover-amount.
     */
    public boolean insertItem(Item item);

    /**
     * Set a slot in the inventory with this item. Item contains slot
     * information.
     *
     * @param item
     *         the {@link Item} to set
     */
    public void setSlot(Item item);

    /**
     * Set item with this id at the provided slot.
     *
     * @param itemId
     *         The ID number for this item.
     * @param amount
     *         amount of this item.
     * @param slot
     *         The slot for this item.
     */
    public void setSlot(int itemId, int amount, int slot);

    /**
     * Put the specified item with the specified amount into the specified slot
     *
     * @param itemId
     *         The ID number for this item.
     * @param amount
     *         The amount of this item.
     * @param damage
     *         Damage value for this item.
     * @param slot
     *         The slot for this item.
     */
    public void setSlot(int itemId, int amount, int damage, int slot);

    /**
     * Put the specified item with the specified amount into the specified slot
     *
     * @param type
     *         The ItemType of this item.
     * @param amount
     *         The amount of this item.
     * @param slot
     *         The slot for this item.
     */
    public void setSlot(ItemType type, int amount, int slot);

    /**
     * Put the specified item with the specified amount into the specified slot
     *
     * @param type
     *         The ItemType for this item.
     * @param amount
     *         The amount of this item.
     * @param damage
     *         Damage value for this item.
     * @param slot
     *         The slot for this item.
     */
    public void setSlot(ItemType type, int amount, int damage, int slot);

    /**
     * Remove an item from this container
     *
     * @param item
     *         the {@link Item} to remove
     *
     * @return the {@link Item} instance removed or {@code null} if no item
     */
    public Item removeItem(Item item);

    /**
     * Remove an item by this ID from the container
     *
     * @param id
     *         ID value for this item.
     *
     * @return the {@link Item} instance removed or {@code null} if no item
     */
    public Item removeItem(int id);

    /**
     * Remove an item by this ID from the container
     *
     * @param id
     *         ID value for this item.
     * @param damage
     *         Damage value for this item.
     *
     * @return the {@link Item} instance removed or {@code null} if no item
     */
    public Item removeItem(int id, int damage);

    /**
     * Remove an item by this ID from the container
     *
     * @param type
     *         The ItemType for this item.
     *
     * @return the {@link Item} instance removed or {@code null} if no item
     */
    public Item removeItem(ItemType type);

    /**
     * Remove an item by this ID from the container
     *
     * @param type
     *         The ItemType for this item.
     * @param damage
     *         Damage value for this item.
     *
     * @return the {@link Item} instance removed or {@code null} if no item
     */
    public Item removeItem(ItemType type, short damage);

    /**
     * Set the contents of this container object
     *
     * @param items
     *         the {@link Item} array to set
     */
    public void setContents(Item[] items);

    /**
     * Set the name of this container.
     *
     * @param value
     *         the name for the inventory
     */
    public void setInventoryName(String value);

    /**
     * Set the slot at the given index with the specified value
     *
     * @param index
     *         the slot id
     * @param value
     *         the {@link Item} to set
     */
    public void setSlot(int index, Item value);

    public boolean canOpenRemote();

    public void setCanOpenRemote(boolean remote);

    /** Update this container */
    public void update();

    public InventoryType getInventoryType();
}
