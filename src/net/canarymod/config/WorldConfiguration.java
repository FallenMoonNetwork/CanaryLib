package net.canarymod.config;

import java.io.IOException;

import net.canarymod.Logman;

/**
 * 
 * @author Jos Kuijpers
 *
 */
public class WorldConfiguration implements ConfigurationContainer {
    private ConfigurationFile cfg;
    private String path;

    public WorldConfiguration(String path) {
    	this.path = path;
        try {
            init(new ConfigurationFile(path));
        } catch (IOException e) {
            Logman.logStackTrace("Could not find the world configuration while reloading!", e);
        }
    }
    
    public WorldConfiguration(ConfigurationFile cfg, String path) {
    	this.path = path;
        init(cfg);
    }
    
    private void init(ConfigurationFile cfg) {
        this.cfg = cfg;
    }
    
    /**
     * Reloads the configuration file
     */
    @Override
    public void reload() {
        try {
            init(new ConfigurationFile(path));
        } catch (IOException e) {
            Logman.logStackTrace("Could not find the world configuration while reloading!", e);
        }
    }
    
    /**
     * Creates the default configuration
     */
    public static void createDefault() {
    	
    }
}
