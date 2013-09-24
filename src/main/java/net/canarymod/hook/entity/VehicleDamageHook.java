package net.canarymod.hook.entity;

import net.canarymod.api.DamageSource;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.vehicle.Vehicle;
import net.canarymod.hook.CancelableHook;

/**
 * Vehicle Damage hook. Contains information about a vehicle receiving damage.
 *
 * @author Chris (damagefilter)
 */
public final class VehicleDamageHook extends CancelableHook {

    private Vehicle vehicle;
    private Entity attacker;
    private DamageSource source;
    private float dealt;

    public VehicleDamageHook(Vehicle vehicle, Entity attacker, DamageSource source, float dealt) {
        this.attacker = attacker;
        this.vehicle = vehicle;
        this.source = source;
        this.dealt = dealt;
    }

    /**
     * Get the vehicle that is about to be damaged
     *
     * @return attacker if there is one, null otherwise
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Get the entity that is inflicting the damage to the vehicle
     *
     * @return defender
     */
    public Entity getAttacker() {
        return attacker;
    }

    /**
     * Gets the {@link DamageSource} type
     *
     * @return source
     */
    public DamageSource getDamageSource() {
        return source;
    }

    /**
     * Gets the amount of damage dealt
     *
     * @return dealt
     */
    public float getDamageDealt() {
        return dealt;
    }

    @Override
    public final String toString() {
        return String.format("%s[Vehicle=%s, Attacker=%s, DamageSource=%s, Dealt=%.4f]", getName(), vehicle, attacker, source, dealt);
    }
}
