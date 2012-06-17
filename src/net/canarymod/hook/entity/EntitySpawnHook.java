package net.canarymod.hook.entity;

import net.canarymod.api.entity.Entity;
import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.Hook;

public class EntitySpawnHook extends CancelableHook{
    
    private Entity entity;
    
    public EntitySpawnHook(Entity entity, boolean spawning, boolean isLiving){
        this.entity = entity;
        this.type = spawning ? isLiving ? Hook.Type.MOB_SPAWN : Hook.Type.ENTITY_SPAWN : isLiving ? Hook.Type.MOB_DESPAWN : Hook.Type.ENTITY_DESPAWN;
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
        return new Object[]{ entity, isCancelled };
    }

}
