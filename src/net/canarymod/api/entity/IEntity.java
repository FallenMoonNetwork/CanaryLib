package net.canarymod.api.entity;

import net.canarymod.api.world.IDimension;
import net.canarymod.api.world.position.Location;

public interface IEntity {
    
    /**
     * Get X position
     * @return
     */
    public double getX();
    
    /**
     * Get Y position
     * @return
     */
    public double getY();
    
    /**
     * Get Z position
     * @return
     */
    public double getZ();
    
    /**
     * Get X Motion (movement speed)
     * @return
     */
    public double getMotionX();
    
    /**
     * Get Y Motion (movement speed)
     * @return
     */
    public double getMotionY();
    
    /**
     * Get Z Motion (movement speed)
     * @return
     */
    public double getMotionZ();
    
    /**
     * Get this Entities pitch (up or down looking)
     * @return
     */
    public float getPitch();
    
    /**
     * Get this entities look direction
     * @return
     */
    public float getRotation();
    
    /**
     * Set X
     * @param x
     */
    public void setX(double x);
    /**
     * Set X
     * @param x
     */
    public void setX(int x);
    
    /**
     * Set Y
     * @param y
     */
    public void setY(double y);
    /**
     * Set Y
     * @param y
     */
    public void setY(int y);
    
    /**
     * Set Z
     * @param z
     */
    public void setZ(double z);
    /**
     * Set Z
     * @param z
     */
    public void setZ(int z);
    
    /**
     * Set X Motion (movement speed)
     * @param motionX
     */
    public void setMotionX(double motionX);
    
    /**
     * Set Y Motion (movement speed)
     * @param motionY
     */
    public void setMotionY(double motionY);
    
    /**
     * Set Z Motion (movement speed)
     * @param motionZ
     */
    public void setMotionZ(double motionZ);
    
    /**
     * Set this entities pitch (up / down looking)
     * @param pitch
     */
    public void setPitch(float pitch);
    
    /**
     * Set this entities rotation
     * @param rotation
     */
    public void setRotation(float rotation);
    /**
     * Set this Entities dimension. (will teleport to the dimension)
     * @param dim
     */
    public void setWorld(IDimension dim);
    
    /**
     * Get this entities world(dimension)
     * @return
     */
    public IDimension getWorld();
    
    /**
     * Returns whether this entity is sprinting
     * @return true if entity is sprinting, false otherwise
     */
    public boolean isSprinting();
    
    /**
     * Set the air ticks for this entity (time until entity starts to drown)
     * @param ticks
     */
    public void setAirTicks(int ticks);
    
    /**
     * Return the number of ticks until this entity starts to drown.
     * @return
     */
    public int getAirTicks();
    
    /**
     * Set how many ticks are left until entity catches fire.
     * Note that there's a base amount of ticks that is always there and
     * if the current fire ticks are smaller than 20% of the base ticks,
     * the entity will catch fire.
     * @param ticks
     */
    public void setFireTicks(int ticks);
    
    /**
     * Check if this entity is a living entity
     * @return
     */
    public boolean isLiving();
    
    /**
     * Check if this entity is an item entity
     * @return
     */
    public boolean isItem();
    
    /**
     * Drop this entities loot (item drops)
     */
    public void dropLoot();
    
    /**
     * Teleport to this location
     * @param x
     * @param y
     * @param z
     */
    public void teleportTo(double x, double y, double z);
    
    /**
     * Teleport to this coords in the given dimension
     * @param x
     * @param y
     * @param z
     * @param dim
     */
    public void teleportTo(double x, double y, double z, IDimension dim);
    
    /**
     * Teleport to this location in the given world
     * @param x
     * @param y
     * @param z
     * @param pitch
     * @param rotation
     * @param dim
     */
    public void teleportTo(double x, double y, double z, float pitch, float rotation, IDimension dim);
    
    /**
     * Teleport to this location and set pitch and rotation
     * @param x
     * @param y
     * @param z
     * @param pitch
     * @param rotation
     */
    public void teleportTo(double x, double y, double z, float pitch, float rotation);
    
    /**
     * Teleport to the specified location
     * @param location
     */
    public void teleportTo(Location location);
}
