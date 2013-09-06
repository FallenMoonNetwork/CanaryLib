package net.canarymod.config;

import java.io.File;
import java.util.HashMap;

import net.canarymod.Canary;
import net.canarymod.MathHelp;
import net.canarymod.api.GameMode;
import net.canarymod.api.world.World;
import net.canarymod.api.world.WorldType;
import net.visualillusionsent.utils.PropertiesFile;

/**
 * @author Jason (darkdiplomat)
 * @author Jos Kuijpers
 */
public class WorldConfiguration implements ConfigurationContainer {
    private PropertiesFile cfg;
    private String worldname;
    private HashMap<String, Boolean> boolCache = new HashMap<String, Boolean>();

    /* Arrays of default mobs, leave static */
    private final static String[]
            animals = new String[]{ "Bat", "Chicken", "Cow", "Mooshroom", "Ocelot", "Pig", "Sheep", "Wolf", "Horse" },
            wateranimals = new String[]{ "Squid" },
            monsters = new String[]{ "Enderman", "PigZombie", "Blaze", "CaveSpider", "Creeper", "Ghast", "MagamaCube", "SilverFish", "Skeleton", "Slime", "Spider", "Witch", "Zombie", "Wither", "EnderDragon", "GiantZombie" },
            golems = new String[]{ "IronGolem", "Snowman" };
    /* Arrays of default enderblocks and disallowed blocks, leave static */
    private final static int[]
            enderblocks = new int[]{ 2, 3, 12, 13, 37, 38, 39, 40, 46, 81, 82, 86, 103, 110 },
            disallowedblocks = new int[]{ 7, 8, 9, 10, 11, 46, 51, 52 };

    public WorldConfiguration(String path, String worldname) {
        this.worldname = worldname;
        File test = new File(path);

        if (!test.exists()) {
            Canary.logInfo("Could not find the world configuration for " + worldname + " at " + path + ", creating default.");
        }
        cfg = new PropertiesFile(path + File.separatorChar + worldname + ".cfg");
        verifyConfig();
    }

    /** Reloads the configuration file */
    @Override
    public void reload() {
        cfg.reload();
        verifyConfig();
    }

    /** Get the configuration file */
    @Override
    public PropertiesFile getFile() {
        return cfg;
    }

