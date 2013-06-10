package net.canarymod.hook.entity;

import net.canarymod.api.entity.Entity;
import net.canarymod.api.world.position.Location;
import net.canarymod.hook.CancelableHook;

/**
 * @author Somners
 */
public class DimensionSwitch extends CancelableHook {

    private Entity entity;
    private Location to, from;

    public DimensionSwitch(Entity entity, Location to, Location from) {
        this.entity = entity;
        this.to = to;
        this.from = from;
    }

    /**
     * Get the entity involved here.
     * 
     * @return The entity.
     */
    public Entity getEntity() {
        return this.entity;
    }

    /**
     * Get the entities current location.
     * 
     * @return Current Location
     */
    public Location getLocationTo() {
        return this.to;
    }

    /**
     * Get the location the entity is warping to.
     * 
     * @return Location to.
     */
    public Location getLocationFrom() {
        return this.from;
    }
}
