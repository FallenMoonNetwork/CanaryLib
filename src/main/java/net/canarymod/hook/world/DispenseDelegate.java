package net.canarymod.hook.world;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * The delegate for {@link DispenseHook}
 * @author Jason Jones
 *
 */
public class DispenseDelegate extends HookDelegate{

    @Override
    public Hook callHook(Hook hook) {
        return this.li.onDispense((DispenseHook) hook);
    }

}
