package net.canarymod.hook.entity;

import net.canarymod.api.entity.living.EntityLiving;
import net.canarymod.api.potion.PotionEffect;
import net.canarymod.hook.Hook;

/**
 * PotionEffectFinishHook<br>
 * Called when a {@link PotionEffect} finishes it's effect on a {@link LivingEntity}
 * 
 * @author Jason (darkdiplomat)
 */
public final class PotionEffectFinishHook extends Hook {
    private EntityLiving entity;
    private PotionEffect effect;

    /**
     * Constructs a new PotionEffectFinshHook
     * 
     * @param entity
     *            the {@link LivingEntity} having the {@link PotionEffect} finishing it's effect
     * @param effect
     *            the {@link PotionEffect} being finished
     */
    public PotionEffectFinishHook(EntityLiving entity, PotionEffect effect) {
        this.entity = entity;
        this.effect = effect;
    }

    /**
     * Gets the {@link LivingEntity} having a {@link PotionEffect} finishing it's effect
     * 
     * @return the {@link LivingEntity}
     */
    public EntityLiving getEntity() {
        return entity;
    }

    /**
     * Gets the {@link PotionEffect} finishing
     * 
     * @return the {@link PotionEffect}
     */
    public PotionEffect getPotionEffect() {
        return effect;
    }

    @Override
    public final String toString() {
        return String.format("%s[EntityLiving=%s, PotionEffect=%s]", getName(), entity, effect);
    }
}
