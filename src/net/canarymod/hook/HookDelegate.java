package net.canarymod.hook;

import net.canarymod.plugin.PluginListener;

public abstract class HookDelegate {
    protected PluginListener li = null;

    /**
     * Create Hook Delegate with empty {@link PluginListener}
     */
    public HookDelegate() {

    }

    /**
     * Set the {@link PluginListener} reference to use for dispatching.
     * 
     * @param li
     */
    public void setListener(PluginListener li) {
        this.li = li;
    }

    /**
     * This calls a pre-defined method from {@link PluginListener}
     * 
     * @param hook
     * @return
     */
    public abstract CustomHook callHook(Hook hook);
}
