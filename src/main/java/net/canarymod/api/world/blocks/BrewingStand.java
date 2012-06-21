package net.canarymod.api.world.blocks;

import net.canarymod.api.inventory.Inventory;

public interface BrewingStand extends ComplexBlock {
    
    /**
     * Get this brewing stand's inventory
     * @return
     */
    public Inventory getInventory();

}
