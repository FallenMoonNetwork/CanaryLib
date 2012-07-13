package net.canarymod.hook.entity;

import net.canarymod.api.entity.Entity;
import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.Hook;
import net.canarymod.plugin.PluginListener;

public final class EntitySpawnHook extends CancelableHook{
    
    private Entity entity;
    
    public EntitySpawnHook(Entity entity, boolean spawning){
        this.entity = entity;
        this.type = spawning ? Hook.Type.ENTITY_SPAWN : Hook.Type.ENTITY_DESPAWN;
    }
    
    /**
     * Gets the {@link Entity}
     * @return
     */
    public Entity getEntity(){
        return entity;
    }
    
    /**
     * Return the set of Data in this order: ENTITY ISCANCELLED
     */
    @Override
    public Object[] getDataSet(){
        return new Object[]{ entity, isCanceled };
    }
    
    /**
     * Dispatches the hook to the given listener.
     * @param listener The listener to dispatch the hook to.
     */
    @SuppressWarnings("incomplete-switch")
    @Override
    public void dispatch(PluginListener listener) {
        switch (this.type) {
            case ENTITY_SPAWN: {
                listener.onEntitySpawn(this);
                break;
            }
            case ENTITY_DESPAWN: {
                listener.onEntityDespawn(this);
                break;
            }
        }
    }

}
