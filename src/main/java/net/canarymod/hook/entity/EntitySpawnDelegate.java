package net.canarymod.hook.entity;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

public class EntitySpawnDelegate extends HookDelegate{

    @Override
    public Hook callHook(Hook hook) {
        switch(hook.getType()){
        case ENTITY_DESPAWN:
            return this.li.onEntityDespawn((EntitySpawnHook) hook);
        case ENTITY_SPAWN:
            return this.li.onEntitySpawn((EntitySpawnHook) hook);
        case MOB_DESPAWN:
            return this.li.onMobDespawn((EntitySpawnHook) hook);
        case MOB_SPAWN:
            return this.li.onMobSpawn((EntitySpawnHook) hook);
        }
        return hook;
    }
}
