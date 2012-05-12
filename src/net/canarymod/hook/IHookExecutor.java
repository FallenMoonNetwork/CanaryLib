package net.canarymod.hook;

import net.canarymod.plugin.*;

/**
 * Interface for a hook executor.
 * @author Chris
 *
 */
public interface IHookExecutor {
    
    /**
     * Register a new hook.
     * The delegate defines which method from {@link IPluginListener} is associated to the hook type
     * @param hook
     * @param delegate
     */
    public void registerHook(Hook.Type hook, HookDelegate delegate);
    
    /**
     * Register a custom hook that is fired from within plugins.<br>
     * NOTE: This might get slightly slower than standard system hooks!<br>
     * Do not overuse it.
     * @param hook
     * @param attachedMethodName
     * @implementation Reflect attachedMethodName to {@link IPluginListener}
     */
    public void registerCustomHook(CustomHook hook, String attachedMethodName);
    
    /**
     * Register listener to this executor
     * @param listener
     * @param plugin
     * @param priority
     * @param hook
     */
    public void registerListener(IPluginListener listener, Plugin plugin, Priority priority, Hook.Type hook);
    
    /**
     * Invokes a cancelable hook call to registered plugin listeners,
     * passing along the given hook object. The execution chain will
     * break if this hook has ben set to be cancelled at some point
     * @param hook
     * @return Hook
     */
    public Hook callCancelableHook(CancelableHook hook);
    
    /**
     * Invokes a normal hook call to registered plugin listeners
     * @param hook
     * @return Hook
     */
    public Hook callHook(Hook hook);
    
    /**
     * Invoke a custom registered hook
     * @param hook
     * @return
     */
    public Hook callCustomHook(CustomHook hook);
}
