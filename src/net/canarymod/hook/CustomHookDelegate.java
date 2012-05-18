package net.canarymod.hook;

import net.canarymod.plugin.PluginListener;

public abstract class CustomHookDelegate {
    protected PluginListener li = null;
    protected String hookName;
    protected String attachedMethodName;

    /**
     * Create a new custom hook delegate
     * 
     * @param hook
     * @param attachedMethod
     */
    public CustomHookDelegate(String hook, String attachedMethod) {
        hookName = hook;
        attachedMethodName = attachedMethod;
    }

    /**
     * Set the {@link PluginListener} reference to use for dispatching.
     * 
     * @param li
     */
    public void setListener(PluginListener li) {
        this.li = li;
    }

    public String getHookName() {
        return hookName;
    }

    public String getAttachedMethodName() {
        return attachedMethodName;
    }

    /**
     * This calls a pre-defined method from {@link PluginListener}
     * 
     * @param hook
     * @return
     * @throws CustomHookConsistencyException
     */
    public abstract CustomHook callHook(CustomHook hook) throws CustomHookConsistencyException;
}
