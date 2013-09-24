package net.canarymod.api.potion;

import net.canarymod.api.entity.living.LivingBase;

/**
 * An effect from a {@link Potion}
 *
 * @author Brian (WWOL)
 * @author Jason (darkdiplomat)
 */
public interface PotionEffect {

    /**
     * Get the potion ID
     *
     * @return potion ID
     */
    public int getPotionID();

    /**
     * The duration of this potion effect
     *
     * @return duration
     */
    public int getDuration();

    /**
     * Return this potions amplifier
     *
     * @return amplifier
     */
    public int getAmplifier();

    /**
     * Checks if the Potion is ambient
     *
     * @return {@code true} if ambient; {@code false} if not
     */
    public boolean isAmbient();

    /**
     * Get the name of this potion effect
     *
     * @return name
     */
    public String getName();

    /**
     * Perform this potion effect against a {@link LivingBase}
     *
     * @param entity
     *         the {@link LivingBase} to perform the effect on
     */
    public void performEffect(LivingBase entity);
}
