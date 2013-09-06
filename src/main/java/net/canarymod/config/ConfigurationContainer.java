package net.canarymod.config;

import net.visualillusionsent.utils.PropertiesFile;

/**
 * This is an access container for ConfigurationFile objects
 * to make access more convenient
 *
 * @author Chris Ksoll
 */
public interface ConfigurationContainer {

    /** Reloads the configuration */
    public void reload();

    /**
     * Get the configuration file
     *
     * @return ConfigurationFile
     */
    public PropertiesFile getFile();
}
