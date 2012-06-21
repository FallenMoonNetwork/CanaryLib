package net.canarymod.hook.entity;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

public class EndermanDelegate extends HookDelegate{

    @Override
    public Hook callHook(Hook hook) {
        switch(hook.getType()){
        case ENDERMAN_DROP:
            return this.li.onEndermanDrop((EndermanHook) hook);
        case ENDERMAN_PICKUP:
            return this.li.onEndermanPickUp((EndermanHook) hook);
        }
        return hook;
    }
    
}
