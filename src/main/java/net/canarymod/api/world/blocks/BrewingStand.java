package net.canarymod.api.world.blocks;

import net.canarymod.api.inventory.Container;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;

public interface BrewingStand extends ComplexBlock, Container<Item> {
    
    /**
     * Get this brewing stand's inventory
     * @return
     */
    public Inventory getInventory();

}
