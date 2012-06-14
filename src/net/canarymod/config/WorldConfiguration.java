package net.canarymod.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import net.canarymod.Logman;

/**
 * 
 * @author Jos Kuijpers
 *
 */
public class WorldConfiguration implements ConfigurationContainer {
    private ConfigurationFile cfg;
    private String path;
    private String[] spawnableMobs;
    private String[] spawnableAnimals;
    private String[] spawnableWaterAnimals;
    private Integer[] enderBlocks = {};
    private Integer[] bannedBlocks = {};
    private String worldname;

    public WorldConfiguration(String path, String worldname) {
    	this.path = path;
    	this.worldname = worldname;
        try {
            init(new ConfigurationFile(path));
        } catch (FileNotFoundException e) {
            Logman.logInfo("Could not find the world configuration at " + path + ", creating default.");
            WorldConfiguration.createDefault(path, worldname);
            try {
                init(new ConfigurationFile(path));    
            }
            catch(IOException ioe) {
                Logman.logStackTrace("Failed to load world configuration, even after creation!", e);                
            }
        } catch (IOException e) {
            Logman.logStackTrace("Failed to load world configuration!", e);
        }
    }
    
    public WorldConfiguration(ConfigurationFile cfg, String path) {
    	this.path = path;
        init(cfg);
    }
    
