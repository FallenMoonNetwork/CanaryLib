package net.canarymod.plugin;

import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.Executor;
import net.canarymod.hook.Hook;

/**
 * Container for registered plugin listeners
 * 
 * @author Chris
 * 
 */
public class RegisteredPluginListener {
    private PluginListener listener;
    private Plugin plugin;
    private PriorityNode priorityNode;
    private Executor executor;

    public RegisteredPluginListener(PluginListener l, Plugin plugin, PriorityNode priorityNode, Executor executor) {
        this.listener = l;
        this.plugin = plugin;
        this.priorityNode = priorityNode;
        this.executor = executor;
    }

    public PluginListener getListener() {
        return listener;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public PriorityNode getPriority() {
        return priorityNode;
    }

    /**
     * Execute the event on the listener registered
     * 
     * @param hook
     */
    public void execute(Hook hook) {
        if(hook instanceof CancelableHook) {
            if(((CancelableHook) hook).isCanceled()) {
                return;
            }
            executor.execute(listener, hook);
        }
    }
}
