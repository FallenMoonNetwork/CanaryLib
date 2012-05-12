package net.canarymod.hook;

import net.canarymod.plugin.IPluginListener;

public abstract class HookDelegate {
    protected IPluginListener li = null;
    
    /**
     * Create Hook Delegate with empty {@link IPluginListener}
     */
    public HookDelegate() {
        
    }
    
    /**
     * Set the {@link IPluginListener} reference to use for dispatching.
     * @param li
     */
    public void setListener(IPluginListener li) {
        this.li = li;
    }
    
    /**
     * This calls a pre-defined method from {@link IPluginListener}
     * @param hook
     * @return
     */
    public abstract CustomHook callHook(Hook hook);
}