    private void init(ConfigurationFile cfg) {
        this.cfg = cfg;
        
        spawnableMobs = cfg.getString("natural-monsters","Spider,Zombie,Skeleton,Creeper,Slime,Enderman,CaveSpider,Silverfish,PigZombie,Ghast,Blaze,LavaSlime,EnderDragon").split(",");
        spawnableAnimals = cfg.getString("natural-animals","Sheep,Pig,Chicken,Cow,Wolf,MushroomCow,Ocelot").split(",");
        spawnableWaterAnimals = cfg.getString("natural-wateranimals","Squid").split(",");
        
        // Get the block lists and transform them to integers for easy handling
        String[] eb = cfg.getString("ender-blocks","1,2,3,4,5,12,13,14,15,16,17,18,19,20,21,22,24,35,37,38,39,40,41,42,45,46,47,48,56,57,58,73,74,79,81,82,86,87,88,89,91,98,99,100,103").split(",");
        String[] bb = cfg.getString("disallowed-blocks","7,8,9,10,11,46,51,52").split(",");
        
        ArrayList<Integer> ebi = new ArrayList<Integer>();
        ArrayList<Integer> bbi = new ArrayList<Integer>();

        for(String s : eb)
        	ebi.add(Integer.valueOf(s.trim()));
        for(String s : bb)
        	bbi.add(Integer.valueOf(s.trim()));
        
        enderBlocks = ebi.toArray(enderBlocks);
        bannedBlocks = bbi.toArray(bannedBlocks);
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
     * Get the configuration file
     */
    public ConfigurationFile getFile() {
    	return cfg;
    }
    
    /**
     * Creates the default configuration
     */
    public static void createDefault(String path, String worldname) {
        ConfigurationFile config;
        try {
            config = new ConfigurationFile(path,true);
        }
        catch(IOException ioe) {
            Logman.logStackTrace("Failed to create default world configuration.", ioe);
            return;
        }
        
        config.setString("world-name", worldname);
        config.setString("world-type","DEFAULT");
        config.setInt("spawn-protection-size",16);
        
        config.setBoolean("allow-nether",true);
        config.setBoolean("allow-end",true);
        config.setBoolean("allow-flight",true);
        config.setBoolean("spawn-npcs",true);
        config.setBoolean("spawn-animals",true);
        config.setBoolean("spawn-monsters",true);
        config.setBoolean("generate-structures",true);
        config.setInt("max-build-height",256);
        
        config.setBoolean("pvp",true);
        config.setInt("difficulty",1);
        config.setInt("gamemode",0);
        
        config.setString("auto-heal","default");
        config.setBoolean("enable-experience",true);
        config.setBoolean("enable-health",true);
        config.setString("ender-blocks","1,2,3,4,5,12,13,14,15,16,17,18,19,20,21,22,24,35,37,38,39,40,41,42,45,46,47,48,56,57,58,73,74,79,81,82,86,87,88,89,91,98,99,100,103");
        config.setString("disallowed-blocks","7,8,9,10,11,46,51,52");
        
        config.setString("natural-animals","Sheep,Pig,Chicken,Cow,Wolf,MushroomCow,Ocelot");
        config.setString("natural-monsters","Spider,Zombie,Skeleton,Creeper,Slime,Enderman,CaveSpider,Silverfish,PigZombie,Ghast,Blaze,LavaSlime,EnderDragon");
        config.setInt("natural-spawn-rate",100);
        config.setString("natural-wateranimals","Squid");

        try {
            config.save();
        }
        catch(IOException ioe) {
            Logman.logStackTrace("Failed to create default world configuration.", ioe);
        }
    }
    
    public int getSpawnProtectionSize() {
        return cfg.getInt("spawn-protection-size",16);
    }
    
    public boolean isAutoHealEnabled() {
    	if(cfg.getString("auto-heal", "default") == "default")
    		return this.canSpawnMonsters();
    	return cfg.getBoolean("auto-heal"); 
    }
    
    public boolean isExperienceEnabled() {
    	return cfg.getBoolean("enable-experience",true);
    }
   
    public boolean isHealthEnabled() {
    	return cfg.getBoolean("enable-health",true);
    }
    
    /**
     * Get an Array of String of spawnable animals
     * @return
     */
    public String[] getSpawnableAnimals() {
        return spawnableAnimals;
    }

    /**
     * Get an Array of String of spawnable water animals
     * @return
     */
    public String[] getSpawnableWaterAnimals() {
        return spawnableWaterAnimals;
    }
    
    /**
     * Get an Array of String of spawnable animals
     * @return
     */
    public String[] getSpawnableMobs() {
        return spawnableMobs;
    }
    
    /**
     * See if a given animal is allowed to spawn
     * 
     * This method looks in both the normal and water animal lists.
     * @param name
     * @return
     */
    public boolean isAnimalSpawnable(String name) {
    	for(String animal : spawnableAnimals) {
            if(name.equals(animal)) {
                return true;
            }
        }
    	for(String animal : spawnableWaterAnimals) {
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
    
    public String getWorldName() {
    	return cfg.getString("world-name", worldname);
    }
    
    public String getWorldType() {
    	// TODO transform to enum 
    	return cfg.getString("world-type","DEFAULT");
    }
    
    public String getWorldSeed() {
    	return cfg.getString("world-seed","");
    }

    public boolean isNetherAllowed() {
    	return cfg.getBoolean("allow-nether",true);
    }

    public boolean isEndAllowed() {
    	return cfg.getBoolean("allow-end",true);
    }
    
    public boolean isFlightAllowed() {
    	return cfg.getBoolean("allow-flight",false);
    }
    
    public boolean canSpawnNpcs() {
    	return cfg.getBoolean("spawn-npcs",true);
    }
    
    public boolean canSpawnAnimals() {
    	return cfg.getBoolean("spawn-animals",true);
    }
    
    public boolean canSpawnMonsters() {
    	return cfg.getBoolean("spawn-monsters",true);
    }
    
    public boolean generatesStructures() {
    	return cfg.getBoolean("generate-structures",true);
    }
    
    public int getMaxBuildHeight() {
    	return cfg.getInt("max-build-height",256);
    }

    public boolean isPvpEnabled() {
    	return cfg.getBoolean("pvp",true);
    }
    
    public int getDifficulty() {
    	// TODO: transform to enum
    	return cfg.getInt("difficulty",1);
    }
    
    public int getGameMode() {
    	// TODO: transform to enum, 0 = survival, 1 = creative
    	return cfg.getInt("gamemode",0);
    }

    public int getNaturalSpawnRate() {
    	return cfg.getInt("natural-spawn-rate",100);
    }
    
/*
"ender-blocks","1,2,3,4,5,12,13,14,15,16,17,18,19,20,21,22,24,35,37,38,39,40,41,42,45,46,47,48,56,57,58,73,74,79,81,82,86,87,88,89,91,98,99,100,103"
"disallowed-blocks","7,8,9,10,11,46,51,52"
*/
}
