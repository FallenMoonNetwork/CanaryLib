package net.canarymod.api.world;

import net.canarymod.api.entity.IPlayer;

/**
 * This is a container for all of the dimensions containing a world
 * @author Chris
 * @author Jos Kuijpers
 *
 */
public interface IWorld {

	public enum DimensionType {
		NORMAL(0),
		NETHER(1),
		END(2);
		
		private int intValue = 0;
		
	    private DimensionType(int intValue) {
	        this.intValue = intValue;
	    }
	    
	    public int getIntValue() {
	        return intValue;
	    }
	    
	    public static DimensionType getFromIntValue(int value) {
	        switch (value) {
	        case 0:
	            return NORMAL;
	        case 1:
	            return NETHER;
	        case 2:
	            return END;
	        default:
	            return null;
	        }
	    }
	};

    /**
     * Return this worlds name (its the same for the dimensions)
     * @return
     */
    public String getName();
    
    /**
     * Get normal dimension
     * @return
     */
    public IDimension getNormal();
    
    /**
     * Get nether dimension
     * @return
     */
    public IDimension getNether();
    
    /**
     * Get End Dimension
     * @return
     */
    public IDimension getEnd();
    
	/**
	 * Get one of the dimensions
	 * @return
	 */
	public IDimension getDimension(DimensionType dimension);
	
    /**
     * Set a nano tick for this dimension
     * @param dimensionIndex (0=normal,1=nether,2=end)
     * @param tickIndex the Index for the tick (this.j/100% in notch code)
     * @param tick
     */
    public void setNanoTick(DimensionType dimension, int tickIndex, long tick);
	
	/**
	 * Enable or disable this world
	 *
	 * By default, a newly created world is enabled
	 * @param enabled
	 */
	public void setEnabled(boolean enabled);
	
	/**
	 * Whether this world is enabled. A disabled world can't be accessed by
	 *		anyone, and might not be loaded at all.
	 *
	 * @return
	 */
	public boolean isEnabled();
	
	/**
	 * Whether the specified player is allowed to enter this world
	 *
	 * @param player
	 * @return true if the player is allowed
	 */
	public boolean canEnterWorld(IPlayer player);
	
	/**
	 * Whether the specified player is allowed to leave this world
	 *
	 * @param player
	 * @return true if the player is allowed
	 */
	public boolean canLeaveWorld(IPlayer player);
	
	/**
     * Match a player name or a part of a player name to an existing online player
     * @param player
     * @return a reference to an online player or null if no match was found
     */
    public abstract IPlayer matchPlayer(String player);
    
    /**
     * Like matchPlayer, this returns a player according to a name String.
     * However, no matching is performed here so you need to pass
     * the exact player name
     * @param player
     * @return a reference to an online player or null if there is no player with the given name
     */
    public abstract IPlayer getPlayer(String player);
}
