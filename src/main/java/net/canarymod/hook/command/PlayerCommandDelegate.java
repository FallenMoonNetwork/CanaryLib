package net.canarymod.hook.command;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * The delegate for {@link PlayerCommandHook}
 * @author Chris Ksoll
 *
 */
public class PlayerCommandDelegate extends HookDelegate {

    @Override
    public Hook callHook(Hook hook) {
        return this.li.onCommand((PlayerCommandHook)hook);
    }

}
