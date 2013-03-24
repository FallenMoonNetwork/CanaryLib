package net.canarymod.api.entity;

import net.canarymod.api.world.World;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Position;
import net.canarymod.api.world.position.Vector3D;

/**
 * This defines an entity in the world
 * Everything that is not a block inherits from Entity in a way.
 * @author Chris Ksoll
 *
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
}
