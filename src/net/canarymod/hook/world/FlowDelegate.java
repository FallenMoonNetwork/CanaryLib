package net.canarymod.hook.world;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * The delegate for {@link FlowHook}
 * @author Jason Jones
 *
 */
public class FlowDelegate extends HookDelegate{

    @Override
    public Hook callHook(Hook hook) {
        return this.li.onFlow((FlowHook) hook);
    }

}