    /** Verifies the world configuration file */
    private void verifyConfig() {
        cfg.getString("world-name", worldname);
        cfg.getString("world-type", "DEFAULT");
        cfg.getInt("spawn-protection", 16);
        cfg.getInt("max-build-height", 256);
        cfg.getBoolean("generate-structures", true);
        cfg.getString("generator-settings", "");
        cfg.getString("world-seed", "");

        cfg.getBoolean("allow-nether", true);
        cfg.getBoolean("allow-end", true);
        cfg.getBoolean("allow-flight", true);

        cfg.getBoolean("pvp", true);
        cfg.getInt("difficulty", 1);
        cfg.getInt("gamemode", 0);
        cfg.getString("auto-heal", "default");
        cfg.getBoolean("enable-experience", true);
        cfg.getBoolean("enable-health", true);

        cfg.getBoolean("spawn-villagers", true);
        cfg.getBoolean("spawn-golems", true);
        cfg.getBoolean("spawn-animals", true);
        cfg.getBoolean("spawn-monsters", true);
        cfg.getStringArray("natural-animals", animals);
        cfg.getStringArray("natural-monsters", monsters);
        cfg.getStringArray("natural-golems", golems);
        cfg.getStringArray("natural-wateranimals", wateranimals);
        cfg.getInt("natural-spawn-rate", 100);

        cfg.getIntArray("ender-blocks", enderblocks);
        cfg.getIntArray("disallowed-blocks", disallowedblocks);

        cfg.getBoolean("disable-ocean", false);
        cfg.getInt("ocean-replacement-id", 4);
        cfg.getBoolean("disable-plains", false);
        cfg.getInt("plains-replacement-id", 4);
        cfg.getBoolean("disable-deserts", false);
        cfg.getInt("deserts-replacement-id", 4);
        cfg.getBoolean("disable-extremehills", false);
        cfg.getInt("extremehills-replacement-id", 4);
        cfg.getBoolean("disable-forests", false);
        cfg.getInt("forests-replacement-id", 3);
        cfg.getBoolean("disable-taiga", false);
        cfg.getInt("taiga-replacement-id", 4);
        cfg.getBoolean("disable-swampland", false);
        cfg.getInt("swampland-replacement-id", 4);
        cfg.getBoolean("disable-river", false);
        cfg.getInt("river-replacement-id", 4);
        cfg.getBoolean("disable-frozenocean", false);
        cfg.getInt("frozenocean-replacement-id", 4);
        cfg.getBoolean("disable-frozenriver", false);
        cfg.getInt("frozenriver-replacement-id", 4);
        cfg.getBoolean("disable-iceplains", false);
        cfg.getInt("iceplains-replacement-id", 4);
        cfg.getBoolean("disable-icemountains", false);
        cfg.getInt("icemountains-replacement-id", 4);
        cfg.getBoolean("disable-mushroomisland", false);
        cfg.getInt("mushroomisland-replacement-id", 4);
        cfg.getBoolean("disable-mushroomislandshore", false);
        cfg.getInt("mushroomislandshore-replacement-id", 4);
        cfg.getBoolean("disable-beach", false);
        cfg.getInt("beach-replacement-id", 4);
        cfg.getBoolean("disable-deserthills", false);
        cfg.getInt("deserthills-replacement-id", 4);
        cfg.getBoolean("disable-foresthills", false);
        cfg.getInt("foresthills-replacement-id", 4);
        cfg.getBoolean("disable-taigahills", false);
        cfg.getInt("taigahills-replacement-id", 4);
        cfg.getBoolean("disable-extremehillsedge", false);
        cfg.getInt("extremehillsedge-replacement-id", 4);
        cfg.getBoolean("disable-jungle", false);
        cfg.getInt("jungle-replacement-id", 4);
        cfg.getBoolean("disable-junglehills", false);
        cfg.getInt("junglehills-replacement-id", 4);

        cfg.save();
    }

    private boolean getBoolean(String key, boolean def) {
        Boolean r = boolCache.get(key);
        if (r != null) {
            return r;
        }
        r = cfg.getBoolean(key, def);
        boolCache.put(key, r);
        return r;
    }

    /**
     * Get the spawn protection size
     *
     * @return an integer between 0 and INTMAX, 16 on failure.
     */
    public int getSpawnProtectionSize() {
        return cfg.getInt("spawn-protection", 16);
    }

    /**
     * Get whether auto heal is enabled.
     *
     * @return true or false. Returns value of canSpawnMonsters() if auto-heal is 'default'
     */
    public boolean isAutoHealEnabled() {
        if (cfg.getString("auto-heal", "default").equals("default")) {
            return this.canSpawnMonsters();
        }
        return getBoolean("auto-heal", false);
    }

    /**
     * Get whether experience is enabled
     *
     * @return true when enabled, false otherwise. Default is true.
     */
    public boolean isExperienceEnabled() {
        return getBoolean("enable-experience", true);
    }

    /**
     * Get whether health is enabled.
     *
     * @return true when enabled, false otherwise. Default is true.
     */
    public boolean isHealthEnabled() {
        return getBoolean("enable-health", true);
    }

    /**
     * Get an Array of String of spawnable animals
     *
     * @return animals array
     */
    public String[] getSpawnableAnimals() {
        return cfg.getStringArray("natural-animals", animals);
    }

    /**
     * Get an Array of String of spawnable water animals
     *
     * @return water animals array
     */
    public String[] getSpawnableWaterAnimals() {
        return cfg.getStringArray("natural-wateranimals", wateranimals);
    }

    /**
     * Get an Array of String of spawnable monsters
     *
     * @return monster array
     */
    public String[] getSpawnableMobs() {
        return cfg.getStringArray("natural-monsters", monsters);
    }

    /**
     * Get an Array of String of spawnable golems
     *
     * @return golem array
     */
    public String[] getSpawnableGolems() {
        return cfg.getStringArray("natural-golems", golems);
    }

