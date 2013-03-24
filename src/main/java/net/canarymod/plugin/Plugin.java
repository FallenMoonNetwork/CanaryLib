package net.canarymod.plugin;


import net.canarymod.Logman;
import net.canarymod.commandsys.CommandOwner;

/**
 * A Canary Mod Plugin.
 *
 * @author Chris
 *
 */
public abstract class Plugin implements CommandOwner {

    protected String version;
    private CanaryClassLoader loader = null;
    private int priority = 0;
    private final Logman logger;

    public Plugin() {
        logger = Logman.getLogman(getName());
    }
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
        return this.getClass().getSimpleName();
    }


    /**
     * Gets the plugin's priority.
     * @return The plugin's priority.
     */
    final public int getPriority() {
        return this.priority;
    }


    /**
     * Set this plugins priority level. This will affect the order of hook execution.
     * @param priority
     */
    final public void setPriority(int priority) {
        this.priority = priority;
    }


    /**
     * Set the version string of this plugin
     * @param version
     */
    final public void setVersion(String version) {
        this.version = version;
    }


    /**
     * Get the version string of this plugin
     * @return
     */
    final public String getVersion() {
        return this.version;
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

    /**
     * Gets the ClassLoader that has loaded this plugin
     * @return
     */
    public CanaryClassLoader getLoader() {
        return loader;
    }

    /**
     * Set the ClassLoader that has loaded this plugin.
     * @param loader
     */
    public void setLoader(CanaryClassLoader loader) {
        if(this.loader == null) {
            this.loader = loader;
        }
    }

    public Logman getLogman() {
        return logger;
    }
}
