package net.canarymod.hook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
 * @author Yariv Livay
 *
 */
public class HookExecutor implements HookExecutorInterface {
    ArrayList<RegisteredPluginListener> listeners_dep = new ArrayList<RegisteredPluginListener>();
    HashMap<Class<? extends Hook>, RegisteredPluginListener> listeners = new HashMap<Class<? extends Hook>, RegisteredPluginListener>();

    /**
     * Register a {@link PluginListener} for a system hook
     * <b>NOTE: </b>we recommend you to use custom priority values in order to give server administrators
     * the possibility to solve execution order conflicts with other plugins themselves.
     */
    @Override
    public void registerListener(PluginListener listener, Plugin plugin, Priority priority) {
        registerListener(listener, plugin, priority.name(), hook);
    }
    
    /**
     * Register a {@link PluginListener} for a system hook
     */
    @Override
    public void registerListener(PluginListener listener, Plugin plugin, String priorityName) {
        PriorityNode priorityNode = plugin.getPriorityNode(priorityName);
        if (priorityNode != null) {
            listeners_dep.add(new RegisteredPluginListener(listener, hook, plugin, plugin.getPriorityNode(priorityName)));
            //Sort by plugin priority system
            Collections.sort(listeners_dep, new PluginComparator());
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
        Iterator<RegisteredPluginListener> iter = listeners_dep.iterator();
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
            for (RegisteredPluginListener l : listeners_dep) {
                if (l.getHook() == hook.getType()) {
                    if (hook.isCanceled()) {
                        int prioValue = l.getPriority().getValue();
                        if(!(prioValue <= 100 || prioValue >= 700)) {
                            continue;
                        }
                    }
                    hook.dispatch(l.getListener());
                }
            }
        }
    }
    
    class PluginComparator implements Comparator<RegisteredPluginListener> {
        @Override
        public int compare(RegisteredPluginListener o1, RegisteredPluginListener o2) {
            if (o1 == o2) {
                return 0;
            }
            int diff = o2.getPriority().getValue() - o1.getPriority().getValue();
            if (diff == 0) {
                diff = o2.getPlugin().getPriority() - o1.getPlugin().getPriority();
            }
            if ((diff == 0) && (!o1.equals(o2)) && (!o1.getPriority().getName().equals(o2.getPriority().getName()))) {
                Logman.logWarning("Plugin '" + o1.getPlugin().getName() + "' and '" + o2.getPlugin().getName() + "' shouldn't have the same priority. Edit Canary.inf to resolve the conflict.");
            }
            return (int)Math.signum(diff);
        }
    }
}
