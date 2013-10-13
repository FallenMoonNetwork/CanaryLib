package net.canarymod.api.world.blocks;

import net.canarymod.api.inventory.Inventory;

/**
 * Furnace wrapper
 *
 * @author Jason (darkdiplomat)
 */
public interface Furnace extends TileEntity, Inventory {
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
     *         ticks of burning left
     */
    public void setBurnTime(short time);

    /**
     * Returns the number of ticks the item to smelt has smolten.
     * An item is ready on 200 ticks (10 Seconds).
     *
     * @return cook time ticks
     */
    public short getCookTime();

    /**
     * Sets the number of ticks the item to smelt has smolten.<br/>
     * An item is ready on 200 ticks (10 Seconds).<br/>
     * For setting the time, subtract the number of tick you want to cook for from 200<br/>
     * ie: 1 Second cooking: 200 - 20 = 180
     *
     * @param time
     *         ticks of cooking
     */
    public void setCookTime(short time);
}
