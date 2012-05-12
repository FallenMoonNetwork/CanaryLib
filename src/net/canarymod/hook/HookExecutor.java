package net.canarymod.hook;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.canarymod.LogManager;
import net.canarymod.hook.Hook.Type;
import net.canarymod.math.FastSortPluginListeners;
import net.canarymod.plugin.Plugin;
import net.canarymod.plugin.IPluginListener;
import net.canarymod.plugin.Priority;
import net.canarymod.plugin.RegisteredPluginListener;

public class HookExecutor implements IHookExecutor {
    List<RegisteredPluginListener> listeners = new ArrayList<RegisteredPluginListener>();
    HashMap<Type,HookDelegate> delegates = new HashMap<Type,HookDelegate>();
    HashMap<String,CustomHookDelegate> customDelegates = new HashMap<String,CustomHookDelegate>();

    /**
     * Register a hook to this Executor.
     * A hook MUST be registered before it can be fired or else the system won't know
     * which method to invoke on the listener and throw an {@link UnkownHookException}
     */
    @Override
    public void registerHook(Type hook, HookDelegate delegate) {
        if(delegates.containsKey(hook)) {
            throw new IllegalArgumentException("Hook "+hook.name()+" is already registered to this HookExecutor!");
        }
        delegates.put(hook, delegate);
    }

    /**
     * Register a custom plugin hook at this {@link HookExecutor}.
     * Please note that custom hooks are slower than normal system hooks
     * and registering them for "spamming hooks" (hooks that would be called very often)
     * should be avoided as good as possible but one or two of them shouldn't be an issue
     * @param hook The custom hook to register
     */
    @Override
    public void registerCustomHook(CustomHook hook, String attachedMethodName) {
        if(customDelegates.containsKey(hook.getHookName())) {
            throw new IllegalArgumentException("Custom Hook "+hook.getHookName()+" is already registered to this HookExecutor!");
        }
        customDelegates.put(hook.getHookName(), new CustomHookDelegate(hook.getHookName(), attachedMethodName) {
            
            @Override
            public CustomHook callHook(CustomHook hook) throws CustomHookConsistencyException {
                try {
                    Method exec = li.getClass().getDeclaredMethod(attachedMethodName, new Class<?>[]{CustomHook.class});
                    hook = (CustomHook) exec.invoke(li, hook);
                } 
                catch (SecurityException e) {
                    LogManager.get().logStackTrace(e);
                    return hook;
                } 
                catch (NoSuchMethodException e) {
                    LogManager.get().logStackTrace(e);
                    throw new CustomHookConsistencyException("Failed to register "+attachedMethodName+" on "+getHookName());
                } 
                catch (IllegalArgumentException e) {
                    LogManager.get().logStackTrace(e);
                    throw new CustomHookConsistencyException("Failed to register "+attachedMethodName+" on "+getHookName());
                } 
                catch (IllegalAccessException e) {
                    LogManager.get().logStackTrace(e);
                    throw new CustomHookConsistencyException("Failed to register "+attachedMethodName+" on "+getHookName());
                } 
                catch (InvocationTargetException e) {
                    LogManager.get().logStackTrace(e);
                    throw new CustomHookConsistencyException("Failed to register "+attachedMethodName+" on "+getHookName());
                }
                return null;
            }
        });
    }
    
    /**
     * Register a {@link IPluginListener} for a system hook
     */
    @Override
    public void registerListener(IPluginListener listener, Plugin plugin, Priority priority, Hook.Type hook) {
        listeners.add(new RegisteredPluginListener(listener, hook, plugin, priority));
      //Sort by priority ordinal
        listeners = FastSortPluginListeners.sort(listeners);
    }

    /**
     * Call a cancelable system hook!
     */
    @Override
    public Hook callCancelableHook(CancelableHook hook) {
        for(RegisteredPluginListener l : listeners) {
            if(l.getHook() == hook.getType()) {
                
                // -----------------------------------------------------------------
                try {
                    hook = (CancelableHook) dispatchHook(l.getListener(), hook);
                } catch (UnkownHookException e) {
                    LogManager.get().logStackTrace(e);
                    return hook;
                }
                //-----------------------------------------------------------------
                
                if(hook.isCancelled) {
                    return hook;
                }
            }
        }
        return hook;
    }

    /**
     * Call a normal system hook
     */
    @Override
    public Hook callHook(Hook hook) {
        for(RegisteredPluginListener l : listeners) {
            if(l.getHook() == hook.getType()) {
                
                // -----------------------------------------------------------------
                try {
                    hook = dispatchHook(l.getListener(), hook);
                } catch (UnkownHookException e) {
                    LogManager.get().logStackTrace(e);
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
        for(RegisteredPluginListener l : listeners) {
            if(l.getHook() == Hook.Type.CUSTOM) {
                try {
                    hook = (CustomHook) dispatchCustomHook(l.getListener(), hook);
                } catch (UnkownHookException e) {
                    LogManager.get().logStackTrace(e);
                    return hook;
                }
            }
        }
        return hook;
    }
    
    /**
     * This delegates the custom hook to the listener and calls appropriate methods according
     * to what was defined in the HookDelegate when registering the hook
     * @param listener
     * @param hook
     * @return
     * @throws UnkownHookException
     */
    private CustomHook dispatchCustomHook(IPluginListener listener, CustomHook hook) throws UnkownHookException {
        CustomHookDelegate delegate = customDelegates.get(hook.getHookName());
        if(delegate == null) { 
            throw new UnkownHookException("Tried to fire an unregistered hook! (" + "Hook" + hook.getType() + " in "+this.getClass().getSimpleName()+")" );
        }
        delegate.setListener(listener);
        try {
            return delegate.callHook(hook);
        } catch (CustomHookConsistencyException e) {
            LogManager.get().logStackTrace(e);
            return hook;
        }
    }
    
    /**
     * This delegates a system hook to its listener and invokes the method that was defined with the HookDelegate
     * when registering this hook
     * @param listener
     * @param hook
     * @return
     * @throws UnkownHookException
     */
    private Hook dispatchHook(IPluginListener listener, Hook hook) throws UnkownHookException {
        HookDelegate delegate = delegates.get(hook.getType());
        if(delegate == null) { 
            throw new UnkownHookException("Tried to fire an unregistered hook! (" + "Hook" + hook.getType() + " in "+this.getClass().getSimpleName()+")" );
        }
        delegate.setListener(listener);
        return delegate.callHook(hook);
    }
}
