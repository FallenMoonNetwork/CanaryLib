package net.canarymod.config;

import java.io.IOException;
import java.util.HashMap;

import net.canarymod.api.world.IWorld;

// TODO: I am not sure about the name 'module'...

/**
 * A caching configuration provider.
 * 
 * This class performs file lookup and caching.
 * Use this class to get access to a configuration file.
 *  
 * @author Jos Kuijpers
 *
 */
public class Configuration {

	private HashMap<String, ConfigurationFile> cache = new HashMap<String, ConfigurationFile>();
	
	/**
	 * Gets a cached configuration file.
	 * 
	 * @param filepath
	 * @return The file or null when failed to load the file,
	 */
	private ConfigurationFile getCachedConfig(String filepath) {
		if(!cache.containsKey(filepath)) {
			try {
				ConfigurationFile file = new ConfigurationFile(filepath);
				cache.put(filepath,file);
			}
			catch(IOException ioe){
			    //TODO: Handle exception and pipe stacktrace to syslog
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
	public ConfigurationFile getServerConfig() {
		return this.getCachedConfig("config/server.cfg");
	}
	
	/**
	 * Gets the net configuration
	 * 
	 * @return networking configuration file
	 */
	public ConfigurationFile getNetConfig() {
		return this.getCachedConfig("config/net.cfg");
	}
	
	/**
	 * Gets the server-wide configuration of a plugin
	 * 
	 * @param plugin
	 * @return configuration of a plugin
	 */
	public ConfigurationFile getPluginConfig(String plugin) {
		return this.getCachedConfig("config/"+plugin+".cfg");
	}
	
	/**
	 * Gets the server-wide configuration of a plugin
	 * 
	 * @param plugin
	 * @param module Used to create multiple configurations for a single plugin.
	 * @return configuration of a plugin 
	 */
	public ConfigurationFile getPluginConfig(String plugin, String module) {
		return this.getCachedConfig("config/"+plugin+"."+module+".cfg");
	}
	
	/**
	 * Gets the world-specific configuration of a plugin
	 * 
	 * If there is no world-specific configuration, it will take the server-wide configuration
	 * 
	 * @param plugin
	 * @param world
	 * @return configuration of a plugin 
	 */
	public ConfigurationFile getPluginConfig(String plugin, IWorld world) {
		ConfigurationFile file = this.getCachedConfig("config/worlds/"+world.getName()+"/"+plugin+".cfg");
		if(file == null)
			file = this.getCachedConfig("config/"+plugin+".cfg");
		return file;
	}
	
	/**
	 * Gets the world-specific configuration of a plugin
	 * 
	 * If there is no world-specific configuration, it will take the server-wide configuration
	 *  
	 * @param plugin
	 * @param module Used to create multiple configurations for a single plugin.
	 * @param world
	 * @return configuration of a plugin 
	 */
	public ConfigurationFile getPluginConfig(String plugin, String module, IWorld world) {
		ConfigurationFile file = this.getCachedConfig("config/worlds/"+world.getName()+"/"+plugin+"."+module+".cfg");
		if(file == null)
			file = this.getCachedConfig("config/"+plugin+"."+module+".cfg");
		return file;
	}
}
