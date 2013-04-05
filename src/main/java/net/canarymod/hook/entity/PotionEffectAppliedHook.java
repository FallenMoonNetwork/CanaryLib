package net.canarymod.hook.entity;

import net.canarymod.api.entity.living.EntityLiving;
import net.canarymod.api.potion.PotionEffect;
import net.canarymod.hook.Hook;

/**
 * PotionEffectAppliedHook<br>
 * Called when a {@link PotionEffect} is applied to a {@link LivingEntity}
 * 
 * @author Jason (darkdiplomat)
 */
public final class PotionEffectAppliedHook extends Hook {
    private EntityLiving entity;
    private PotionEffect effect;

    /**
     * Constructs a new PotionEffectAppliedHook
     * 
     * @param entity
     *            the {@link LivingEntity} having the {@link PotionEffect} applied to
     * @param effect
     *            the {@link PotionEffect} being applied
     */
    public PotionEffectAppliedHook(EntityLiving entity, PotionEffect effect) {
        this.entity = entity;
        this.effect = effect;
    }

    /**
     * Gets the {@link LivingEntity} having a {@link PotionEffect} applied to
     * 
     * @return the {@link LivingEntity}
     */
    public EntityLiving getEntity() {
        return entity;
    }

    /**
     * Gets the {@link PotionEffect} being applied
     * 
     * @return the {@link PotionEffect}
     */
    public PotionEffect getPotionEffect() {
        return effect;
    }

    /**
     * Sets the {@link PotionEffect} to apply
     * 
     * @param effect
     *            a new {@link PotionEffect} or null to cancel out any effects
     */
    public void setPotionEffect(PotionEffect effect) {
        this.effect = effect;
    }

    @Override
    public final String toString() {
        return String.format("%s[EntityLiving=%s, PotionEffect=%s]", getName(), entity, effect);
    }
}
