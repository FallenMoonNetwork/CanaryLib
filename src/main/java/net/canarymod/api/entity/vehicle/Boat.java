package net.canarymod.api.entity.vehicle;

public interface Boat extends Vehicle {
    /**
     * Gets the direction that the vehicle is moving
     * 
     * @return
     */
    public int getForwardDirection();

    /**
     * Sets the direction that the vehicle is moving
     * 
     * @param direction
     */
    public void setForwardDirection(int direction);
}
