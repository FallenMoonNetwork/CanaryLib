package net.canarymod.api.world.blocks;

import net.canarymod.api.entity.Entity;
import net.canarymod.api.inventory.Inventory;

public interface Dispenser extends ComplexBlock, Inventory {

    /**
     * Activate the dispenser: spawns an item from a random slot.
     * 
     * @return the entity spawned
     */
    public Entity activate();

    /**
     * Dispense the item from the given slot.
     * 
     * @param slot
     *            the slot to dispend from
     * @return the entity spawned
     */
    public Entity dispenseFromSlot(int slot);

}
