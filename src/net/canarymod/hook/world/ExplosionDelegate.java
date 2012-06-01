package net.canarymod.hook.world;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * The delegate for {@link ExplosionHook}
 * @author Jason Jones
 *
 */
public class ExplosionDelegate extends HookDelegate{

    @Override
    public Hook callHook(Hook hook) {
        return this.li.onExplosion((ExplosionHook) hook);
    }
}
