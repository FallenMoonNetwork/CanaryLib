package net.canarymod.hook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import net.canarymod.Logman;
import net.canarymod.hook.Hook.Type;
import net.canarymod.plugin.PluginListener;
import net.canarymod.plugin.Plugin;
import net.canarymod.plugin.Priority;
import net.canarymod.plugin.PriorityNode;
import net.canarymod.plugin.RegisteredPluginListener;

/**
 * Stores registered listeners and performs hook dispatches.
 * 
 * @author Chris Ksoll
 * @author Jos Kuijpers
 *
 */
public class HookExecutor implements HookExecutorInterface {
    ArrayList<RegisteredPluginListener> listeners = new ArrayList<RegisteredPluginListener>();

    /**
     * Register a {@link PluginListener} for a system hook
     * @deprecated Use {@link HookExecutor#registerListener(PluginListener, Plugin, String, net.canarymod.hook.Hook.Type)} instead.
     */
    @Override
    public void registerListener(PluginListener listener, Plugin plugin, Priority priority, Hook.Type hook) {
        registerListener(listener, plugin, priority.name(), hook);
    }
    
    /**
     * Register a {@link PluginListener} for a system hook
     */
    @Override
    public void registerListener(PluginListener listener, Plugin plugin, String priorityName, Hook.Type hook) {
        PriorityNode priorityNode = plugin.getPriorityNode(priorityName);
        if (priorityNode != null) {
            listeners.add(new RegisteredPluginListener(listener, hook, plugin, plugin.getPriorityNode(priorityName)));
            //Sort by plugin priority system
            Collections.sort(listeners, new PluginComparator());
        }
        else {
            Logman.logSevere("Failed to register hook '" + hook.name() + "' for plugin '" + plugin.getName() + "'. No such priority: " + priorityName);
        }
    }

    /**
     * Unregisters all listeners for specified plugin
     * @param plugin
     */
    @Override
    public void unregisterPluginListeners(Plugin plugin) {
        Iterator<RegisteredPluginListener> iter = listeners.iterator();
        while(iter.hasNext()) {
            if(iter.next().getPlugin() == plugin) {
                iter.remove();
            }
        }
    }
    
    /**
     * Call a system hook
     */
    @Override
    public void callHook(Hook hook) {
        if (hook instanceof CustomHook && hook.getType() != Type.CUSTOM) {
            Logman.logWarning("Failed to call custom hook '" + ((CustomHook)hook).getHookName() + "'. Hook type must be CUSTOM.");
        }
        else {
            for (RegisteredPluginListener l : listeners) {
                if (l.getHook() == hook.getType()) {
                    if (hook.isCanceled()) {
                        //If the hook is cancelled only forward it to plugins that are PASSIVE
                        //or HIGH or CRITICAL
                        String priorityName = l.getPriority().getName();
                        if (!(priorityName.equals("PASSIVE") || priorityName.equals("HIGH") || priorityName.equals("CRITICAL"))) {
                            continue;
                        }
                        // TODO Need to check if we want to reference the priority values here instead of the names.
                    }
                    hook.dispatch(l.getListener());
                }
            }
        }
    }
    
    class PluginComparator implements Comparator<RegisteredPluginListener> {
        @Override
        public int compare(RegisteredPluginListener o1, RegisteredPluginListener o2) {
            int diff = o2.getPriority().getValue() - o1.getPriority().getValue();
            if (diff == 0) {
                diff = o2.getPlugin().getPriority() - o1.getPlugin().getPriority();
            }
            if (diff == 0) {
                Logman.logWarning("Plugin '" + o1.getPlugin().getName() + "' and '" + o2.getPlugin().getName() + "' shouldn't have the same priority. Edit Canary.inf to resolve the conflict.");
            }
            return (int)Math.signum(diff);
        }
    }
}
