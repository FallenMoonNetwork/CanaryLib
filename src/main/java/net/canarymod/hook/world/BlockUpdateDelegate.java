package net.canarymod.hook.world;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * The delegate for {@link BlockUpdateHook}
 * @author Jason Jones
 *
 */
public class BlockUpdateDelegate extends HookDelegate{

    @Override
    public Hook callHook(Hook hook) {
        return this.li.onBlockUpdate((BlockUpdateHook) hook);
    }
}