    /**
     * Get the block types allowed for enderman to move.
     *
     * @return An integer array containing the block types.
     */
    public int[] getEnderBlocks() {
        return cfg.getIntArray("ender-blocks", enderblocks);
    }

    /**
     * Get the block types banned.
     *
     * @return An integer array containing the block types.
     */
    public int[] getBannedBlocks() {
        return cfg.getIntArray("disallowed-blocks", disallowedblocks);
    }

    /**
     * See if a given animal is allowed to spawn
     * This method looks in both the normal and water animal lists.
     *
     * @param name
     *         the name of the Animal
     *
     * @return true or false
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
     *
     * @param name
     *         the name of the Mob
     *
     * @return true or false
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
     *
     * @return a string with the world name
     */
    public String getWorldName() {
        return cfg.getString("world-name", worldname);
    }

    /**
     * Get the world type.
     *
     * @return a String with the world type. Default is DEFAULT
     */
    public WorldType getWorldType() {
        return WorldType.fromString(cfg.getString("world-type", "DEFAULT"));
    }

    /**
     * Get the world seed.
     *
     * @return a string containing the world seed
     */
    public String getWorldSeed() {
        return cfg.getString("world-seed", "");
    }

    /**
     * Get whether the nether is allowed
     *
     * @return true when allowed, false otherwise
     */
    public boolean isNetherAllowed() {
        return getBoolean("allow-nether", true);
    }

    /**
     * Get whether the end is allowed
     *
     * @return true when allowed, false otherwise
     */
    public boolean isEndAllowed() {
        return getBoolean("allow-end", true);
    }

    /**
     * Get whether flight is allowed
     *
     * @return true when allowed, false otherwise
     */
    public boolean isFlightAllowed() {
        return getBoolean("allow-flight", true);
    }

    /**
     * Get whether NPCs can be spawned
     *
     * @return true or false
     */
    public boolean canSpawnVillagers() {
        return getBoolean("spawn-villagers", true);
    }

    /**
     * Get whether animals can be spawned
     *
     * @return true or false
     */
    public boolean canSpawnAnimals() {
        return getBoolean("spawn-animals", true);
    }

    /**
     * Get whether monsters can be spawned
     *
     * @return true or false
     */
    public boolean canSpawnMonsters() {
        return getBoolean("spawn-monsters", true);
    }

    /**
     * Get whether golems can be spawned
     *
     * @return true or false
     */
    public boolean canSpawnGolems() {
        return getBoolean("spawn-golems", true);
    }

    /**
     * Get whether structures must be generated
     *
     * @return true or false
     */
    public boolean generatesStructures() {
        return getBoolean("generate-structures", true);
    }

    /**
     * Get the maximum build height
     *
     * @return an integer, defaulting to 256
     */
    public int getMaxBuildHeight() {
        return MathHelp.setInRange(cfg.getInt("max-build-height", 256), 1, 256);
    }

    /**
     * Get whether PVP is enabled
     *
     * @return true when enabled, false otherwise. Default is true.
     */
    public boolean isPvpEnabled() {
        return getBoolean("pvp", true);
    }

    /**
     * Get the difficulty
     *
     * @return difficulty
     */
    public World.Difficulty getDifficulty() {
        return World.Difficulty.fromId(cfg.getInt("difficulty", 1));
    }

    /**
     * Get the game mode for this world
     *
     * @return game mode
     */
    public GameMode getGameMode() {
        return GameMode.fromId(cfg.getInt("gamemode", 0));
    }

    /**
     * Get the natural spawn rate, a percentage.
     *
     * @return A value from 0 to 100, default is 100.
     */
    public int getNaturalSpawnRate() {
        return MathHelp.setInRange(cfg.getInt("natural-spawn-rate", 100), 0, 100);
    }

    /**
     * Gets the World Generator settings
     *
     * @return world generator settings
     */
    public String getGeneratorSettings() {
        return cfg.getString("generator-settings", "");
    }

