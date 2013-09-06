package net.canarymod.api.statistics;

/**
 * Stat wrapper
 *
 * @author Jason (darkdiplomat)
 */
public interface Stat {

    /**
     * Get the id of this stat.
     * May not be very useful apart from comparing equality of stats with different names.
     * E.G. if Minecraft changes the name
     *
     * @return the Stat ID
     */
    public int getId();

    /**
     * Gets the name of the stat.
     * NOTE: This has gone through Minecraft's string translate to get a readable form/
     *
     * @return the name of the Stat
     */
    public String getName();

    /**
     * Return is this stat is independent (i.e., lacking prerequisites for being updated)
     *
     * @return {@code true} if independent; {@code false} if not
     */
    public boolean isIndependent();

}
