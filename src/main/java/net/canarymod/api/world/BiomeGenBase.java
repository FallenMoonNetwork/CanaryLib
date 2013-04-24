package net.canarymod.api.world;

import java.util.Random;

/**
 * BiomeGenBase contains data about a certain {@link BiomeType}
 * @author Chris (damagefilter)
 *
 */
public interface BiomeGenBase {

    /**
     * Check if this biome can spawn lightning bolts.
     * @return
     */
    public boolean canSpawnLightning();

    /**
     * Check if this is a tropic biome by determining the amount of rainfall.
     * More rainfall means more tropic biome
     * @return
     */
    public boolean isTropic();

    /**
     * Check if this Biome has snowfall instead of rain (therefore is a winter biome)
     * @return true if this biome has snowfall instead of rain, false otherwise
     */
    public boolean isWinter();

    /**
     * Returns the chance a create can spawn here.
     * This is on a scale from 0 to 1
     * @return
     */
    public float getSpawnChance();

    /**
     * get an integer representing the amount of rainfall in this biome
     * @return
     */
    public int getRainfall();

    /**
     * Returns a float value representing this biomes temperature
     * @return
     */
    public float getTemperature();

    /**
     * Decorates the chunk coordinates in the given world with decorations of this biome generator
     * @param world
     * @param rnd
     * @param x
     * @param z
     */
    public void decorate(World world, Random rnd, int x, int z);

    /**
     * get the BiomeType for this BiomeGen
     * @return
     */
    public BiomeType getBiomeType();
}
