package net.canarymod.hook.entity;


import net.canarymod.api.entity.living.EntityLiving;
import net.canarymod.api.entity.vehicle.Vehicle;
import net.canarymod.hook.CancelableHook;


/**
 * Is called when a LivingEntity exits a vehicle
 * @author chris
 *
 */
public final class VehicleEnterHook extends CancelableHook {
    private Vehicle vehicle;
    private EntityLiving enteringEntity;

    public VehicleEnterHook(Vehicle vehicle, EntityLiving entity) {
        this.vehicle = vehicle;
        this.enteringEntity = entity;
    }

    /**
     * @return the vehicle
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * @return the enteringEntity
     */
    public EntityLiving getEntity() {
        return enteringEntity;
    }

    @Override
    public final String toString() {
        return String.format("%s[Vehicle=%s, Entity=%s]", getName(), vehicle, enteringEntity);
    }
}
