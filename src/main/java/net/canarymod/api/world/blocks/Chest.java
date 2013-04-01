package net.canarymod.api.world.blocks;


import net.canarymod.api.inventory.Inventory;


public interface Chest extends ComplexBlock, Inventory {

    /**
     * Check if this chest has an attached chest (is a double chest)
     * 
     * @return
     */
    public boolean hasAttachedChest();
}
