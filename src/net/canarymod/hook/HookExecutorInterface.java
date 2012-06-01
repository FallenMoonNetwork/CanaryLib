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
     * Register a custom hook that is fired from within plugins.<br>
     * NOTE: This might get slightly slower than standard system hooks!<br>
     * Do not overuse it.
     * 
     * @param hook
     * @param attachedMethodName
     * @implementation Reflect attachedMethodName to {@link PluginListener}
     */
    public void registerCustomHook(CustomHook hook, String attachedMethodName);

    /**
     * Register listener to this executor
     * 
     * @param listener
     * @param plugin
     * @param priority
     * @param hook
     */
    public void registerListener(PluginListener listener, Plugin plugin, Priority priority, Hook.Type hook);

    /**
     * Invokes a cancelable hook call to registered plugin listeners, passing
     * along the given hook object. The execution chain will break if this hook
     * has ben set to be cancelled at some point
     * 
     * @param hook
     * @return Hook
     */
    public Hook callCancelableHook(CancelableHook hook);

    /**
     * Invokes a normal hook call to registered plugin listeners
     * 
     * @param hook
     * @return Hook
     */
    public Hook callHook(Hook hook);

    /**
     * Invoke a custom registered hook
     * 
     * @param hook
     * @return
     */
    public Hook callCustomHook(CustomHook hook);
}
