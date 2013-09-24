package net.canarymod.hook.entity;

import net.canarymod.api.entity.Entity;
import net.canarymod.api.world.position.Location;
import net.canarymod.hook.CancelableHook;

/**
 * Dimension switch hook. Contains information about an entity changeing
 * dimentions
 *
 * @author Somners 
 */
public class DimensionSwitchHook extends CancelableHook {

    private Entity entity;
    private Location to, from;

    public DimensionSwitchHook(Entity entity, Location to, Location from) {
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

    @Override
    public final String toString() {
        return String.format("%s[Entity=%s, Location To=%s, Location From=%s]", getName(), entity, to, from);
    }
}
