package net.canarymod.hook.entity;

import net.canarymod.api.entity.Entity;
import net.canarymod.hook.CancelableHook;

/**
 * Entity spawn hook. Contains information about an entity spawning.
 *
 * @author Jason (darkdiplomat)
 */
public final class EntitySpawnHook extends CancelableHook {

    private Entity entity;

    public EntitySpawnHook(Entity entity) {
        this.entity = entity;
    }

    /**
     * Gets the {@link Entity} spawning
     *
     * @return {@link Entity}
     */
    public Entity getEntity() {
        return entity;
    }

    @Override
    public final String toString() {
        return String.format("%s[Entity=%s]", getName(), entity);
    }
}
