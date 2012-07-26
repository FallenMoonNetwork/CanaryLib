package net.canarymod.api.world;

import java.util.ArrayList;
import java.util.Collection;

import net.canarymod.api.world.World.GeneratorType;

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
     * @return World dimension object
     */
    public World getWorld(String name);
    
    /**
     * Get world with name by WorldType.
     * Setting autoload true will force the world to be created or loaded,
     * depending on its status
     * @param name
     * @param type
     * @param autoload true if worlds should be loaded if they are not
     * @return
     */
    public World getWorld(String name, WorldType type, boolean autoload);

    /**
     * Create a new world with the given name and seed
     * 
     * @param name
     * @param seed
     * @return
     */
    public boolean createWorld(String name, long seed, WorldType type);
    
    /**
     * Create a new world with the given name and seed and GeneratorType
     * @param name
     * @param seed
     * @param type
     * @return
     */
    public boolean createWorld(String name, long seed, WorldType worldType, GeneratorType genType);

    /**
     * Create a new world with the given name, seed will be selected randomly
     * 
     * @param name
     * @return
     */
    public boolean createWorld(String name, WorldType type);


    /**
     * Destroys the world with the given name
     * 
     * @param name
     */
    public void destroyWorld(String name); // TODO: so this might NOT be such a good idea to implement... I left it protected...
    
    /**
     * Load the world with the given name that is of the given world type.
     * If type is Type.NORMAL then the loaded world will be name_NORMAL
     * @param name
     * @param type
     * @return
     */
    public World loadWorld(String name, WorldType type);
    /**
     * Remove a world from memory and save it to disk
     * @param name
     */
    public void unloadWorld(String name, WorldType type);

    /**
     * Returns a list of all world
     * 
     * @return an array of IWorld objects
     */
    public Collection<World> getAllWorlds();
    
    /**
     * Check if a world with the given name is loaded.
     * This will perform a check for name+type.name()
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
