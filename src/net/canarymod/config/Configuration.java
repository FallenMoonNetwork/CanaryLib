package net.canarymod.config;

import java.io.IOException;
import java.util.HashMap;

import net.canarymod.Logman;
import net.canarymod.api.world.World;

/**
 * A caching configuration provider.
 * 
 * This class performs file lookup and caching. Use this class to get access to
 * a configuration file.
 * 
 * @author Jos Kuijpers
 * 
 */
public class Configuration {

    private static HashMap<String, ConfigurationFile> cache = new HashMap<String, ConfigurationFile>();
    
    private static ServerConfiguration serverCfg;
    private static NetworkConfiguration netConfig;
    
    public Configuration() {
        try {
            serverCfg = new ServerConfiguration(new ConfigurationFile("config/server.cfg"));
            netConfig = new NetworkConfiguration(new ConfigurationFile("config/net.cfg"));
        } catch (IOException e) {
            Logman.logStackTrace("Could not load a configuration file.", e);
        }
    }

    /**
     * Gets a cached configuration file.
     * 
     * @param filepath
     * @return The file or null when failed to load the file,
     */
    private static ConfigurationFile getCachedConfig(String filepath) {
        if (!cache.containsKey(filepath)) {
            try {
                ConfigurationFile file = new ConfigurationFile(filepath);
                cache.put(filepath, file);
            } catch (IOException ioe) {
                Logman.logStackTrace("Configuration file not found!", ioe);
            	// TODO need a way to know the file does not exist, so we can determine the config files for worlds
                return null;
            }
        }
        return cache.get(filepath);
    }

    /**
     * Gets the server configuration
     * 
     * @return server configuration file
     */
    public static ServerConfiguration getServerConfig() {
        return serverCfg;
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
     * Gets the server-wide configuration of a plugin
     * 
     * @param plugin
     * @return configuration of a plugin
     */
    public static ConfigurationFile getPluginConfig(String plugin) {
        return Configuration.getCachedConfig("config/" + plugin + ".cfg");
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
        return Configuration.getCachedConfig("config/" + plugin + "." + module + ".cfg");
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
        ConfigurationFile file = Configuration.getCachedConfig("config/worlds/" + world.getName() + "/" + plugin + ".cfg");
        if (file == null) file = Configuration.getCachedConfig("config/" + plugin + ".cfg");
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
        ConfigurationFile file = Configuration.getCachedConfig("config/worlds/" + world.getName() + "/" + plugin + "." + module + ".cfg");
        if (file == null) file = Configuration.getCachedConfig("config/" + plugin + "." + module + ".cfg");
        return file;
    }
}
