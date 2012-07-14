package net.canarymod.config;

import java.io.File;
import java.util.HashMap;

import net.canarymod.api.world.World;

/**
 * A caching configuration provider.
 * 
 * This class performs file lookup and caching. Use this class to get access to
 * a configuration file.
 * 
 * @author Jos Kuijpers
 */
public class Configuration {

    private static HashMap<String, ConfigurationFile> cache = new HashMap<String, ConfigurationFile>();
    
    private static ServerConfiguration serverConfig;
    private static NetworkConfiguration netConfig;
    private static DatabaseConfiguration dbConfig;
    private static HashMap<String,WorldConfiguration> worldConfigs = new HashMap<String,WorldConfiguration>();
    
    public Configuration() {
        serverConfig = new ServerConfiguration("config"+File.separatorChar+"server.cfg");
        netConfig = new NetworkConfiguration("config"+File.separatorChar+"net.cfg");
        dbConfig = new DatabaseConfiguration("config"+File.separatorChar+"db.cfg");
    }
    
    /**
     * Reload all configuration from disk
     */
    public static void reload() {
        serverConfig.reload();
        netConfig.reload();
        dbConfig.reload();
        
        // Reload world configurations
        for(WorldConfiguration wc : worldConfigs.values()) {
            wc.reload();
        }
        
        // Clear the cache
        cache.clear();
    }

    /**
     * Gets a cached configuration file.
     * 
     * @param filepath
     * @return The file or null when failed to load the file,
     */
    private static ConfigurationFile getCachedConfig(String filepath) {
        if (!cache.containsKey(filepath)) {
            ConfigurationFile file = new ConfigurationFile(filepath);
            cache.put(filepath, file);
        }
        return cache.get(filepath);
    }

    /**
     * Gets the server configuration
     * 
     * @return server configuration file
     */
    public static ServerConfiguration getServerConfig() {
        return serverConfig;
    }

    /**
     * Gets the net configuration
     * 
     * @return networking configuration file
     */
    public static NetworkConfiguration getNetConfig() {
        return netConfig;
    }

    /**
     * Gets the net configuration
     * 
     * @return networking configuration file
     */
    public static DatabaseConfiguration getDbConfig() {
        return dbConfig;
    }

    /**
     * Get the world configuration for the specified world
     * @param world
     * @return world configuration
     */
    public static WorldConfiguration getWorldConfig(String world) {
    	if(worldConfigs.containsKey(world))
    		return worldConfigs.get(world);
    	
    	WorldConfiguration config = new WorldConfiguration("config"+File.separatorChar+"worlds"+File.separatorChar+world+File.separatorChar+"world.cfg", world);
    	
    	worldConfigs.put(world, config);
    	return config;
    }

    /**
     * Gets the server-wide configuration of a plugin
     * 
     * @param plugin
     * @return configuration of a plugin
     */
    public static ConfigurationFile getPluginConfig(String plugin) {
        return Configuration.getCachedConfig("config"+File.separatorChar + plugin + ".cfg");
    }

    /**
     * Gets the server-wide configuration of a plugin
     * 
     * @param plugin
     * @param module
     *            Used to create multiple configurations for a single plugin.
     * @return configuration of a plugin
     */
    public static ConfigurationFile getPluginConfig(String plugin, String module) {
        return Configuration.getCachedConfig("config"+File.separatorChar+ plugin + "." + module + ".cfg");
    }

    /**
     * Gets the world-specific configuration of a plugin
     * 
     * If there is no world-specific configuration, it will take the server-wide
     * configuration
     * 
     * @param plugin
     * @param world
     * @return configuration of a plugin
     */
    public static ConfigurationFile getPluginConfig(String plugin, World world) {
        ConfigurationFile file = Configuration.getCachedConfig("config"+File.separatorChar+"worlds"+File.separatorChar+ world.getName() +File.separatorChar + plugin + ".cfg");
        if (file == null) file = Configuration.getCachedConfig("config"+File.separatorChar + plugin + ".cfg");
        return file;
    }

    /**
     * Gets the world-specific configuration of a plugin
     * 
     * If there is no world-specific configuration, it will take the server-wide
     * configuration
     * 
     * @param plugin
     * @param module
     *            Used to create multiple configurations for a single plugin.
     * @param world
     * @return configuration of a plugin
     */
    public static ConfigurationFile getPluginConfig(String plugin, String module, World world) {
        ConfigurationFile file = Configuration.getCachedConfig("config"+File.separatorChar+"worlds"+File.separatorChar + world.getName()  +File.separatorChar+  plugin + "." + module + ".cfg");
        if (file == null) file = Configuration.getCachedConfig("config"+File.separatorChar + plugin + "." + module + ".cfg");
        return file;
    }
}
