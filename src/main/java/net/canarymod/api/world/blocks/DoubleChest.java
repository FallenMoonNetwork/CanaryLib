package net.canarymod.api.world.blocks;

import net.canarymod.api.inventory.Inventory;

public interface DoubleChest extends Chest {

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
