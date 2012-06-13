package net.canarymod.hook.world;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * 
 * @author Brian McCarthy
 *
 */
public class DecorateDelegate extends HookDelegate {

    @Override
    public Hook callHook(Hook hook) {
        return this.li.onDecorate((DecorateHook) hook);
    }

}
