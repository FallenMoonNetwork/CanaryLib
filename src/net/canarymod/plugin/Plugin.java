package net.canarymod.plugin;

/**
 * A Canary Mod Plugin.
 * 
 * @author Chris
 * 
 */
public abstract class Plugin {

    protected String name = null;
    
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
    public String getName() {
        if (this.name != null) {
            return this.name;
        }
        else {
            return this.getClass().getSimpleName();
        }
    }
    
    void setName(String name) {
        this.name = name;
    }

    /**
     * Pass me the hash please
     */
    @Override
    public int hashCode() {
        int hash = 6; //number of chars in "Plugin" :P
        return hash * getName().hashCode(); //anyone got a better idea?
    }

    public boolean equals(Object obj) {
        return false;
    }
}
