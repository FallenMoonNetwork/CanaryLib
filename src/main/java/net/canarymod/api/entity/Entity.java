package net.canarymod.api.entity;

import java.util.UUID;

import net.canarymod.api.entity.living.Golem;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.nbt.BaseTag;
import net.canarymod.api.nbt.CompoundTag;
import net.canarymod.api.world.World;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Position;
import net.canarymod.api.world.position.Vector3D;

/**
 * This defines an entity in the world
 * Everything that is not a block inherits from Entity in a way.
 *
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public interface Entity {

    /**
     * Get X position
     *
     * @return x position
     */
    public double getX();

    /**
     * Get Y position
     *
     * @return y position
     */
    public double getY();

    /**
     * Get Z position
     *
     * @return z position
     */
    public double getZ();

    /**
     * Get X Motion (movement speed)
     *
     * @return x motion
     */
    public double getMotionX();

    /**
     * Get Y Motion (movement speed)
     *
     * @return y motion
     */
    public double getMotionY();

    /**
     * Get Z Motion (movement speed)
     *
     * @return z motion
     */
    public double getMotionZ();

    /**
     * Get this Entities pitch (up or down looking) (Rotation around X axis)
     *
     * @return pitch
     */
    public float getPitch();

    /**
     * Get this entities look direction (Rotation around Y axis)
     *
     * @return rotation
     */
    public float getRotation();

    /**
     * Returns this entities coordinates in a Vector3D object
     *
     * @return {@link Position}
     */
    public Position getPosition();

    /**
     * Get this entities location, including world, dimension, pitch and rotation and coordinates
     *
     * @return {@link Location}
     */
    public Location getLocation();

    /**
     * Get the assigned unique ID for this entity
     *
     * @return id
     */
    public int getID();

    /**
     * Gets the assigned UUID for this entity
     *
     * @return {@link UUID}
     */
    public UUID getUUID();

    /**
     * Set X coordinate
     *
     * @param x
     *         the X coordinate to set
     */
    public void setX(double x);

    /**
     * Set X coordinate
     *
     * @param x
     *         the X coordinate to set
     */
    public void setX(int x);

    /**
     * Set Y coordinate
     *
     * @param y
     *         the Y coordinate to set
     */
    public void setY(double y);

    /**
     * Set Y coordinate
     *
     * @param y
     *         the Y coordinate to set
     */
    public void setY(int y);

    /**
     * Set Z coordinate
     *
     * @param z
     *         the Z coordinate to set
     */
    public void setZ(double z);

    /**
     * Set Z coordinate
     *
     * @param z
     *         the Z coordinate to set
     */
    public void setZ(int z);

    /**
     * Set X Motion (movement speed)
     *
     * @param motionX
     *         the X-wise motion
     */
    public void setMotionX(double motionX);

    /**
     * Set Y Motion (movement speed)
     *
     * @param motionY
     *         the Y-wise motion
     */
    public void setMotionY(double motionY);

    /**
     * Set Z Motion (movement speed)
     *
     * @param motionZ
     *         the Z-wise motion
     */
    public void setMotionZ(double motionZ);

    /**
     * Set this entities pitch (up / down looking)
     *
     * @param pitch
     *         the Y rotation to set
     */
    public void setPitch(float pitch);

    /**
     * Set this entities rotation
     *
     * @param rotation
     *         the X rotation to set
     */
    public void setRotation(float rotation);

    /**
     * Get the motion vector of this entity.
     * This is a convenience for position calculations
     *
     * @return {@link Vector3D}
     */
    public Vector3D getMotion();

    /**
     * Returns the forward direction of this entity.
     * Convenience for position calculations
     *
     * @return {@link Vector3D}
     */
    public Vector3D getForwardVector();

    /**
     * Translates this entity in its position by the given Vector3D.
     *
     * @param factor
     *         the {@link Vector3D} factor
     */
    public void translate(Vector3D factor);

    /**
     * Move this entity with the forces given. Note that those are not the
     * coordinates to move to! It does also not translate the entity on a vector.
     * This simply adds force to this entity
     *
     * @param motionX
     *         the X-wise motion
     * @param motionY
     *         the Y-wise motion
     * @param motionZ
     *         the Z-wise motion
     */
    public void moveEntity(double motionX, double motionY, double motionZ);

    /**
     * Teleport this entity to the given coordinates
     *
     * @param x
     *         the X coordinate
     * @param y
     *         the Y coordinate
     * @param z
     *         the Z coordinate
     */
    public void teleportTo(double x, double y, double z);

    /**
     * Teleport to this coords in the given dimension
     *
     * @param x
     *         the X coordinate
     * @param y
     *         the Y coordinate
     * @param z
     *         the Z coordinate
     * @param world
     *         the {@link World}
     */
    public void teleportTo(double x, double y, double z, World world);

    /**
     * Teleport this entity to the given coordinates, with the given pitch and rotation to look at
     * this.entity.b(x, y, z, rotation, pitch);
     *
     * @param x
     *         the X coordinate
     * @param y
     *         the Y coordinate
     * @param z
     *         the Z coordinate
     * @param pitch
     *         the Y-wise rotation
     * @param rotation
     *         the X-wise rotation
     */
    public void teleportTo(double x, double y, double z, float pitch, float rotation);

    /**
     * Teleport to this location in the given world
     *
     * @param x
     *         the X coordinate
     * @param y
     *         the Y coordinate
     * @param z
     *         the Z coordinate
     * @param pitch
     *         the Y-wise rotation
     * @param rotation
     *         the X-wise rotation
     * @param dim
     *         the {@link World}
     */
    public void teleportTo(double x, double y, double z, float pitch, float rotation, World dim);

    /**
     * Teleport to the specified location
     *
     * @param location
     *         the {@link Location} to teleport to
     */
    public void teleportTo(Location location);

    /**
     * Teleport this entity to the given position
     *
     * @param position
     *         the {@link Position} to teleport to
     */
    public void teleportTo(Position position);

    /**
     * Get this entities world(dimension)
     *
     * @return {@link World}
     */
    public World getWorld();

    /**
     * Checks if the player is sprinting
     *
     * @return {@code true} if sprinting; {@code false} otherwise
     */
    public boolean isSprinting();

    /**
     * Mark this entity as spriting or not sprinting
     *
     * @param sprinting
     *         {@code true} for sprinting; {@code false} otherwise
     */
    public void setSprinting(boolean sprinting);

    /**
     * Returns whether this entity is sneaking
     *
     * @return {@code true} if sneaking; {@code false} otherwise
     */
    public boolean isSneaking();

    /**
     * Mark this entity as sneaking or not
     *
     * @param sneaking
     *         {@code true} for sneaking; {@code false} otherwise
     */
    public void setSneaking(boolean sneaking);

    /**
     * Set how many ticks are left until entity catches fire. Note that there's
     * a base amount of ticks that is always there and if the current fire ticks
     * are smaller than 20% of the base ticks, the entity will catch fire.
     *
     * @param ticks
     *         the fire ticks to set
     */
    public void setFireTicks(int ticks);

    /**
     * Get how many ticks are left until entity catches fire.
     *
     * @return fire ticks
     */
    public int getFireTicks();

    /**
     * Check if this entity is a living entity
     *
     * @return {@code true} if living; {@code false} otherwise
     */
    public boolean isLiving();

    /**
     * Check if this entity is an item entity
     *
     * @return {@code true} if {@link EntityItem}; {@code false} otherwise
     */
    public boolean isItem();

    /**
     * Check if this entity is a mob
     *
     * @return {@code true} when it is a mob
     */
    public boolean isMob();

    /**
     * Check if this entity is an animal (implements the animal interface)
     *
     * @return {@code true} if animal; {@code false} otherwise
     */
    public boolean isAnimal();

    /**
     * Check if this entity is a player entity
     *
     * @return {@code true} when it is a player
     */
    public boolean isPlayer();

    /**
     * Checks if this entity is a {@link Golem}
     *
     * @return {@code true} if {@link Golem}; {@code false} if not
     */
    public boolean isGolem();

    /**
     * Make this entity drop the given item
     *
     * @param itemId
     *         the {@link Item} id to drop
     * @param amount
     *         the amount to be dropped
     *
     * @return the resulting {@link EntityItem}
     */
    public EntityItem dropLoot(int itemId, int amount);

    /**
     * Make this entity drop the given item
     *
     * @param item
     *         the {@link Item} to be dropped
     *
     * @return the resulting {@link EntityItem}
     */
    public EntityItem dropLoot(Item item);

    /**
     * Get this entity's name, can either be its qualified name or display name if present
     *
     * @return name
     */
    public String getName();

    /**
     * Gets the Entity's qualified name (ie: Class Name)
     *
     * @return qualified name
     */
    public String getFqName();

    /**
     * Check if this entity can spawn at its current specified position or not
     *
     * @return {@code true} if the entity can; {@code false} otherwise
     */
    public boolean canSpawn();

    /**
     * Spawn this entity in the world.
     *
     * @return {@code true} if successful; {@code false} if not
     */
    public boolean spawn();

    /**
     * Spawn this entity with a given rider entity
     *
     * @param rider
     *         the {@code Entity} rider
     *
     * @return {@code true} if successful; {@code false} otherwise
     */
    public boolean spawn(Entity rider);

    /**
     * Check if this Entity is riding another entity.
     *
     * @return {@code true} if the entity is riding another entity; {@code false} otherwise
     */
    public boolean isRiding();

    /**
     * Checks if the Entity is being ridden by another Entity.
     *
     * @return {@code true} if being ridden; {@code false} if not
     */
    public boolean isRidden();

    /**
     * Gets the Entity the Entity is riding
     *
     * @return the Entity being ridden by the Entity; {@code null} if not riding
     */
    public Entity getRiding();

    /**
     * Gets the Entity the Entity is being ridden by
     *
     * @return the Entity that is riding the Entity; {@code null} if no rider
     */
    public Entity getRider();

    /**
     * Set this entities rider.
     * The given EntityLiving will be attached as rider on this entity.
     *
     * @param rider
     *         the {@code Entity} rider
     */
    public void setRider(Entity rider);

    /** Destroys this entity */
    public void destroy();

    /**
     * Gets whether this Entity is pending clean up
     *
     * @return {@code true} if dead; {@code false} otherwise
     */
    public boolean isDead();

    /**
     * Get's the NBT Tag for this Entity.
     *
     * @return {@link CompoundTag}
     */
    public CompoundTag getNBT();

    /**
     * Set this NBT Tag for this entity. Any missing values will be overridden
     * to the defaults.
     *
     * @param tag
     *         The tag to set
     *
     * @see #getNBT()
     */
    public void setNBT(BaseTag tag);

    /**
     * Returns whether this entity is invisible
     *
     * @return {@code true} if invisible; {@code false} otherwise
     */
    public boolean isInvisible();

    /**
     * Mark this entity as invisible or not
     *
     * @param invisible
     *         {@code true} for invisible; {@code false} for visible
     */
    public void setInvisible(boolean invisible);

    /**
     * Gets the persistent MetaData tag for the Entity
     *
     * @return MetaData
     */
    public CompoundTag getMetaData();

    /**
     * Gets the Type of the entity
     *
     * @return the {@link EntityType}
     */
    public EntityType getEntityType();

}
