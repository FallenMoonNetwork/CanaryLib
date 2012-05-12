package net.canarymod.api.entity;

public interface IVehicle extends IEntity {

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

    /**
     * Gets the entity inside this vehicle
     * 
     * @return
     */
    public IEntity getPassenger();

    /**
     * Sets the entity inside this vehicle
     * 
     * @param passenger
     */
    public void setPassenger(IEntity passenger);

    /**
     * Says whether or not this vehicle is a boat
     * 
     * @return true if boat
     */
    public boolean isBoat();

    /**
     * Says whether or not this vehicle is a minecart
     * 
     * @return
     */
    public boolean isMinecart();

}
