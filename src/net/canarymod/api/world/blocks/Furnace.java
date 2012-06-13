package net.canarymod.api.world.blocks;

import net.canarymod.api.inventory.Inventory;

public interface Furnace extends ComplexBlock{
    
    /**
     * Get this furnace's inventory
     * @return inventory
     */
    public Inventory getInventory();

}
