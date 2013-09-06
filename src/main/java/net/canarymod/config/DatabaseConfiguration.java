package net.canarymod.config;

import java.io.File;

import net.canarymod.Canary;
import net.visualillusionsent.utils.PropertiesFile;

/**
 * Database Configuration settings
 *
 * @author Jos Kuijpers
 * @author Jason (darkdiplomat)
 */
public class DatabaseConfiguration implements ConfigurationContainer {
    private PropertiesFile cfg;

    public DatabaseConfiguration(String path) {
        File test = new File(path);

        if (!test.exists()) {
            Canary.logInfo("Could not find the database configuration at " + path + ", creating default.");
        }
        this.cfg = new PropertiesFile(path);
        verifyConfig();
    }

    /** Reloads the configuration file */
    @Override
    public void reload() {
        cfg.reload();
        verifyConfig();
    }

    /** Get the configuration file */
    @Override
    public PropertiesFile getFile() {
        return cfg;
    }

    /** Creates the default configuration */
    private void verifyConfig() {
        cfg.getString("name", "minecraft");
        cfg.getString("host", "localhost");
        cfg.getString("username", "admin");
        cfg.getString("password", "admin");
        cfg.getInt("port", 3306);
        cfg.getInt("maxConnections", 5);
        cfg.save();
    }

    /**
     * Get the URL to the database.
     * This is a combination of host, port and database
     *
     * @param driver
     *         the JDBC driver name (ie: mysql or sqlite)
     *
     * @return database url
     */
    public String getDatabaseUrl(String driver) {
        int port = getDatabasePort();

        return "jdbc:" + driver + "://" + getDatabaseHost() + ((port == 0) ? "" : (":" + port)) + "/" + getDatabaseName();
    }

    /**
     * Get the database host, defaulting to localhost
     *
     * @return database host
     */
    public String getDatabaseHost() {
        return cfg.getString("host", "localhost");
    }

    /**
     * Get the database port
     *
     * @return The configured port or 0
     */
    public int getDatabasePort() {
        return cfg.getInt("port", 0);
    }

    /**
     * Get the name of the database. Defaults to 'minecraft'
     *
     * @return database name
     */
    public String getDatabaseName() {
        return cfg.getString("name", "minecraft");
    }

    /**
     * Get database user
     * This might be null if the datasource is not a password protected database type such as XML.
     *
     * @return database username
     */
    public String getDatabaseUser() {
        return cfg.getString("username");
    }

    /**
     * Get database password.
     * This might be null if the datasource is not a password protected database type such as XML.
     *
     * @return database password
     */
    public String getDatabasePassword() {
        return cfg.getString("password");
    }

    /**
     * Get the maximum number of concurrent connections to the database.
     * This might be null if the datasource is not a connection oriented database type such as XML.
     *
     * @return database maximum connections
     */
    public int getDatabaseMaxConnections() {
        return cfg.getInt("maxConnections");
    }
}
