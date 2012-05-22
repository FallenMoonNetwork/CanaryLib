package net.canarymod.hook.player;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * The delegate for {@link BanHook}
 * @author Chris Ksoll
 *
 */
public class IpBanDelegate extends HookDelegate {

    @Override
    public Hook callHook(Hook hook) {
        return this.li.onIpBan((IpBanHook)hook);
    }

}
