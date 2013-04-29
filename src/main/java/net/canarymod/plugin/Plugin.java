package net.canarymod.plugin;


import net.canarymod.commandsys.CommandOwner;
import net.canarymod.logger.Logman;
import net.canarymod.tasks.TaskOwner;
import net.visualillusionsent.utils.PropertiesFile;


/**
 * A Canary Mod Plugin.
 *
 * @author Chris
 *
 */
public abstract class Plugin implements CommandOwner, TaskOwner {

    protected String version, author;
    private CanaryClassLoader loader = null;
    private int priority = 0;
    private PropertiesFile inf;

    /**
     * CanaryMod will call this upon enabling this plugin
     *
     * @return {@code true} to signal successful enable; {@code false} if known to be unable to run
     */
    public abstract boolean enable();

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
     * Get the version string of this plugin
     * @return
     */
    final public String getVersion() {
        return inf.getString("version");
    }

    /**
     * Get this plugins author name
     * @return
     */
    final public String getAuthor() {
        return inf.getString("author");
    }

    /**
     * Pass me the hash please
     */
    @Override
    public int hashCode() {
        int hash = 6; // number of chars in "Plugin" :P

        return hash * getName().hashCode(); // anyone got a better idea?
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Plugin)) {
            return false;
        }
        return ((Plugin)obj).getName().equals(getName());
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
    public void setLoader(CanaryClassLoader loader, PropertiesFile inf, String jarname) {
        if (this.loader == null) {
            this.loader = loader;
        }
        if(this.inf == null) {
            this.inf = inf;
            this.inf.setString("jarname", jarname);
        }
    }

    public String getJarName() {
        return this.inf.getString("jarname");
    }

    public Logman getLogman() {
        return Logman.getLogman(getName());
    }
}
