package net.canarymod.hook.player;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * The delegate for {@link LoginChecksHook}
 * @author Chris Ksoll
 *
 */
public class LoginChecksDelegate extends HookDelegate {

    @Override
    public Hook callHook(Hook hook) {
        return this.li.onLoginChecks((LoginChecksHook)hook);
    }

}
