package net.canarymod.api;

import net.canarymod.api.entity.Entity;
import net.canarymod.api.world.World;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.position.Location;

/**
 *
 * @author Somners
 */
public interface PathFinder {

    /**
     * Sets a entity on path to the Given points
     * @param x x coordinate
     * @param y y coordinate
     * @param z z coordinate
     * @param world world
     * @return true if the path was set, false if failed
     */
    public boolean setPathToXYZ(double x, double y, double z, World world);

    /**
     * Sets the entity on a path to the given location.
     * @param location location to navigate to
     * @return true if the path was set, false if failed
     */
    public boolean setPathToLocation(Location location);

    /**
     * Sets the entity on a path to the given entity.
     * @param entity entity to navigate to
     * @return true if the path was set, false if failed
     */
    public boolean setPathToEntity(Entity entity);

    /**
     * Sets the entity on a path to the given block.
     * @param block block to navigate to
     * @return true if the path was set, false if failed
     */
    public boolean setPathToBlock(Block block);

    /**
     * Enables water routes on this path.
     */
    public void enableWaterPathing();

    /**
     * Disables water routes on this path.
     */
    public void disableWaterPathing();

    /**
     * Sets whether or not this entity can swim. default is false
     * @param bool true it can swim, false it drowns.
     */
    public void setCanSwim(boolean bool);

    /**
     * can this entity pass through open doors? default is true.
     * @param bool true, it will path through doors, false to avoid doors.
     */
    public void setCanPassOpenDoors(boolean bool);

    /**
     * Set if this entity will break down doors. default is false.
     * @param bool true, it will bust through doors, false to not.
     */
    public void setCanPassClosedDoors(boolean bool);

    /**
     * Set whether or not this entity should avoid sunlight. default is false.
     * @param bool true to avoid, false to not.
     */
    public void setAvoidSun(boolean bool);

    /**
     * Set the speed of this mob, it should be between 0.0F and 1.0F <br>
     * <b>NOTE:</b> 1.0F is really really fast.<br>
     * @param speed speed for this mob to walk.
     */
    public void setSpeed(float speed);

    /**
     * Set the max range this pathfinder will find a path to. default is 25.
     * @param range
     */
    public void setPathSearchRange(float range);


}
