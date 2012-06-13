package net.canarymod.api.inventory;

public interface PlayerInventory extends Inventory{
    
    public Item getArmorSlot(int slot);
    
    public void setArmorSlot(Item item);
    
    
}
