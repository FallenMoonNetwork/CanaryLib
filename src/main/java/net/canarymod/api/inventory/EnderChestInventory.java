package net.canarymod.api.inventory;

import net.canarymod.api.entity.living.humanoid.Player;

public interface EnderChestInventory extends Inventory {

    /**
     * Get the owner of this ender chest inventory
     * 
     * @return
     */
    public Player getInventoryOwner();
}
