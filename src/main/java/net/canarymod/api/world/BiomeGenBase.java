package net.canarymod.api.world;

import java.util.Random;

/**
 * BiomeGenBase contains data about a certain {@link BiomeType}
 * 
 * @author Chris (damagefilter)
 */
public interface BiomeGenBase {

    /**
     * Check if this biome can spawn lightning bolts.
     * 
     * @return {@code true} is spawns lightning; {@code false} if not
     */
    public boolean canSpawnLightning();

    /**
     * Check if this is a tropic biome by determining the amount of rainfall.
     * More rainfall means more tropic biome
     * 
     * @return {@code true} is tropic; {@code false} if not
     */
    public boolean isTropic();

    /**
     * Check if this Biome has snowfall instead of rain (therefore is a winter biome)
     * 
     * @return true if this biome has snowfall instead of rain, false otherwise
     */
    public boolean isWinter();

    /**
     * Returns the chance a create can spawn here.
     * This is on a scale from 0 to 1
     * 
     * @return spawn chance
     */
    public float getSpawnChance();

    /**
     * get an integer representing the amount of rainfall in this biome
     * 
     * @return rainfall
     */
    public int getRainfall();

    /**
     * Returns a float value representing this biomes temperature
     * 
     * @return temperature
     */
    public float getTemperature();

    /**
     * Decorates the chunk coordinates in the given world with decorations of this biome generator
     * 
     * @param world
     *            the world
     * @param rnd
     *            the random
     * @param x
     *            the chunk x coordinate
     * @param z
     *            the chunk z coordinate
     */
    public void decorate(World world, Random rnd, int x, int z);

    /**
     * get the BiomeType for this BiomeGen
     * 
     * @return {@link BiomeType}
     */
    public BiomeType getBiomeType();
}
