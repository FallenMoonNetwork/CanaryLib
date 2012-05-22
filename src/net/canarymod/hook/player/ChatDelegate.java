package net.canarymod.hook.player;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * The delegate for {@link ChatHook}
 * @author Chris Ksoll
 *
 */
public class ChatDelegate extends HookDelegate {

    @Override
    public Hook callHook(Hook hook) {
        return this.li.onChat((ChatHook)hook);
    }

}
