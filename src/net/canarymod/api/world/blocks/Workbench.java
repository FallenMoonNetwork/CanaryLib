package net.canarymod.api.world.blocks;

import net.canarymod.api.inventory.Inventory;

public interface Workbench extends ComplexBlock{
    
    /**
     * Get this workbench's inventory
     * @return inventory
     */
    public Inventory getInventory();

}
