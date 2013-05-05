package net.canarymod.api.entity.living;


import java.util.List;

import net.canarymod.api.DamageType;
import net.canarymod.api.PathFinder;
import net.canarymod.api.ai.AIManager;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.living.animal.EntityAnimal;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.entity.living.monster.EntityMob;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.potion.Potion;
import net.canarymod.api.potion.PotionEffect;
import net.canarymod.api.potion.PotionEffectType;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Position;


/**
 * An entity living defines any entities that own health, such as animals and mobs.
 * Every entitiy with health inherits from this.
 *
 * @author Chris (damagefilter)
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
     * Sets the maximum health.
     *
     * @param max
     */
    public void setMaxHealth(int max);

    /**
     * Get the amount of ticks this entity is dead.
     *
     * @return
     */
    public int getDeathTicks();

    /**
     * Set how many ticks this entity is dead
     * @param ticks
     */
    public void setDeathTicks(int ticks);

    /**
     * Get the amount of ticks this entity remains invincible
     * @return
     */
    public int getInvulnerabilityTicks();

    /**
     * Set the amount of ticks this entity remains invincible
     * @param ticks the ticks
     */
    public void setInvulnerabilityTicks(int ticks);


    /**
     * Get this entities age. (Has nothing to do with the breeding stuff!! Use {@link Ageable#getGrowingAge()} instead!)
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
     * Murder this entity
     */
    public void kill();

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
     * Get the position of the chunk this entity has been homed to
     * The home can be the position they spawned or their AI home.
     *
     * @return
     */
    public Location getHome();

    /**
     * Override the home of this entity. The home is the position of the chunk.
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
    public void setHomeArea(Position position, int radius);

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
    @Override
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
     * Spawn this entity with an attached rider(s) on its back
     *
     * @param rider
     */
    public boolean spawn(EntityLiving... riders);

    /**
     * Add a {@link PotionEffect} to this entity
     *
     * @param effect
     */
    public void addPotionEffect(PotionEffect effect);

    /**
     * Add a {@link PotionEffect} to this entity using custom values
     *
     * @param type
     * @param level
     * @param amp
     */
    public void addPotionEffect(PotionEffectType type, int duration, int amplifier);

    /**
     * Is this potion active on this entity
     *
     * @param potion
     * @return
     */
    public boolean isPotionActive(Potion potion);

    /**
     * Gets the supplied potions {@link PotionEffect} if it is active, else null
     *
     * @param potion
     * @return PotionEffect or null
     */
    public PotionEffect getActivePotionEffect(Potion potion);

    /**
     * Get a list of all active {@link PotionEffect}s.
     *
     * @return a List<PotionEffect>
     */
    public List<PotionEffect> getAllActivePotionEffects();

    /**
     * Set this Entities target entity. Depending on entity type this must not
     * necessarily be an attack target. Null to remove target
     *
     * @param target
     */
    public void setTarget(EntityLiving target);

    /**
     * Get the current target of this entity
     *
     * @return
     */
    public EntityLiving getTarget();

    /**
     * Look at the specified x, y, z coordinates
     *
     * @param x
     * @param y
     * @param z
     */
    public void lookAt(double x, double y, double z);

    /**
     * Look at the specified location
     *
     * @param location
     */
    public void lookAt(Location location);

    /**
     * Gets the item this {@link EntityLiving} is holding
     * @return
     */
    public Item getItemInHand();

    /**
     * Returns an Item array with 5 elements, representing this Entities current equipment
     * @see EntityLiving#getEquipmentInSlot(int)
     * @return
     */
    public Item[] getEquipment();

    /**
     * Returns the equipment piece for the given slot.
     * <ul>
     * <li>0 = item in hand</li>
     * <li>1 = boots</li>
     * <li>2 = leggings</li>
     * <li>3 = chestplate</li>
     * <li>4 = helmet</li>
     * </ul>
     * @param slot
     * @return
     */
    public Item getEquipmentInSlot(int slot);

    /**
     * Override all of this Entities equipment
     * @param items
     */
    public void setEquipment(Item[] items);

    /**
     * Set an item to a given slot.
     * <ul>
     * <li>0 = item in hand</li>
     * <li>1 = boots</li>
     * <li>2 = leggings</li>
     * <li>3 = chestplate</li>
     * <li>4 = helmet</li>
     * </ul>
     * @param item
     * @param slot
     */
    public void setEquipment(Item item, int slot);

    /**
     * Gets the drop chance of the items in the equipment slots
     * <ul>
     * <li>0 = item in hand</li>
     * <li>1 = boots</li>
     * <li>2 = leggings</li>
     * <li>3 = chestplate</li>
     * <li>4 = helmet</li>
     * </ul>
     * @param slot
     * @return
     */
    public float getDropChance(int slot);

    /**
     * Set the drop chance of an item for a given slot.
     * <ul>
     * <li>0 = item in hand</li>
     * <li>1 = boots</li>
     * <li>2 = leggings</li>
     * <li>3 = chestplate</li>
     * <li>4 = helmet</li>
     * </ul>
     * @param slot
     * @param chance
     */
    public void setDropChance(int slot, float chance);


    /**
     * Get the PathFinder class for this Entity.
     * @return the pathfinder
     */
    public PathFinder getPathFinder();

    /**
     * Move this entity to exact location with this speed.
     * @param x x coord
     * @param y y coord
     * @param z z coord
     * @param speed Set the speed of this mob, it should be between 0.0F and 1.0F <br>
     * <b>NOTE:</b> 1.0F is really really fast.<br>
     */
    public void moveEntityToXYZ(double x, double y, double z, float speed);

    /**
     * Returns the AIManager for this entity.
     *  <br>
     * <b>NOTE:</b> This really does nothing for Players. It won't get used.
     * @return
     */
    public AIManager getAITaskManager();

    /**
     * Gets how many Arrows are stuck in an Entity
     * @return
     */
    public int getArrowCountInEntity();

    /**
     * Sets how many Arrows are stuck in an Entity
     * @param arrows
     */
    public void setArrowCountInEntity(int arrows);

    /**
     * Attacks for the Living Entity the targeted entity with the
     * currently designated damage.
     * @param entity Entity to attack.
     * @param damage The amount of damage to do.
     */
    public void attackEntity(Entity entity, int damage);
}
