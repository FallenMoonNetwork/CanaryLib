package net.canarymod.plugin;

import net.canarymod.commandsys.CommandOwner;

/**
 * A Canary Mod Plugin.
 *
 * @author Chris
 *
 */
public abstract class Plugin implements CommandOwner {

    protected String name = null;
    private int priority = 0;

    /**
     * CanaryMod will call this upon enabling this plugin
     */
    public abstract void enable();

    /**
     * CanaryMod will call this upon disabling this plugin
     */
    public abstract void disable();

    /**
     * Return this plugins name.
     *
     * @return
     */
    @Override
    final public String getName() {
        if (this.name != null) {
            return this.name;
        }
        else {
            return this.getClass().getSimpleName();
        }
    }

    final void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the plugin's priority.
     * @return The plugin's priority.
     */
    final public int getPriority() {
        return this.priority;
    }


    final public void setPriority(int priority) {
        this.priority = priority;
    }


    /**
     * Pass me the hash please
     */
    @Override
    public int hashCode() {
        int hash = 6; //number of chars in "Plugin" :P
        return hash * getName().hashCode(); //anyone got a better idea?
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
