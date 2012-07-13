package net.canarymod.hook;

import net.canarymod.plugin.PluginListener;
import net.canarymod.plugin.Plugin;
import net.canarymod.plugin.Priority;

/**
 * Interface for a hook executor.
 * 
 * @author Chris
 * 
 */
public interface HookExecutorInterface {

    /**
     * Register listener to this executor.
     * <p>If you want to allow your users to dynamically change the plugin's priority to avoid conflicts, be sure to check out the second overload: {@link HookExecutorInterface#registerListener(PluginListener, Plugin, String, net.canarymod.hook.Hook.Type)}.</p>
     * 
     * @param listener
     * @param plugin
     * @param priority
     * @param hook
     * @see {@link HookExecutorInterface#registerListener(PluginListener, Plugin, String, net.canarymod.hook.Hook.Type)}
     */
    public void registerListener(PluginListener listener, Plugin plugin, Priority priority, Hook.Type hook);
    
    /**
     * Register listener to this executor.
     * <p>In the Canary.inf put a value of the format <code>priority-priorityName = value</code> to allow your users to dynamically change this hook's priority.
     * 
     * @param listener
     * @param plugin
     * @param priorityName
     * @param hook
     */
    public void registerListener(PluginListener listener, Plugin plugin, String priorityName, Hook.Type hook);

    /**
     * Unregister all listeners for specified plugin
     * @param plugin
     */
    public void unregisterPluginListeners(Plugin plugin);

    /**
     * Invokes a hook call to registered plugin listeners
     * 
     * @param hook
     * @return Hook
     */
    public void callHook(Hook hook);
}
