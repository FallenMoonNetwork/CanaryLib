package net.canarymod.hook.entity;


import net.canarymod.api.entity.Entity;
import net.canarymod.hook.CancelableHook;


public final class EntitySpawnHook extends CancelableHook {

    private Entity entity;

    public EntitySpawnHook(Entity entity, boolean spawning) {
        this.entity = entity;
    }

    /**
     * Gets the {@link Entity}
     * @return
     */
    public Entity getEntity() {
        return entity;
    }

    /**
     * Return the set of Data in this order: ENTITY ISCANCELLED
     */
    @Override
    public Object[] getDataSet() {
        return new Object[] { entity, isCanceled };
    }
}
