package net.canarymod.hook;

import net.canarymod.plugin.Plugin;
import net.canarymod.plugin.PluginListener;

/**
 * Interface for a hook executor.
 *
 * @author Chris (damagefilter)
 */
public interface HookExecutorInterface {

    /**
     * Register listener to this executor.
     *
     * @param listener
     *         the {@link PluginListener} instance
     * @param plugin
     *         the {@link Plugin}
     */
    public void registerListener(PluginListener listener, Plugin plugin);

    /**
     * Unregister all listeners for specified plugin
     *
     * @param plugin
     *         the {@link Plugin} instance
     */
    public void unregisterPluginListeners(Plugin plugin);

    /**
     * Invokes a hook call to registered plugin listeners
     *
     * @param hook
     *         the {@link Hook} instance
     */
    public void callHook(Hook hook);
}
