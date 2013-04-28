package net.canarymod.api.factory;


import net.canarymod.api.potion.PotionEffect;
import net.canarymod.api.potion.PotionEffectType;


public interface PotionFactory {

    /**
     * Make a new {@link PotionEffect} with id, duration, amplifier
     * @param id
     * @param duration
     * @param amplifier
     * @return
     */
    PotionEffect newPotionEffect(int id, int duration, int amplifier);
    
    /**
     * Make a new {@link PotionEffect} with type, duration, amplifier
     * 
     * @param type
     * @param duration
     * @param amplifier
     * @return
     */
    PotionEffect newPotionEffect(PotionEffectType type, int duration, int amplifier);
    
}
