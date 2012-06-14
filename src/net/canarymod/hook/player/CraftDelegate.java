package net.canarymod.hook.player;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

public class CraftDelegate extends HookDelegate{

    @Override
    public Hook callHook(Hook hook) {
        switch(hook.getType()){
        case CRAFT:
            return this.li.onCraft((CraftHook) hook);
        }
        return hook;
    }

}
