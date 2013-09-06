package net.canarymod.api.world.blocks;

import net.canarymod.api.inventory.Hopper;
import net.canarymod.api.inventory.Inventory;

/**
 * Interface for Hopper Blocks.
 *
 * @author Somners
 */
public interface HopperBlock extends Hopper, TileEntity {

    /**
     * Gets the Inventory inputting to the hopper.
     *
     * @return the Inventory or null if none.
     */
    public Inventory getInputInventory();

    /**
     * Gets the Inventory the hopper outputs to.
     *
     * @return the Inventory or null if none.
     */
    public Inventory getOutputInventory();

    /**
     * Check if this hopper is connected to any Container either input or output.
     *
     * @return true - it is connected<br>
     *         false - it is not connected
     */
    public boolean isConnected();

    /**
     * Check if the block this hopper inputs from is a Container.
     *
     * @return true - it is connected<br>
     *         false - it is not connected
     */
    public boolean isInputConnected();

    /**
     * Check if the block this hopper outputs to is a Container.
     *
     * @return true - it is connected<br>
     *         false - it is not connected
     */
    public boolean isOutputConnected();
}
