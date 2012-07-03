package net.canarymod.hook;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import net.canarymod.Logman;
import net.canarymod.plugin.PluginListener;
import net.canarymod.plugin.Plugin;
import net.canarymod.plugin.Priority;
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
    HashMap<String, CustomHookDelegate> customDelegates = new HashMap<String, CustomHookDelegate>();

    /**
     * Register a custom plugin hook at this {@link HookExecutor}. Please note
     * that custom hooks are slower than normal system hooks and registering
     * them for "spamming hooks" (hooks that would be called very often) should
     * be avoided as good as possible but one or two of them shouldn't be an
     * issue
     * 
     * @param hook
     *            The custom hook to register
     */
    @Override
    public void registerCustomHook(CustomHook hook, String attachedMethodName) {
        if (customDelegates.containsKey(hook.getHookName())) {
            throw new IllegalArgumentException("Custom Hook " + hook.getHookName() + " is already registered to this HookExecutor!");
        }
        customDelegates.put(hook.getHookName(), new CustomHookDelegate(hook.getHookName(), attachedMethodName) {

            @Override
            public CustomHook callHook(CustomHook hook) throws CustomHookConsistencyException {
                try {
                    Method exec = li.getClass().getDeclaredMethod(attachedMethodName, new Class<?>[] { CustomHook.class });
                    hook = (CustomHook) exec.invoke(li, hook);
                } catch (SecurityException e) {
                	Logman.logStackTrace(e.getMessage(), e);
                    return hook;
                } catch (NoSuchMethodException e) {
                	Logman.logStackTrace(e.getMessage(), e);
                    throw new CustomHookConsistencyException("Failed to call custom hook " + attachedMethodName + " on " + getHookName());
                } catch (IllegalArgumentException e) {
                	Logman.logStackTrace(e.getMessage(), e);
                    throw new CustomHookConsistencyException("Failed to call custom hook " + attachedMethodName + " on " + getHookName());
                } catch (IllegalAccessException e) {
                	Logman.logStackTrace(e.getMessage(), e);
                    throw new CustomHookConsistencyException("Failed to call custom hook " + attachedMethodName + " on " + getHookName());
                } catch (InvocationTargetException e) {
                	Logman.logStackTrace(e.getMessage(), e);
                    throw new CustomHookConsistencyException("Failed to call custom hook " + attachedMethodName + " on " + getHookName());
                }
                return hook;
            }
        });
    }

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
        listeners.add(new RegisteredPluginListener(listener, hook, plugin, plugin.getPriorityNode(priorityName)));
        //Sort by priority ordinal
        Collections.sort(listeners, new PluginComparator());
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
     * Call a cancelable system hook
     */
    @Override
    public Hook callCancelableHook(CancelableHook hook) {
        for (RegisteredPluginListener l : listeners) {
            if (l.getHook() == hook.getType()) {
                if (hook.isCanceled()) {
                    //If the hook is cancelled only forward it to plugins that are PASSIVE
                    //or HIGH or CRITICAL
                    String priorityName = l.getPriority().getName();
                    if (priorityName.equals("PASSIVE") || priorityName.equals("HIGH") || priorityName.equals("CRITICAL")) {
                        continue;
                    }
                    // TODO Need to check if we want to reference the priority values here instead of the names.
                }
                // -----------------------------------------------------------------
                try {
                    hook = (CancelableHook) dispatchHook(l.getListener(), hook);
                } catch (UnknownHookException e) {
                    Logman.logStackTrace(e.getMessage(), e);
                    return hook;
                }
                // -----------------------------------------------------------------
            }
        }
        return hook;
    }

    /**
     * Call a normal system hook
     */
    @Override
    public Hook callHook(Hook hook) {
        for (RegisteredPluginListener l : listeners) {
            if (l.getHook() == hook.getType()) {

                // -----------------------------------------------------------------
                try {
                    hook = dispatchHook(l.getListener(), hook);
                } catch (UnknownHookException e) {
                	Logman.logStackTrace(e.getMessage(), e);
                    return hook;
                }
                // -----------------------------------------------------------------
            }
        }
        return hook;
    }

    /**
     * Call a custom hook
     */
    @Override
    public Hook callCustomHook(CustomHook hook) {
        for (RegisteredPluginListener l : listeners) {
            if (l.getHook() == Hook.Type.CUSTOM) {
                try {
                    hook = (CustomHook) dispatchCustomHook(l.getListener(), hook);
                } catch (UnknownHookException e) {
                	Logman.logStackTrace(e.getMessage(), e);
                    return hook;
                } catch(CustomHookConsistencyException e) {
                    Logman.logStackTrace(e.getMessage(), e);
                }
            }
        }
        return hook;
    }

    /**
     * This delegates the custom hook to the listener and calls appropriate
     * methods according to what was defined in the {@link HookDelegate} when
     * registering the hook
     * 
     * @param listener
     * @param hook
     * @return
     * @throws UnknownHookException
     * @throws CustomHookConsistencyException 
     */
    private CustomHook dispatchCustomHook(PluginListener listener, CustomHook hook) throws UnknownHookException, CustomHookConsistencyException {
        CustomHookDelegate delegate = customDelegates.get(hook.getHookName());
        if (delegate == null) {
            throw new UnknownHookException("Tried to fire an unregistered custom hook! (" + "Hook" + hook.getName() + " in " + this.getClass().getSimpleName() + ")");
        }
        delegate.setListener(listener);
        return delegate.callHook(hook);
    }

    /**
     * This delegates a system hook to its listener and invokes the method that
     * was defined with the {@link HookDelegate}
     * 
     * @param listener
     * @param hook
     * @return
     * @throws UnknownHookException
     */
    private Hook dispatchHook(PluginListener listener, Hook hook) throws UnknownHookException {
        HookDelegate delegate = hook.getType().getDelegate();
        if (delegate == null) {
            throw new UnknownHookException("Tried to fire an invalid hook! " + "Hook" + hook.getType() + " in " + this.getClass().getSimpleName() + " has no Delegate attached!");
        }
        delegate.setListener(listener);
        return delegate.callHook(hook);
    }
    
    class PluginComparator implements Comparator<RegisteredPluginListener> {
        @Override
        public int compare(RegisteredPluginListener o1, RegisteredPluginListener o2) {
            return (int)Math.signum(o2.getPriority().getValue() - o1.getPriority().getValue());
        }
    }
}
