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
     * Register listener to this executor
     * 
     * @param listener
     * @param plugin
     * @param priority
     * @param hook
     * @deprecated Use {@link HookExecutorInterface#registerListener(PluginListener, Plugin, String, net.canarymod.hook.Hook.Type)} instead.
     */
    public void registerListener(PluginListener listener, Plugin plugin, Priority priority, Hook.Type hook);
    
    /**
     * Register listener to this executor
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
