package net.canarymod.hook;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.canarymod.plugin.PluginListener;

/**
 * A custom hook interface, just for you (the plugin dev)! Implement this
 * properly and register it at the system as custom hook and you're good to go!
 * 
 * @author Chris
 * 
 */
public abstract class CustomHook extends Hook {
    protected String hookName;
    protected String methodName;

    /**
     * Makes sure this CustomHook has a name
     * 
     * @param name
     */
    public CustomHook(String hookName, String methodName) {
        this.hookName = hookName;
        this.methodName = methodName;
        this.type = Type.CUSTOM;
    }

    /**
     * Returns an Object set of data this Hook contains
     * 
     * @return
     */
    @Override
    public Object[] getDataSet() {
        return new Object[] {hookName, methodName};
    }

    /**
     * Gets the name of the custom hook
     * 
     * @return The name of the custom hook
     */
    public final String getHookName() {
        return hookName;
    }
    
    /**
     * Gets the method name of the custom hook
     * @return The method name of the custom hook
     */
    public final String getMethodName() {
        return methodName;
    }
    
    /**
     * Dispatches the custom hook to the given listener.
     * @param listener The listener to dispatch the custom hook to.
     */
    @Override
    public final void dispatch(PluginListener listener) {
        try {
            Method exec = listener.getClass().getDeclaredMethod(this.methodName, new Class<?>[] { CustomHook.class });
            if (exec != null) {
                exec.invoke(listener, this);
            }
        } catch (SecurityException e) {
        } catch (NoSuchMethodException e) {
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        }
    }
}
