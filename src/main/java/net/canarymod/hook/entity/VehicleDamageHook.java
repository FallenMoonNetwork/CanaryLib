package net.canarymod.hook.entity;

import net.canarymod.api.DamageSource;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.vehicle.Vehicle;
import net.canarymod.hook.CancelableHook;

/**
 * Vehicle Damage hook. Contains information about a vehicle receiving damage.
 * @author Chris Ksoll
 *
 */
public final class VehicleDamageHook extends CancelableHook{

    private Vehicle vehicle;
    private Entity attacker;
    private DamageSource source;
    private int dealt;

    public VehicleDamageHook(Vehicle vehicle, Entity attacker, DamageSource source, int dealt){
        this.attacker = attacker;
        this.vehicle = vehicle;
        this.source = source;
        this.dealt = dealt;
    }

    /**
     * Get the vehicle that is about to be damaged
     * @return attacker if there is one, null otherwise
     */
    public Vehicle getVehicle(){
        return vehicle;
    }

    /**
     * Get the entity that is inflicting the damage to the vehicle
     * @return defender
     */
    public Entity getAttacker(){
        return attacker;
    }

    /**
     * Gets the {@link DamageSource} type
     * @return source
     */
    public DamageSource getDamageSource(){
        return source;
    }

    /**
     * Gets the amount of damage dealt
     * @return dealt
     */
    public int getDamageDealt(){
        return dealt;
    }

    /**
     * Return the set of Data in this order: VEHICLE ATTACKER SOURCE DELT ISCANCELLED
     */
    @Override
    public Object[] getDataSet(){
        return new Object[]{ vehicle, attacker, source, dealt, isCanceled };
    }
}