    public boolean isBiomeDisabled(int n) {
        switch (n) {
            case 0:
                return cfg.getBoolean("disable-ocean", false);
            case 1:
                return cfg.getBoolean("disable-plains", false);
            case 2:
                return cfg.getBoolean("disable-desert", false);
            case 3:
                return cfg.getBoolean("disable-extremehills", false);
            case 4:
                return cfg.getBoolean("disable-forest", false);
            case 5:
                return cfg.getBoolean("disable-taiga", false);
            case 6:
                return cfg.getBoolean("disable-swampland", false);
            case 7:
                return cfg.getBoolean("disable-river", false);
            case 8:
            case 9:
                return false; // Hell and Sky are Nether and End biomes
            case 10:
                return cfg.getBoolean("disable-frozenocean", false);
            case 11:
                return cfg.getBoolean("disable-frozenriver", false);
            case 12:
                return cfg.getBoolean("disable-iceplains", false);
            case 13:
                return cfg.getBoolean("disable-icemountains", false);
            case 14:
                return cfg.getBoolean("disable-mushroomisland", false);
            case 15:
                return cfg.getBoolean("disable-mushroomislandshore", false);
            case 16:
                return cfg.getBoolean("disable-beach", false);
            case 17:
                return cfg.getBoolean("disable-deserthills", false);
            case 18:
                return cfg.getBoolean("disable-foresthills", false);
            case 19:
                return cfg.getBoolean("disable-taigahills", false);
            case 20:
                return cfg.getBoolean("disable-extremehillsedge", false);
            case 21:
                return cfg.getBoolean("disable-jungle", false);
            case 22:
                return cfg.getBoolean("disable-junglehills", false);
            default:
                return false;
        }
    }

    public int getReplacementBiomeId(int n) {
        switch (n) {
            case 0:
                return MathHelp.setInRange(cfg.getInt("ocean-replacement-id", 4), 0, 22);
            case 1:
                return MathHelp.setInRange(cfg.getInt("plains-replacement-id", 4), 0, 22);
            case 2:
                return MathHelp.setInRange(cfg.getInt("desert-replacement-id", 4), 0, 22);
            case 3:
                return MathHelp.setInRange(cfg.getInt("extremehills-replacement-id", 4), 0, 22);
            case 4:
                return MathHelp.setInRange(cfg.getInt("forest-replacement-id", 3), 0, 22);
            case 5:
                return MathHelp.setInRange(cfg.getInt("taiga-replacement-id", 4), 0, 22);
            case 6:
                return MathHelp.setInRange(cfg.getInt("swampland-replacement-id", 4), 0, 22);
            case 7:
                return MathHelp.setInRange(cfg.getInt("river-replacement-id", 4), 0, 22);
            case 8:
                return 8;
            case 9:
                return 9;
            case 10:
                return MathHelp.setInRange(cfg.getInt("frozenocean-replacement-id", 4), 0, 22);
            case 11:
                return MathHelp.setInRange(cfg.getInt("frozenriver-replacement-id", 4), 0, 22);
            case 12:
                return MathHelp.setInRange(cfg.getInt("iceplains-replacement-id", 4), 0, 22);
            case 13:
                return MathHelp.setInRange(cfg.getInt("icemountains-replacement-id", 4), 0, 22);
            case 14:
                return MathHelp.setInRange(cfg.getInt("mushroomisland-replacement-id", 4), 0, 22);
            case 15:
                return MathHelp.setInRange(cfg.getInt("mushroomislandshore-replacement-id", 4), 0, 22);
            case 16:
                return MathHelp.setInRange(cfg.getInt("beach-replacement-id", 4), 0, 22);
            case 17:
                return MathHelp.setInRange(cfg.getInt("deserthills-replacement-id", 4), 0, 22);
            case 18:
                return MathHelp.setInRange(cfg.getInt("foresthills-replacement-id", 4), 0, 22);
            case 19:
                return MathHelp.setInRange(cfg.getInt("taigahills-replacement-id", 4), 0, 22);
            case 20:
                return MathHelp.setInRange(cfg.getInt("extremehillsedge-replacement-id", 4), 0, 22);
            case 21:
                return MathHelp.setInRange(cfg.getInt("jungle-replacement-id", 4), 0, 22);
            case 22:
                return MathHelp.setInRange(cfg.getInt("junglehills-replacement-id", 4), 0, 22);
            default:
                return 4;
        }
    }
}
