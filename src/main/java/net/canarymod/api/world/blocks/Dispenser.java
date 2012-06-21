package net.canarymod.api.world.blocks;

import net.canarymod.api.entity.Entity;
import net.canarymod.api.inventory.Inventory;

public interface Dispenser extends ComplexBlock{
    
    /**
     * Get this dispensers inventory
     * @return inventory
     */
    public Inventory getInventory();
    
    public Entity activate();
    
    public Entity dispenseFromSlot(int slot);
    
}
