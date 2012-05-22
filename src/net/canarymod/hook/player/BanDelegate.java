package net.canarymod.hook.player;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * The delegate for {@link BanHook}
 * @author Chris Ksoll
 *
 */
public class BanDelegate extends HookDelegate {

    @Override
    public Hook callHook(Hook hook) {
        return this.li.onBan((BanHook)hook);
    }

}
