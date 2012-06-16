package net.canarymod.api.world;

import java.util.ArrayList;

/**
 * This is a container for all of the worlds.
 * 
 * @author Jos Kuijpers
 * 
 */
public interface WorldManager {

    /**
     * Gets the world with the specified name
     * 
     * @param name
     * @return an IWorld instance or null
     */
    public World getWorld(String name);

    /**
     * Create a new world with the given name and seed
     * 
     * @param name
     * @param seed
     * @return
     */
    public boolean createWorld(String name, long seed);

    /**
     * Create a new world with the given name, seed will be selected randomly
     * 
     * @param name
     * @return
     */
    public boolean createWorld(String name);

    /**
     * Return a specific dimension of the given world
     * 
     * @param world
     * @param dimension
     * @return
     */
    public Dimension getDimension(String world, int dimension);

    /**
     * Destroys the world with the given name
     * 
     * @param name
     */
    public void destroyWorld(String name); // TODO: so this might NOT be such a good idea to implement... I left it protected...
    
    /**
     * Load an existing world into memory.
     * This returns the loaded world or null,
     * if the world didn't load. (If it doesn't exist perhaps)
     * @param name
     * @return
     */
    public World loadWorld(String name);
    
    /**
     * Remove a world from memory and save it to disk
     * @param name
     */
    public void unloadWorld(String name);

    /**
     * Returns a list of all world
     * 
     * @return an array of IWorld objects
     */
    public World[] getAllWorlds();
    
    /**
     * Check if a world with the given name is loaded
     * @param name
     * @return
     */
    public boolean worldIsLoaded(String name);
    
    /**
     * Check if a world with the given name exists,
     * that doesn't mean it is loaded, it just means it's in the worlds folder
     * and therefore has been used at some point before
     * @param name
     * @return
     */
    public boolean worldExists(String name);
    
    /**
     * Return a static list of all existing worlds
     * @return
     */
    public ArrayList<String> getExistingWorlds();
}
