package net.canarymod.hook;

/**
 * A custom hook interface, just for you (the plugin dev)! Implement this
 * properly and register it at the system as custom hook and you're good to go!
 * 
 * @author Chris
 * 
 */
public abstract class CustomHook extends Hook {
    protected String hookName;

    /**
     * Makes sure this CustomHook has a name
     * 
     * @param name
     */
    public CustomHook(String name) {
        hookName = name;
    }

    @Override
    public abstract Object[] getDataSet();

    /**
     * Get the name of the custom hook
     * 
     * @return
     */
    public String getHookName() {
        return hookName;
    }
}
