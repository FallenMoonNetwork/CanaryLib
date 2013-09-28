package net.canarymod.api.entity.living;

import java.util.List;

import net.canarymod.api.DamageType;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.potion.Potion;
import net.canarymod.api.potion.PotionEffect;
import net.canarymod.api.potion.PotionEffectType;
import net.canarymod.api.world.position.Location;

/**
 * EntityLivingBase wrapper interface
 *
 * @author Jason (darkdiplomat)
 */
public interface LivingBase extends Entity {

    /**
     * Get this Entities health. May not work on entities that are not
     * LivingEntity
     *
     * @return health
     */
    public float getHealth();

    /**
     * Set this entities health. May not work on entities that are not
     * LivingEntity
     *
     * @param health
     *         the health to be set
     */
    public void setHealth(float health);

    /**
     * Increase this entities health. This does not set but add the amount of
     * health
     *
     * @param health
     *         to increase the health with (negative values decrease)
     */
    public void increaseHealth(float health);

    /**
     * Gets the Maximum allowed health for the Entity
     * @return maximum health
     */
    public double getMaxHealth();

    /**
     * Sets the Maximum allowed health for the Entity
     * @param maxHealth
     * the maximum health
     */
    public void setMaxHealth(double maxHealth);

    /**
     * Check if this entity can see the provided entity.
     *
     * @param entity
     *         the {@link LivingBase} to check sight of
     *
     * @return {@code true} if the entity can see the provided entity (provided is not
     *         hidden); {@code false} otherwise
     */
    public boolean canSee(LivingBase entity);

    /**
     * Get the amount of ticks this entity is dead.
     *
     * @return death ticks
     */
    public int getDeathTicks();

    /**
     * Set how many ticks this entity is dead
     *
     * @param ticks
     *         the amount of death ticks to set
     */
    public void setDeathTicks(int ticks);

    /**
     * Get the amount of ticks this entity remains invincible
     *
     * @return invulnerability ticks
     */
    public int getInvulnerabilityTicks();

    /**
     * Set the amount of ticks this entity remains invincible
     *
     * @param ticks
     *         the amount of invulnerability ticks to set
     */
    public void setInvulnerabilityTicks(int ticks);

    /**
     * Get this entities age. (Has nothing to do with the breeding stuff!! Use {@link Ageable#getGrowingAge()} instead!)
     *
     * @return age of the entity
     */
    public int getAge();

    /**
     * Set how long this entity exists already. (Has nothing to do with the
     * breeding stuff!! Use {@link Ageable#setGrowingAge(int)} instead!)
     *
     * @param age
     *         the age to be set
     */
    public void setAge(int age);

    /** Murder this entity */
    public void kill();

    /**
     * Inflict the given damage to this entity
     *
     * @param type
     *         the {@link DamageType}
     * @param damage
     *         the amount of damage
     */
    public void dealDamage(DamageType type, float damage);

    /**
     * Knock back this entity with the given forces on x and z axis
     *
     * @param xForce
     *         the X-wise force
     * @param zForce
     *         the Z-wise force
     */
    public void knockBack(double xForce, double zForce);

    /**
     * Add a {@link PotionEffect} to this entity
     *
     * @param effect
     *         the {@link PotionEffect} to add
     */
    public void addPotionEffect(PotionEffect effect);

    /**
     * Add a {@link PotionEffect} to this entity using custom values
     *
     * @param type
     *          the {@link PotionEffectType}
     * @param duration
     *          the duration of the effect
     * @param amplifier
     *          the amplifier of the effect
     */
    public void addPotionEffect(PotionEffectType type, int duration, int amplifier);

    /**
     * Removes a specified {@link PotionEffectType} from this entity.
     *
     * @param type
     *          the {@link PotionEffectType} to remove
     */
    public void removePotionEffect(PotionEffectType type);

    /**
     * Removes all potion effects from this entity
     */
    public void removeAllPotionEffects();
    /**
     * Is this potion active on this entity
     *
     * @param potion
     *         the {@link Potion} to check activity
     *
     * @return {code true} if potion is active; {@code false} otherwise
     */
    public boolean isPotionActive(Potion potion);

    /**
     * Gets the supplied potions {@link PotionEffect} if it is active, else null
     *
     * @param potion
     *         the {@link Potion} to check effects for
     *
     * @return {@link PotionEffect} or null
     */
    public PotionEffect getActivePotionEffect(Potion potion);

    /**
     * Get a list of all active {@link PotionEffect}s.
     *
     * @return a List<PotionEffect>
     */
    public List<PotionEffect> getAllActivePotionEffects();

    /**
     * Set this Entities revenge target entity. Depending on entity type this must not
     * necessarily be an attack target. Null to remove target
     *
     * @param target
     *         the {@link LivingBase} target or {@code null} to remove target
     */
    public void setRevengeTarget(LivingBase target);

    /**
     * Get the current revenge target of this entity
     *
     * @return the revenge target or {@code null} if no target
     */
    public LivingBase getRevengeTarget();

    /**
     * Set the last entity to have attacked this entity.
     * Not sure why this would be used. Null to remove.
     *
     * @param entity
     *         the {@link LivingBase} to set as last assailant
     */
    public void setLastAssailant(LivingBase entity);

    /**
     * Get the last living entity to attack this entity
     *
     * @return last assailant
     */
    public LivingBase getLastAssailant();

    /**
     * Look at the specified x, y, z coordinates
     *
     * @param x
     *         the X coordinate
     * @param y
     *         the Y coordinate
     * @param z
     *         the Z coordinate
     */
    public void lookAt(double x, double y, double z);

    /**
     * Look at the specified location
     *
     * @param location
     *         the {@link Location} to look at
     */
    public void lookAt(Location location);

    /**
     * Look at the specified {@link Entity}
     *
     * @param entity
     *         the {@link Entity} to look at
     */
    public void lookAt(Entity entity);

    /**
     * Gets how many Arrows are stuck in an Entity
     *
     * @return arrow count
     */
    public int getArrowCountInEntity();

    /**
     * Sets how many Arrows are stuck in an Entity
     *
     * @param arrows
     *         the count of arrows to set
     */
    public void setArrowCountInEntity(int arrows);

    /** Swings the Item holding arm */
    public void swingArm();

    /**
     * Attacks the {@link LivingBase} target and causes the specified damage.
     *
     * @param target
     *         {@link LivingBase} to attack.
     * @param damage
     *         The amount of damage to do.
     */
    public void attackEntity(LivingBase target, float damage);

}
