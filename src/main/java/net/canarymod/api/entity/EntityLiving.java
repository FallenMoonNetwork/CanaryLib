package net.canarymod.api.entity;

import java.util.List;

import net.canarymod.api.DamageType;
import net.canarymod.api.entity.potion.Potion;
import net.canarymod.api.entity.potion.PotionEffect;
import net.canarymod.api.entity.potion.PotionType;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Vector3D;

/**
 * An entity living defines any entities that own health, such as animals and mobs.
 * Every entitiy with health inherits from this.
 * @author Chris Ksoll
 *
 */
public interface EntityLiving extends Entity {

    /**
     * Get this Entities health. May not work on entities that are not
     * LivingEntity
     * 
     * @return health
     */
    public int getHealth();

    /**
     * Get the maximum allowed amount of health for this entity
     * 
     * @return maximum health
     */
    public int getMaxHealth();

    /**
     * Set this entities health. May not work on entities that are not
     * LivingEntity
     * 
     * @param health
     */
    public void setHealth(int health);

    /**
     * Increase this entities health. This does not set but add the amount of
     * health
     * 
     * @param amount
     *            to increase the health with (negative values decrease)
     */
    public void increaseHealth(int health);

    /**
     * Get this entities age. (Has nothing to do with the breeding stuff!! Use
     * {@link Ageable#getGrowingAge()} instead!)
     * 
     * @return age
     */
    public int getAge();

    /**
     * Set how long this entity exists already. (Has nothing to do with the
     * breeding stuff!! Use {@link Ageable#setGrowingAge(int)} instead!)
     * 
     * @param age
     */
    public void setAge(int age);

    /**
     * Get the amount of ticks this entity is dead.
     * 
     * @return
     */
    public int getDeathTicks();

    /**
     * Set the amount of ticks this entity is dead
     * 
     * @param ticks
     */
    public void setDeathTicks(int ticks);

    /**
     * Murder this entity
     */
    public void kill();
    
    /**
     * Destroys this entity
     */
    public void destroy();

    /**
     * Inflict the given damage to this entity
     * 
     * @param damagetype
     * @param damage
     */
    public void dealDamage(DamageType type, int damage);

    /**
     * Knock back this entity with the given forces on x and z axis
     * 
     * @param xForce
     * @param zForce
     */
    public void knockBack(double xForce, double zForce);

    /**
     * Move this entity with the forces given. Note that those are not the
     * coordinates to move to!!!
     * 
     * @param motionX
     * @param motionY
     * @param motionZ
     */
    public void moveEntity(double motionX, double motionY, double motionZ);

    /**
     * Get the position of the chunk this entity has been homed to
     * 
     * The home can be the position they spawned or their AI home.
     * 
     * @return
     */
    public Location getHome();

    /**
     * Override the home of this entity. The home is the position of the chunk.
     * 
     * Position of a chunk are the bitshifted Euler-coordinates.
     * 
     * @param origin
     */
    public void setHome(Location origin);

    /**
     * Set the max. distance that this entity is allowed to be away from its
     * home (in blocks)
     */
    public void setHomeRadius(int radius);

    /**
     * Set the home area for this entity, that is its home location and the
     * distance it is allowed to travel away from that home
     * 
     * @param x
     * @param y
     * @param z
     * @param radius
     */
    public void setHomeArea(int x, int y, int z, int radius);

    /**
     * Set the home area for this entity, that is its home location and the
     * distance it is allowed to travel away from that home
     * 
     * @param position
     * @param radius
     */
    public void setHomeArea(Vector3D position, int radius);

    /**
     * Make this entity homeless (that means it can travel throughout the whole
     * world!!)
     */
    public void removeHome();

    /**
     * Checks if this entity has a home.
     * 
     * @return
     */
    public boolean hasHome();

    /**
     * Plays the sound of this entity (For example the growl of a Zombie if this
     * is a Zombie)
     */
    public void playLivingSound();

    /**
     * Check if this entity can see the provided entity.
     * 
     * @param entity
     * @return True if the entity can see the provided entity (provided is not
     *         hidden)
     */
    public boolean canSee(EntityLiving entity);

    /**
     * Get this entity's machine readable name
     * 
     * @return the name
     */
    public String getName();

    /**
     * Check if this entity is a mob
     * 
     * @return true when it is a mob
     */
    public boolean isMob();

    /**
     * Get the generic mob interface for this entity. Can be casted to specific
     * mob. Check with instanceof!
     * 
     * @return this or null
     */
    public EntityMob getMob();

    /**
     * Check if this entity is an animal (implements the animal interface)
     * 
     * @return true when it is an animal
     */
    public boolean isAnimal();

    /**
     * Get this entity as EntityAnimal if it is an animal at all
     * 
     * @return this or null
     */
    public EntityAnimal getAnimal();

    /**
     * Check if this entity is a player entity
     * 
     * @return true when it is a player
     */
    public boolean isPlayer();

    /**
     * Get this entity as Player if it is a player at all
     * 
     * @return this or null
     */
    public Player getPlayer();
    
    /**
     * Spawn this entity in the world.
     */
    public void spawn();
    
    /**
     * Spawn this entity with an attached rider on its back
     * @param rider
     */
    public void spawn(EntityLiving rider);
    
    /**
     * Add a {@link PotionEffect} to this entity
     * @param effect
     */
    public void addPotionEffect(PotionEffect effect);
    
    /**
     * Add a {@link PotionEffect} to this entity using custom values 
     * @param type
     * @param level
     * @param amp
     */
    public void addPotionEffect(PotionType type, int duration, int amplifier);
    
    /**
     * Is this potion active on this entity
     * @param potion
     * @return
     */
    public boolean isPotionActive(Potion potion);
    
    /**
     * Gets the supplied potions {@link PotionEffect} if it is active, else null
     * @param potion
     * @return PotionEffect or null
     */
    public PotionEffect getActivePotionEffect(Potion potion);
    
    /**
     * Get a list of all active {@link PotionEffect}s.
     * @return a List<PotionEffect>
     */
    public List<PotionEffect> getAllActivePotionEffects();
}
