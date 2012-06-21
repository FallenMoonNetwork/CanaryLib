package net.canarymod.hook.world;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * The delegate for {@link BlockPhysicsHook}
 * @author Jason Jones
 *
 */
public class BlockPhysicsDelegate extends HookDelegate{

    @Override
    public Hook callHook(Hook hook) {
        return this.li.onBlockPhysics((BlockPhysicsHook) hook);
    }

}
