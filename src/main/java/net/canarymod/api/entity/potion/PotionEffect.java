package net.canarymod.api.entity.potion;

import net.canarymod.api.entity.EntityLiving;

/**
 * An effect from a {@link Potion}
 * @author Brian McCarthy
 *
 */
public interface PotionEffect {

    /**
     * Get the potion ID
     * @return potion ID
     */
    public int getPotionID();
    
    /**
     * The duration of this potion effect
     * @return duration
     */
    public int getDuration();
    
    /**
     * Return this potions amplifier
     * @return amplifier
     */
    public int getAmplifier();
    
    /**
     * Get the name of this potion effect
     * @return name
     */
    public String getName();
    
    /**
     * Perfor this potion effect against a {@link LivingEntity}
     * @param entity
     */
    public void performEffect(EntityLiving entity);
}
