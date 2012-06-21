package net.canarymod.hook.command;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * The delegate for {@link ConsoleCommandHook}
 * @author Chris Ksoll
 *
 */
public class ConsoleCommandDelegate extends HookDelegate {

    @Override
    public Hook callHook(Hook hook) {
        return this.li.onConsoleCommand((ConsoleCommandHook)hook);
    }

}
