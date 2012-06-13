package net.canarymod.hook.world;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * The delegate for {@link SignHook}
 * @author Jason Jones
 *
 */
public class SignDelegate extends HookDelegate{

    @Override
    public Hook callHook(Hook hook) {
        switch(hook.getType()){
        case SIGN_CHANGE:
            return this.li.onSignChange((SignHook) hook);
        case SIGN_SHOW:
            return this.li.onSignShow((SignHook) hook);
        }
        return hook;
    }
}
