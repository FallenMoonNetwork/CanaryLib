package net.canarymod.api.entity;


import java.util.UUID;
import net.canarymod.api.nbt.BaseTag;

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
     * @return
     */
    public double getX();

    /**
     * Get Y position
     *
     * @return
     */
    public double getY();

    /**
     * Get Z position
     *
     * @return
     */
    public double getZ();

    /**
     * Get X Motion (movement speed)
     *
     * @return
     */
    public double getMotionX();

    /**
     * Get Y Motion (movement speed)
     *
     * @return
     */
    public double getMotionY();

    /**
     * Get Z Motion (movement speed)
     *
     * @return
     */
    public double getMotionZ();

    /**
     * Get this Entities pitch (up or down looking) (Rotation around X axis)
     *
     * @return
     */
    public float getPitch();

    /**
     * Get this entities look direction (Rotation around Y axis)
     *
     * @return
     */
    public float getRotation();

    /**
     * Returns this entities coordinates in a Vector3D object
     * @return
     */
    public Position getPosition();

    /**
     * Get this entities locaion, including world, dimension, pitch and rotation and coordinates
     * @return
     */
    public Location getLocation();

    /**
     * Get the assigned unique ID for this entity
     * @return
     */
    public int getID();

    /**
     * Gets the assigned UUID for this entity
     * @return
     */
    public UUID getUUID();

    /**
     * Set X
     *
     * @param x
     */
    public void setX(double x);

    /**
     * Set X
     *
     * @param x
     */
    public void setX(int x);

    /**
     * Set Y
     *
     * @param y
     */
    public void setY(double y);

    /**
     * Set Y
     *
     * @param y
     */
    public void setY(int y);

    /**
     * Set Z
     *
     * @param z
     */
    public void setZ(double z);

    /**
     * Set Z
     *
     * @param z
     */
    public void setZ(int z);

    /**
     * Set X Motion (movement speed)
     *
     * @param motionX
     */
    public void setMotionX(double motionX);

    /**
     * Set Y Motion (movement speed)
     *
     * @param motionY
     */
    public void setMotionY(double motionY);

    /**
     * Set Z Motion (movement speed)
     *
     * @param motionZ
     */
    public void setMotionZ(double motionZ);

    /**
     * Set this entities pitch (up / down looking)
     *
     * @param pitch
     */
    public void setPitch(float pitch);

    /**
     * Set this entities rotation
     *
     * @param rotation
     */
    public void setRotation(float rotation);

    /**
     * Get the motion vector of this entity.
     * This is a convenience for position calculations
     * @return
     */
    public Vector3D getMotion();

    /**
     * Returns the forward direction of this entity.
     * Convenience for position calculations
     * @return
     */
    public Vector3D getForwardVector();

    /**
     * Translates this entity in its position by the given Vector3D.
     * @param factor
     */
    public void translate(Vector3D factor);

    /**
     * Move this entity with the forces given. Note that those are not the
     * coordinates to move to! It does also not translate the entity on a vector.
     * This simply adds force to this entity
     *
     * @param motionX
     * @param motionY
     * @param motionZ
     */
    public void moveEntity(double motionX, double motionY, double motionZ);

    /**
     * Teleport this entity to the given coordinates, with the given pitch and rotation to look at
     * this.entity.b(x, y, z, rotation, pitch);
     * @param x
     * @param y
     * @param z
     * @param rotation
     * @param pitch
     */
    public void teleportTo(double x, double y, double z, float rotation, float pitch);

    /**
     * Teleport this entity to the given coordinates
     * @param x
     * @param y
     * @param z
     */
    public void teleportTo(double x, double y, double z);

    /**
     * Teleport this entity to the given position
     * @param position
     */
    public void teleportTo(Position position);

    /**
     * Set this Entities dimension. (will teleport to the dimension)
     *
     * @param dim
     */
    public void setDimension(World dim);

    /**
     * Get this entities world(dimension)
     *
     * @return
     */
    public World getWorld();

    /**
     * Checks if the player is sprinting
     *
     * @return
     */
    public boolean isSprinting();

    /**
     * Mark this entity as spriting or not sprinting
     *
     * @param sprinting
     */
    public void setSprinting(boolean sprinting);

    /**
     * Returns whether this entity is sneaking
     *
     * @return
     */
    public boolean isSneaking();

    /**
     * Mark this entity as sneaking or not
     *
     * @param sneaking
     */
    public void setSneaking(boolean sneaking);

    /**
     * Set how many ticks are left until entity catches fire. Note that there's
     * a base amount of ticks that is always there and if the current fire ticks
     * are smaller than 20% of the base ticks, the entity will catch fire.
     *
     * @param ticks
     */
    public void setFireTicks(int ticks);

    /**
     * Get how many ticks are left until entity catches fire.
     *
     * @return
     */
    public int getFireTicks();

    /**
     * Check if this entity is a living entity
     *
     * @return
     */
    public boolean isLiving();

    /**
     * Check if this entity is an item entity
     *
     * @return
     */
    public boolean isItem();

    /**
     * Make this entity drop the given item
     *
     * @param itemId
     * @param amount
     */
    public EntityItem dropLoot(int itemId, int amount);

    /**
     * Get this entities name
     * @return
     */
    public String getName();

    /**
     * Check if this entity can spawn at its current specified position or not
     *
     * @return true if the entity can
     */
    public boolean canSpawn();

    /**
     * Spawn this entity in the world.
     */
    public boolean spawn();

    /**
     * Spawn this entity with a given rider entity
     * @param rider
     * @return
     */
    public boolean spawn(Entity rider);

    /**
     * Check if this Entity is riding another entity.
     * @return True if the entity is riding another entity. False otherwise
     */
    public boolean isRiding();

    /**
     * Set this entities rider.
     * The given EntityLiving will be attached as rider on this entity.
     *
     * @param rider
     */
    public void setRider(Entity rider);

    /**
     * Destroys this entity
     */
    public void destroy();
    
    /**
     * Get's the NBT Tag for this Entity.
     * 
     * @return 
     */
    public BaseTag getNBT();
    
    /**
     * Set this NBT Tag for this entity. Any missing values will be overridden 
     * to the defaults.
     * @param tag The tag to set
     * @see Entity.getNBT()
     */
    public void setNBT(BaseTag tag);
}
