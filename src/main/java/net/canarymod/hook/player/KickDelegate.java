package net.canarymod.hook.player;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * The delegate for {@link LoginHook}
 * @author Chris Ksoll
 *
 */
public class KickDelegate extends HookDelegate {

    @Override
    public Hook callHook(Hook hook) {
        return this.li.onKick((KickHook)hook);
    }

}
