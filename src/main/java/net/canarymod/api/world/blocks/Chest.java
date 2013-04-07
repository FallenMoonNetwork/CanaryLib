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
     * Get the chest that is attached to this chest.
     * Returns null if there is no attached chest.
     * You can check with hasAttachedChest()
     *
     * @return
     */
    public Chest getAttachedChest();
}
