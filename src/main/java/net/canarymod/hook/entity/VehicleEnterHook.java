package net.canarymod.hook.entity;

import net.canarymod.api.entity.living.LivingBase;
import net.canarymod.api.entity.vehicle.Vehicle;
import net.canarymod.hook.CancelableHook;

/**
 * Is called when a LivingEntity exits a vehicle
 *
 * @author Chris (damagefilter)
 */
public final class VehicleEnterHook extends CancelableHook {
    private Vehicle vehicle;
    private LivingBase enteringEntity;

    public VehicleEnterHook(Vehicle vehicle, LivingBase entity) {
        this.vehicle = vehicle;
        this.enteringEntity = entity;
    }

    /** @return the vehicle */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /** @return the enteringEntity */
    public LivingBase getEntity() {
        return enteringEntity;
    }

    @Override
    public final String toString() {
        return String.format("%s[Vehicle=%s, Entity=%s]", getName(), vehicle, enteringEntity);
    }
}
