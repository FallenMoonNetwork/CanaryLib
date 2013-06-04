package net.canarymod.api.world.blocks;

import net.canarymod.api.inventory.Inventory;

/**
 * Furnace wrapper
 * 
 * @author Jason (darkdiplomat)
 */
public interface Furnace extends ComplexBlock, Inventory {
    /**
     * Returns the number of ticks the current fuel item has to go.
     * 
     * @return burn time ticks
     */
    public short getBurnTime();

    /**
     * Sets the number of ticks the current fuel item has to go.
     * 
     * @param time
     *            ticks of burning left
     */
    public void setBurnTime(short time);

    /**
     * Returns the number of ticks the item to smelt has smolten.
     * An item is ready on 200 ticks.
     * 
     * @return cook time ticks
     */
    public short getCookTime();

    /**
     * Sets the number of ticks the item to smelt has smolten.
     * An item is ready on 200 ticks.
     * 
     * @param time
     *            ticks of cooking
     */
    public void setCookTime(short time);
}
