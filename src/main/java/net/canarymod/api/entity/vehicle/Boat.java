package net.canarymod.api.entity.vehicle;

/**
 * Boat Wrapper
 *
 * @author Chris (damagefilter)
 */
public interface Boat extends Vehicle {

    /**
     * Gets the direction that the vehicle is moving
     *
     * @return forward direction
     */
    public int getForwardDirection();

    /**
     * Sets the direction that the vehicle is moving
     *
     * @param direction
     *         the forward direction (0,1,2,3)
     */
    public void setForwardDirection(int direction);
}
