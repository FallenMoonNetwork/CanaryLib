package net.canarymod.config;

import java.io.File;
import net.canarymod.Canary;
import net.visualillusionsent.utils.PropertiesFile;

/**
 * @author Jos Kuijpers
 * @author Jason (darkdiplomat)
 */
public class DatabaseConfiguration implements ConfigurationContainer {
    private PropertiesFile cfg;

    public DatabaseConfiguration(String path) {
        File test = new File(path);

        if (!test.exists()) {
            Canary.logInfo("Could not find the database configuration at " + path + ", creating default.");
            DatabaseConfiguration.createDefault();
        }
        this.cfg = new PropertiesFile(path);
    }

    public DatabaseConfiguration(PropertiesFile cfg) {
        this.cfg = cfg;
    }

    /**
     * Reloads the configuration file
     */
    @Override
    public void reload() {
        cfg.reload();
    }

    /**
     * Get the configuration file
     */
    @Override
    public PropertiesFile getFile() {
        return cfg;
    }

    /**
     * Creates the default configuration
     */
    public static void createDefault() {
        PropertiesFile config;

        config = new PropertiesFile("config" + File.separatorChar + "db.cfg");

        config.setString("name", "minecraft");
        config.setString("host", "localhost");
        config.setString("username", "admin");
        config.setString("password", "admin");
        config.setInt("port", 3306);
        config.setInt("maxConnections", 5);

        config.save();
    }

    /**
     * Get the URL to the database.
     * This is a combination of host, port and database
     * 
     * @return
     */
    public String getDatabaseUrl(String driver) {
        int port = getDatabasePort();

        return "jdbc:" + driver + "://" + getDatabaseHost() + ((port == 0) ? "" : (":" + port)) + "/" + getDatabaseName();
    }

    /**
     * Get the database host, defaulting to localhost
     * 
     * @return
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
     * @return
     */
    public String getDatabaseName() {
        return cfg.getString("name", "minecraft");
    }

    /**
     * Get database user
     * This might be null if the datasource is not a password protected database type such as flatfile.
     * 
     * @return
     */
    public String getDatabaseUser() {
        return cfg.getString("username");
    }

    /**
     * Get database password.
     * This might be null if the datasource is not a password protected database type such as flatfile.
     * 
     * @return
     */
    public String getDatabasePassword() {
        return cfg.getString("password");
    }

    /**
     * Get the maximum number of concurrent connections to the database.
     * This might be null if the datasource is not a connection oriented database type such as flatfile.
     * 
     * @return
     */
    public int getDatabaseMaxConnections() {
        return cfg.getInt("maxConnections");
    }
}
