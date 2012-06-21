package net.canarymod.config;

import java.io.FileNotFoundException;
import java.io.IOException;

import net.canarymod.Logman;

/**
 * 
 * @author Jos Kuijpers
 *
 */
public class DatabaseConfiguration implements ConfigurationContainer {
    private ConfigurationFile cfg;

    public DatabaseConfiguration(String path) {
        try {
            init(new ConfigurationFile(path));
        } catch (FileNotFoundException e) {
            Logman.logInfo("Could not find the database configuration at " + path + ", creating default.");
            DatabaseConfiguration.createDefault();
            try {
                init(new ConfigurationFile(path));    
            }
            catch(IOException ioe) {
                Logman.logStackTrace("Failed to load database configuration, even after creation!", e);                
            }
        } catch (IOException e) {
            Logman.logStackTrace("Failed to load database configuration!", e);
        }
    }
    
    public DatabaseConfiguration(ConfigurationFile cfg) {
        init(cfg);
    }
    
    private void init(ConfigurationFile cfg) {
        this.cfg = cfg;
    }
    
    /**
     * Reloads the configuration file
     */
    @Override
    public void reload() {
        try {
            init(new ConfigurationFile("config/db.cfg"));
        } catch (IOException e) {
            Logman.logStackTrace("Could not find the database configuration while reloading!", e);
        }
    }
    
    /**
     * Get the configuration file
     */
    public ConfigurationFile getFile() {
    	return cfg;
    }
    
    /**
     * Creates the default configuration
     */
    public static void createDefault() {
    	ConfigurationFile config;
    	try {
    	    config = new ConfigurationFile("config/db.cfg",true);
    	}
    	catch(IOException ioe) {
    	    Logman.logStackTrace("Failed to create default database configuration.", ioe);
    	    return;
    	}

        config.setString("name", "minecraft");
        config.setString("host", "localhost");
        config.setString("username", "admin");
        config.setString("password", "admin");
        config.setInt("port", 3306);
        
        try {
            config.save();
        }
        catch(IOException ioe) {
            Logman.logStackTrace("Failed to create default database configuration.", ioe);
        }
    }
    
    /**
     * Get the URL to the database.
     * 
     * This is a combination of host, port and database
     * @return
     */
    public String getDatabaseUrl() {
    	int port = getDatabasePort();
    	return getDatabaseHost()+((port == 0)?"":(":"+port))+"/"+getDatabaseName();
    }
    
    /**
     * Get the database host, defaulting to localhost
     * @return
     */
    public String getDatabaseHost() {
        return cfg.getString("host","localhost");
    }
    
    /**
     * Get the database port
     * 
     * @return The configured port or 0
     */
    public int getDatabasePort() {
        return cfg.getInt("port",0);
    }
    
    /**
     * Get the name of the database. Defaults to 'minecraft'
     * @return
     */
    public String getDatabaseName() {
    	return cfg.getString("name","minecraft");
    }
    
    /**
     * Get database user
     * This might be null if the datasource is not a password protected database type such as flatfile.
     * @return
     */
    public String getDatabaseUser() {
        return cfg.getString("username");
    }
    
    /**
     * Get database password.
     * This might be null if the datasource is not a password protected database type such as flatfile.
     * @return
     */
    public String getDatabasePassword() {
        return cfg.getString("password");
    }
}
