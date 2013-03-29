package net.canarymod.config;


import java.io.File;

import net.canarymod.Canary;
import net.canarymod.api.world.World;
import net.visualillusionsent.utils.PropertiesFile;


/**
 * @author Jason (darkdiplomat)
 * @author Jos Kuijpers
 */
public class WorldConfiguration implements ConfigurationContainer {
    private PropertiesFile cfg;
    private String worldname;

    public WorldConfiguration(String path, String worldname) {
        this.worldname = worldname;
        File test = new File(path);

        if (!test.exists()) {
            Canary.logInfo("Could not find the world configuration for " + worldname + " at " + path + ", creating default.");
            WorldConfiguration.createDefault(path, worldname);
        }

        cfg = new PropertiesFile(path + File.separatorChar + worldname + ".cfg");


    }

    public WorldConfiguration(PropertiesFile cfg) {
        this.cfg = cfg;
    }

    /**
     * Reloads the configuration file
     */
    @Override
    public void reload() {
        cfg.reload();
    }

    /**
     * Get the configuration file
     */
    @Override
    public PropertiesFile getFile() {
        return cfg;
    }

    /**
     * Creates the default configuration
     */
    public static void createDefault(String path, String worldname) {
        PropertiesFile config = new PropertiesFile(path + File.separatorChar + worldname + ".cfg");
        config.setString("world-name", worldname);
        config.setString("world-type", "DEFAULT");
        config.setInt("spawn-protection-size", 16);

        config.setBoolean("allow-nether", true);
        config.setBoolean("allow-end", true);
        config.setBoolean("allow-flight", true);
        config.setBoolean("spawn-npcs", true);
        config.setBoolean("spawn-animals", true);
        config.setBoolean("spawn-monsters", true);
        config.setBoolean("generate-structures", true);
        config.setInt("max-build-height", 256);

        config.setBoolean("pvp", true);
        config.setInt("difficulty", 1);
        config.setInt("gamemode", 0);

        config.setString("auto-heal", "default");
        config.setBoolean("enable-experience", true);
        config.setBoolean("enable-health", true);
        config.setString("ender-blocks", "2,3,12,13,37,38,39,40,46,81,82,86,103,110");
        config.setString("disallowed-blocks", "7,8,9,10,11,46,51,52");

        config.setString("natural-animals", "Sheep,Pig,Chicken,Cow,Wolf,MushroomCow,Ocelot");
        config.setString("natural-monsters", "Spider,Zombie,Skeleton,Creeper,Slime,Enderman,CaveSpider,Silverfish,PigZombie,Ghast,Blaze,LavaSlime,EnderDragon");
        config.setInt("natural-spawn-rate", 100);
        config.setString("natural-wateranimals", "Squid");
        config.setString("generator-settings", "");

        config.save();
    }

    /**
     * Get the spawn protection size
     * @return an integer between 0 and INTMAX, 16 on failure.
     */
    public int getSpawnProtectionSize() {
        return cfg.getInt("spawn-protection-size", 16);
    }

    /**
     * Get whether auto heal is enabled.
     * @return true or false. Returns value of canSpawnMonsters() if auto-heal is 'default'
     */
    public boolean isAutoHealEnabled() {
        if (cfg.getString("auto-heal", "default") == "default") {
            return this.canSpawnMonsters();
        }
        return cfg.getBoolean("auto-heal");
    }

    /**
     * Get whether experience is enabled
     * @return true when enabled, false otherwise. Default is true.
     */
    public boolean isExperienceEnabled() {
        return cfg.getBoolean("enable-experience", true);
    }

    /**
     * Get whether health is enabled.
     * @return true when enabled, false otherwise. Default is true.
     */
    public boolean isHealthEnabled() {
        return cfg.getBoolean("enable-health", true);
    }

    /**
     * Get an Array of String of spawnable animals
     * @return
     */
    public String[] getSpawnableAnimals() {
        return cfg.getStringArray("natural-animals", new String[]{ "Sheep", "Pig", "Chicken", "Cow", "Wolf", "MushroomCow", "Ocelot", "Bat" });
    }

    /**
     * Get an Array of String of spawnable water animals
     * @return
     */
    public String[] getSpawnableWaterAnimals() {
        return cfg.getStringArray("natural-wateranimals", new String[]{ "Squid" });
    }

    /**
     * Get an Array of String of spawnable animals
     * @return
     */
    public String[] getSpawnableMobs() {
        return cfg.getStringArray("natural-monsters", new String[]{ "Spider", "Zombie", "Skeleton", "Creeper", "Slime", "Enderman", "CaveSpider", "Silverfish", "PigZombie", "Ghast", "Blaze", "LavaSlime", "EnderDragon" });
    }

