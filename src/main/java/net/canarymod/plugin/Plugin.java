package net.canarymod.plugin;

import net.canarymod.Canary;
import net.canarymod.commandsys.CommandOwner;
import net.canarymod.logger.Logman;
import net.canarymod.tasks.TaskOwner;
import net.visualillusionsent.utils.PropertiesFile;

/**
 * A Canary Mod Plugin.
 * 
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public abstract class Plugin implements CommandOwner, TaskOwner {
    protected String version, author;
    private int priority = 0;
    private boolean isClosed = false;
    private boolean disabled = true;

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
     * Return the Plugin's name.
     * 
     * @return the Plugin's name
     */
    @Override
    final public String getName() {
        return getCanaryInf().getString("name");
    }

    /**
     * Gets the Plugin's priority.
     * 
     * @return The Plugin's priority.
     */
    final public int getPriority() {
        return this.priority;
    }

    /**
     * Set this Plugin's priority level. This will affect the order of hook execution.
     * 
     * @param priority
     *            the Priority level
     */
    final public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Get the version string of the Plugin
     * 
     * @return the version
     */
    final public String getVersion() {
        return getCanaryInf().getString("version", "UNKNOWN");
    }

    /**
     * Get this Plugin Author's name
     * 
     * @return Author's name
     */
    final public String getAuthor() {
        return getCanaryInf().getString("author", "UNKNOWN");
    }

    /**
     * Gets the name of the Plugin's Jar File
     * 
     * @return the Jar File name
     */
    public String getJarName() {
        return getCanaryInf().getString("jarName");
    }

    /**
     * Gets the path of the Plugin's Jar file as {@literal "plugins/<jar>"}
     * 
     * @return the Plugin's Jar path
     */
    public String getJarPath() {
        return getCanaryInf().getString("jarPath");
    }

    public Logman getLogman() {
        return Logman.getLogman(getName());
    }

    /**
     * Gets the Plugin's Canary.inf file
     * <p>
     * NOTE: DO NOT CALL {@link PropertiesFile#save()}<br>
     * Saving is unsupported
     * <p>
     * If the Plugin is reloaded, any changes will be lost
     * 
     * @return the Plugin's Canary.inf
     */
    public final PropertiesFile getCanaryInf() {
        return Canary.loader().getPluginInf(getClass().getSimpleName());
    }

    /**
     * Check if this plugin needs a new instance instead of just re-enabling it
     * 
     * @return {@code true} if closed; {@code false} otherwise
     */
    public final boolean isClosed() {
        return isClosed;
    }

    /**
     * Marks this plugin to be re-instantiated on reloading/re-enabling
     */
    final void markClosed() {
        isClosed = true;
    }

    /**
     * Gets whether this Plugin is disabled
     * 
     * @return {@code true} if disabled; {@code false} if enabled
     */
    public final boolean isDisabled() {
        return disabled;
    }

    /**
     * Toggles the disabled state of the Plugin
     */
    final void toggleDisabled() {
        this.disabled = !this.disabled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 6; // number of chars in "Plugin" :P

        return hash * getName().hashCode(); // anyone got a better idea?
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(Object obj) {
        return obj == this; // object instances should be completely equal
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        return String.format("Plugin[Name: '%s' Version: '%s' Author: '%s' JarPath: '%s']", getName(), getVersion(), getAuthor(), getJarPath());
    }
}
