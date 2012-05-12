package net.canarymod.api.world;

/**
 * This is a container for all of the worlds.
 * @author Jos Kuijpers
 *
 */
public interface IWorldManager {

	/**
	 * Gets the world with the specified name
	 * 
	 * @param name
	 * @return an IWorld instance or null
	 */
    public IWorld getWorld(String name);
	
    /**
     * Create a new world with the given name and seed
     * @param name
     * @param seed
     * @return
     */
    public boolean createWorld(String name, long seed);
    
    /**
     * Create a new world with the given name, seed will be selected randomly
     * @param name
     * @return
     */
    public boolean createWorld(String name);
	
	/**
     * Return a specific dimension of the given world
     * @param world
     * @param dimension
     * @return
     */
    public IDimension getDimension(String world, int dimension);
    
    /**
	 * Destroys the world with the given name
	 *
	 * @param name
	 */
	public void destroyWorld(String name); // TODO: so this might NOT be such a good idea to implement... I left it protected...
	
	/**
	 * Returns a list of all world
	 *
	 * @return an array of IWorld objects
	 */
	public IWorld[] getAllWorlds();
}
