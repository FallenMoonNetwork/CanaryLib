package net.canarymod.api.world.blocks;


import net.canarymod.api.inventory.Inventory;


public interface Chest extends ComplexBlock, Inventory {

    /**
     * Check if this chest has an attached chest (is a double chest)
     *
     * @return
     */
    public boolean hasAttachedChest();

    /**
     * Gets the DoubleChest that results from this chest and its attached chest.
     * Returns null if there is no attached chest
     *
     * @return
     */
    public DoubleChest getDoubleChest();
}
