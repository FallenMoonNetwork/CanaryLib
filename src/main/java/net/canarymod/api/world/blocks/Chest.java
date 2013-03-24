package net.canarymod.api.world.blocks;


import net.canarymod.api.inventory.Container;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;


public interface Chest extends ComplexBlock, Container<Item> {

    /**
     * Get this chests inventory
     * 
     * @return
     */
    public Inventory getInventory();

    /**
     * Check if this chest has an attached chest (is a double chest)
     * 
     * @return
     */
    public boolean hasAttachedChest();
}
