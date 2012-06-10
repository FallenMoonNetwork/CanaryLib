package net.canarymod.hook.world;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

public class PistonDelegate extends HookDelegate{

    @Override
    public Hook callHook(Hook hook) {
        switch(hook.getType()){
        case PISTON_EXTEND:
            return this.li.onPistonExtend((PistonHook) hook);
        case PISTON_RETRACT:
            return this.li.onPistonRetract((PistonHook) hook);
        }
        return null;
    }

}
