package net.canarymod.api.inventory;

/**
 * Generic interface for containers of different types.
 */
public interface Container<T>{
    
    public T[] getContents();

    public void setContents(T[] items);

    public T getSlot(int index);

    public void setSlot(int index, T value);

    public int getInventorySize();

    public String getInventoryName();

    public void setInventoryName(String value);
    
    public void clearContents();
    
    public Item getItem(int id, int amount);
    
    public Item getItem(int id);
    
    public Item removeItem(Item item);
    
    public Item removeItem(int id);
    
    public T decreaseItemStackSize(int var1, int var2);
    
    public int getInventoryStackLimit();
    
    public boolean hasItemStack(T oItemStack);

    public boolean hasItem(int itemId);
    
    public void update();
}