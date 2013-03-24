package net.canarymod.api.inventory;


/**
 * Generic interface for containers of different types.
 */
public interface Container<T> {
    
    /**
     * Get an array of contents for this Container object
     * @return
     */
    public T[] getContents();

    /**
     * Set the contents of this container object
     * @param items
     */
    public void setContents(T[] items);

    /**
     * Get (Item) from the specified Container slot
     * @param index
     * @return
     */
    public T getSlot(int index);
    
    /**
     * Add an item to this container
     * @param itemId
     * @param amount
     */
    public void addItem(int itemId, int amount);
    
    /**
     * Add an item to this container
     * @param item
     */
    public void addItem(Item item);
    
    /**
     * Get the next empty slot or -1 if there are no more empty slots 
     * @return
     */
    public int getEmptySlot();

    /**
     * Set the slot at the given index with the specified value
     * @param index
     * @param value
     */
    public void setSlot(int index, T value);

    /**
     * Get the number of all slots of this container
     * @return
     */
    public int getInventorySize();

    /**
     * Get the name of this container (something like inventory.chest)
     * @return
     */
    public String getInventoryName();

    /**
     * Set the name of this container.
     * @param value
     */
    public void setInventoryName(String value);
    
    /**
     * Remove all items from this container
     */
    public void clearContents();
    
    /**
     * Get an item with the specified amount from this container.
     * This will remove the item from the container if the specified amount 
     * is available and an Item will be returned (null if not available)
     * @param id
     * @param amount
     * @return
     */
    public Item getItem(int id, int amount);
    
    /**
     * Get the next available item stack that has the specified ID
     * @param id
     * @return
     */
    public Item getItem(int id);
    
    /**
     * Remove an item from this container
     * @param item
     * @return
     */
    public Item removeItem(Item item);
    
    /**
     * Remove an item by this ID from the container
     * @param id
     * @return
     */
    public Item removeItem(int id);
    
    /**
     * Remove from the amount from the next available item stack with the given ID
     * @param itemId
     * @param amount
     * @return
     */
    public T decreaseItemStackSize(int itemId, int amount);
    
    /**
     * Get the stack limit for this inventory.
     * That is: How big cna an item stack be
     * @return
     */
    public int getInventoryStackLimit();
    
    /**
     * Check if container has this item stack
     * @param oItemStack
     * @return
     */
    public boolean hasItemStack(T oItemStack);
    
    /**
     * Check if the container has an item stack with the specified ID and the specified amount.
     * @param itemId
     * @param amount
     * @return
     */
    public boolean hasItemStack(int itemId, int amount);
    
    /**
     * Check if this container has an itemstack with the specified ID, a minimum amount and the maximum amount specified
     * @param itemId
     * @param minAmount
     * @param maxAmount
     * @return
     */
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount);
    
    /**
     * Check if this container contains any item stack with the specified ID
     * @param itemId
     * @return
     */
    public boolean hasItem(int itemId);
    
    /**
     * Update this container
     */
    public void update();
}
