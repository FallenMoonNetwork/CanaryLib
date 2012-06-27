package net.canarymod.api.world.blocks;

import net.canarymod.api.inventory.Container;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;

public interface Furnace extends ComplexBlock, Container<Item>{
    
    /**
     * Get this furnace's inventory
     * @return inventory
     */
    public Inventory getInventory();

}
