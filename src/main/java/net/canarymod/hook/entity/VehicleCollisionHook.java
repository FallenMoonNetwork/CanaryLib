package net.canarymod.hook.entity;


import net.canarymod.api.entity.living.EntityLiving;
import net.canarymod.api.entity.vehicle.Vehicle;
import net.canarymod.hook.CancelableHook;


/**
 * Is called when a vehicle collides with a LivingEntity
 * @author chris
 *
 */
public class VehicleCollisionHook extends CancelableHook {
    private Vehicle vehicle;
    private EntityLiving collisioner;

    /**
     * Constructs a Collision hook.
     * @param vehicle
     * @param entity entity that got hit by a vehicle
     */
    public VehicleCollisionHook(Vehicle vehicle, EntityLiving entity) {
        this.vehicle = vehicle;
        this.collisioner = entity;
    }

    /**
     * Get the {@link Vehicle} that caused the collision
     * @return the vehicle
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Get the {@link EntityLiving} that got hit by the vehicle
     * @return the enteringEntity
     */
    public EntityLiving getEntity() {
        return collisioner;
    }
}
