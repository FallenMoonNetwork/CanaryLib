package net.canarymod.config;

import java.io.IOException;

import net.canarymod.Logman;


public class ServerConfiguration implements ConfigurationContainer {
    private ConfigurationFile cfg;
    private String[] spawnableMobs;
    private String[] spawnableAnimals;

    public ServerConfiguration(ConfigurationFile cfg) {
        init(cfg);
    }
    
    private void init(ConfigurationFile cfg) {
        this.cfg = cfg;
        spawnableMobs = cfg.getString("natural-monsters").split(",");
        spawnableAnimals = cfg.getString("natural-animals").split(",");
    }
    
    @Override
    public void reload() {
        try {
            init(new ConfigurationFile("config/server.cfg"));
        } catch (IOException e) {
            Logman.logStackTrace("Could not find the server configuration while reloading! (Wtf man?!)", e);
        }
    }
    
    /**
     * Get the default world name defined in the config
     * @return
     */
    public String getDefaultWorldName(){
        return cfg.getString("world-name", "world");
    }
    
    public String getPort() {
        return cfg.getString("port", "22025");
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
