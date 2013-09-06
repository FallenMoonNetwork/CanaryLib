package net.canarymod.api.world.blocks;

/**
 * Comparator wrapper
 *
 * @author Jason (darkdiplomat)
 */
public interface Comparator extends TileEntity {

    /**
     * Gets the output signal of the Comparator
     *
     * @return output signal
     */
    public int getOutputSignal();

    /**
     * Sets the output signal of the Comparator
     *
     * @param signal
     *         the output signal
     */
    public void setOutputSignal(int signal);

}
