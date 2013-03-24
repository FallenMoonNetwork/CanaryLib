package net.canarymod.api.world.blocks;


import net.canarymod.api.inventory.Container;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;


public interface Workbench extends ComplexBlock, Container<Item> {
    
    /**
     * Get this workbench's inventory
     * @return inventory
     */
    public Inventory getInventory();
    
}
