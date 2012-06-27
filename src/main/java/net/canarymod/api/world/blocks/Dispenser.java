package net.canarymod.api.world.blocks;

import net.canarymod.api.entity.Entity;
import net.canarymod.api.inventory.Container;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;

public interface Dispenser extends ComplexBlock, Container<Item>{
    
    /**
     * Get this dispensers inventory
     * @return inventory
     */
    public Inventory getInventory();
    
    public Entity activate();
    
    public Entity dispenseFromSlot(int slot);
    
}
