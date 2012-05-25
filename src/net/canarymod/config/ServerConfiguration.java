package net.canarymod.config;

import java.io.IOException;

import net.canarymod.Logman;
import net.canarymod.database.Database;


public class ServerConfiguration implements ConfigurationContainer {
    private ConfigurationFile cfg;
    private String[] spawnableMobs;
    private String[] spawnableAnimals;
    private Database.Type backboneType;

    public ServerConfiguration(ConfigurationFile cfg) {
        init(cfg);
    }
    
    private void init(ConfigurationFile cfg) {
        this.cfg = cfg;
        spawnableMobs = cfg.getString("natural-monsters").split("[ \t]*,[ \t]*");
        spawnableAnimals = cfg.getString("natural-animals").split("[ \t]*,[ \t]*");
        
        String typeVal = cfg.getString("data-source", "flatfile");
        if(typeVal.equalsIgnoreCase("flatfile")) {
        	backboneType = Database.Type.FLATFILE;
        } else if(typeVal.equalsIgnoreCase("mysql")) {
        	backboneType = Database.Type.MYSQL;
        }
    }
    
    /**
     * Reloads the configuration file
     */
    @Override
    public void reload() {
        try {
            init(new ConfigurationFile("config/server.cfg"));
        } catch (IOException e) {
            Logman.logStackTrace("Could not find the server configuration while reloading!", e);
        }
    }
    
    /**
     * Creates the default configuration
     */
    public static void createDefault() {
    	
    }
    
    /**
     * Get datasource type
     * @return
     */
    public Database.Type getDatasourceType() {
	        return backboneType;
    }
    
    /**
     * Get the default world name defined in the config
     * @return
     */
    public String getDefaultWorldName(){
        return cfg.getString("world-name", "world");
    }
    
    /**
     * See if a given animal is allowed to spawn
     * @param name
     * @return
     */
    public boolean isAnimalSpawnable(String name) {
        for(String animal : spawnableAnimals) {
            if(name.equals(animal)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * See if a given mob is allowed to spawn
     * @param name
     * @return
     */
    public boolean isMobSpawnable(String name) {
        for(String mob : spawnableMobs) {
            if(name.equals(mob)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Whether this server is in debug mode.
     * 
     * Use debug mode when developing plugins, CanaryAPI or CanaryMod.
     * @return
     */
    public boolean isDebugMode() {
        return cfg.getBoolean("debug-mode", false);
    }
    
    /**
     * Get an Array of String of spawnable animals
     * @return
     */
    public String[] getSpawnableAnimals() {
        return spawnableAnimals;
    }
    
    /**
     * Get an Array of String of spawnable animals
     * @return
     */
    public String[] getSpawnableMobs() {
        return spawnableMobs;
    }
}
