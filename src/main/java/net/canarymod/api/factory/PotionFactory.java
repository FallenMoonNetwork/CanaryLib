package net.canarymod.api.factory;


import net.canarymod.api.entity.potion.PotionEffect;


public interface PotionFactory {

    /**
     * Make a new {@link PotionEffect} with id, duration, amplifier
     * @param id
     * @param duration
     * @param amplifier
     * @return
     */
    PotionEffect newPotionEffect(int id, int duration, int amplifier);
    
}
