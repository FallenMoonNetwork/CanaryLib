package net.canarymod.api.entity.vehicle;

import net.canarymod.api.entity.Entity;

public interface Vehicle extends Entity {

    /**
     * Gets the entity inside this vehicle
     * 
     * @return
     */
    public Entity getPassenger();

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

    /**
     * Check if this vehicle is empty.
     * 
     * @return
     */
    public boolean isEmpty();

}
