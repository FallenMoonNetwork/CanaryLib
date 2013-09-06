package net.canarymod.hook.entity;

import net.canarymod.api.entity.vehicle.Vehicle;
import net.canarymod.hook.Hook;

/**
 * VehicleDestoryHook<br>
 * Called when a Minecart or Boat is destroyed
 *
 * @author Jason (darkdiplomat)
 */
public class VehicleDestroyHook extends Hook {
    private Vehicle vehicle;

    public VehicleDestroyHook(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * The {@link Vehicle} being destoryed
     *
     * @return the {@link Vehicle}
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public final String toString() {
        return String.format("%s[Vehicle=%s]", getName(), vehicle);
    }
}
