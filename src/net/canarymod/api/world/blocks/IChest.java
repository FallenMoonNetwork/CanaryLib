package net.canarymod.api.world.blocks;

import net.canarymod.api.inventory.IInventory;

public interface IChest extends IBlock, IComplexBlock{
    
    /**
     * Get this chests inventory
     * @return
     */
    public IInventory getInventory();
    
    /**
     * Check if this chest has an attached chest (is a double chest)
     * @return
     */
    public boolean hasAttachedChest();
}