    /**
     * Get the block types allowed for enderman to move.
     * @return An integer array containing the block types.
     */
    public int[] getEnderBlocks() {
        return cfg.getIntArray("ender-blocks", new int[]{ 2, 3, 12, 13, 37, 38, 39, 40, 46, 81, 82, 86, 103, 110 });
    }

    /**
     * Get the block types banned.
     * @return An integer array containing the block types.
     */
    public int[] getBannedBlocks() {
        return cfg.getIntArray("disallowed-blocks", new int[]{ 7, 8, 9, 10, 11, 46, 51, 52 });
    }

    /**
     * See if a given animal is allowed to spawn
     * 
     * This method looks in both the normal and water animal lists.
     * @param name
     * @return
     */
    public boolean isAnimalSpawnable(String name) {
        for (String animal : cfg.getStringArray("natural-animals")) {
            if (name.equals(animal)) {
                return true;
            }
        }
        for (String animal : cfg.getStringArray("natural-wateranimals")) {
            if (name.equals(animal)) {
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
        for (String mob : cfg.getStringArray("natural-monsters")) {
            if (name.equals(mob)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the world name
     * @return a string with the world name
     */
    public String getWorldName() {
        return cfg.getString("world-name", worldname);
    }

    /**
     * Get the world type.
     * @return a String with the world type. Default is DEFAULT
     */
    public World.GeneratorType getWorldType() {
        return World.GeneratorType.fromString(cfg.getString("world-type", "DEFAULT"));
    }

    /**
     * Get the world seed.
     * @return a string containing the world seed
     */
    public String getWorldSeed() {
        return cfg.getString("world-seed", "");
    }

    /**
     * Get whether the nether is allowed
     * @return true when allowed, false otherwise
     */
    public boolean isNetherAllowed() {
        return cfg.getBoolean("allow-nether", true);
    }

    /**
     * Get whether the end is allowed
     * @return true when allowed, false otherwise
     */
    public boolean isEndAllowed() {
        return cfg.getBoolean("allow-end", true);
    }

    /**
     * Get whether flight is allowed
     * @return true when allowed, false otherwise
     */
    public boolean isFlightAllowed() {
        return cfg.getBoolean("allow-flight", false);
    }

    /**
     * Get whether NPCs can be spawned
     * @return true or false
     */
    public boolean canSpawnNpcs() {
        return cfg.getBoolean("spawn-npcs", true);
    }

    /**
     * Get whether animals can be spawned
     * @return true or false
     */
    public boolean canSpawnAnimals() {
        return cfg.getBoolean("spawn-animals", true);
    }

    /**
     * Get whether monsters can be spawned
     * @return true or false
     */
    public boolean canSpawnMonsters() {
        return cfg.getBoolean("spawn-monsters", true);
    }

    /**
     * Get whether structures must be generated
     * @return true or false
     */
    public boolean generatesStructures() {
        return cfg.getBoolean("generate-structures", true);
    }

    /**
     * Get the maximum build height
     * @return an integer, defaulting to 256
     */
    public int getMaxBuildHeight() {
        return cfg.getInt("max-build-height", 256);
    }

    /**
     * Get whether PVP is enabled
     * @return true when enabled, false otherwise. Default is true.
     */
    public boolean isPvpEnabled() {
        return cfg.getBoolean("pvp", true);
    }

    /**
     * Get the difficulty
     * @return
     */
    public World.Difficulty getDifficulty() {
        return World.Difficulty.fromId(cfg.getInt("difficulty", 1));
    }

    /**
     * Get the game mode for this world
     * @return
     */
    public World.GameMode getGameMode() {
        return World.GameMode.fromId(cfg.getInt("gamemode", 0));
    }

    /**
     * Get the natural spawn rate, a percentage.
     * @return A value from 0 to 100, default is 100.
     */
    public int getNaturalSpawnRate() {
        return cfg.getInt("natural-spawn-rate", 100);
    }

    public String getGeneratorSettings() {
        return cfg.getString("generator-settings", "");
    }

    /*
     "ender-blocks","1,2,3,4,5,12,13,14,15,16,17,18,19,20,21,22,24,35,37,38,39,40,41,42,45,46,47,48,56,57,58,73,74,79,81,82,86,87,88,89,91,98,99,100,103"
     "disallowed-blocks","7,8,9,10,11,46,51,52"
     */
}
