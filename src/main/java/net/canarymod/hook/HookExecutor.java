package net.canarymod.hook;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import net.canarymod.Canary;
import net.canarymod.ToolBox;
import net.canarymod.plugin.Plugin;
import net.canarymod.plugin.PluginListener;
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
    HashMap<Class<? extends Hook>, ArrayList<RegisteredPluginListener>> listeners = new HashMap<Class<? extends Hook>, ArrayList<RegisteredPluginListener>>();

    /**
     * Register a {@link PluginListener} for a system hook
     */
    @Override
    public void registerListener(PluginListener listener, Plugin plugin) {
        Method[] methods = ToolBox.safeArrayMerge(listener.getClass().getMethods(), listener.getClass().getDeclaredMethods(), new Method[1]);
        for (final Method method : methods) {
            // Check if the method is a hook handling method
            final HookHandler handler = method.getAnnotation(HookHandler.class);

            if (handler == null) {
                continue; // Next, not one of our things
            }
            // Check the parameters for number and type and decide if it's one
            // that is really a handler method
            Class<?>[] parameters = method.getParameterTypes();

            if (parameters.length > 1 || parameters.length == 0) {
                throw new HookConsistencyException("Amount of parameters for " + method.getName() + " is invalid. Expected 1, was " + parameters.length);
            }
            Class<?> hookCls = parameters[0];

            if (!Hook.class.isAssignableFrom(hookCls)) {
                throw new HookConsistencyException("Hook is not assignable from " + hookCls.getName());
            }
            if (!listeners.containsKey(hookCls.asSubclass(Hook.class))) {
                listeners.put(hookCls.asSubclass(Hook.class), new ArrayList<RegisteredPluginListener>());
            }
            Dispatcher dispatcher = new Dispatcher() {

                @Override
                public void execute(PluginListener listener, Hook hook) {
                    try {
                        method.invoke(listener, hook);
                    } catch (IllegalArgumentException ex) {
                        throw new HookExecutionException(ex.getMessage(), ex.getCause());
                    } catch (IllegalAccessException ex) {
                        throw new HookExecutionException(ex.getMessage(), ex.getCause());
                    } catch (InvocationTargetException ex) {
                        throw new HookExecutionException(ex.getMessage(), ex.getCause());
                    } catch (Exception ex) {
                        throw new HookExecutionException(ex.getMessage(), ex.getCause());
                    }
                }
            };
            dispatcher.ignoreCanceled = handler.ignoreCanceled();

            listeners.get(hookCls.asSubclass(Hook.class)).add(new RegisteredPluginListener(listener, plugin, dispatcher, handler.priority()));
        }
    }

    /**
     * Unregisters all listeners for specified plugin
     * @param plugin
     */
    @Override
    public void unregisterPluginListeners(Plugin plugin) {

        Iterator<ArrayList<RegisteredPluginListener>> iter = listeners.values().iterator();

        while (iter.hasNext()) {
            Iterator<RegisteredPluginListener> regIterator = iter.next().iterator();

            while (regIterator.hasNext()) {
                RegisteredPluginListener listener = regIterator.next();

                if (listener.getPlugin().equals(plugin)) {
                    regIterator.remove();
                }
            }
        }
    }

    /**
     * Call a system hook
     */
    @Override
    public void callHook(Hook hook) {
        ArrayList<RegisteredPluginListener> listeners = this.listeners.get(hook.getClass().asSubclass(Hook.class));
        if(listeners != null) {
            for (RegisteredPluginListener l : listeners) {
                try {
                    l.execute(hook);
                } catch (HookExecutionException hexex) {
                    Canary.logStackTrace("Exception while executing Hook: " + hook.getName() + " in PluginListener: " +
                            l.getListener().getClass().getSimpleName() + " (Plugin: " + l.getPlugin().getName() + ")", hexex);
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
            int diff = o2.getPluginPriority() - o1.getPluginPriority();

            if (diff == 0) {
                diff = o2.getMethodPriority().getPriorityValue() - o1.getMethodPriority().getPriorityValue();
            }
            return (int) Math.signum(diff);
        }
    }
}
