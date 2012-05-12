package net.canarymod.plugin;

import net.canarymod.hook.Hook;

/**
 * Container for registered plugin listeners
 * @author Chris
 *
 */
public class RegisteredPluginListener {
    private IPluginListener listener;
    private Hook.Type hook;
    private Plugin plugin;
    private Priority priority;
    
    public RegisteredPluginListener(IPluginListener l, Hook.Type hook, Plugin plugin, Priority priority) {
        this.listener = l;
        this.hook = hook;
        this.plugin = plugin;
        this.priority = priority;
    }
    public Hook.Type getHook() {
        return hook;
    }
    public IPluginListener getListener() {
        return listener;
    }

    public Plugin getPlugin() {
        return plugin;
    }
    public Priority getPriority() {
        return priority;
    }

}
