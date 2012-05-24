package net.canarymod.config;

import java.io.IOException;

import net.canarymod.Logman;
import net.canarymod.database.Database;

public class NetworkConfiguration implements ConfigurationContainer {
    private ConfigurationFile cfg;
    private Database.Type backboneType;
    
    public NetworkConfiguration(ConfigurationFile cfg) {
        init(cfg);
    }
    
    private void init(ConfigurationFile cfg) {
        this.cfg = cfg;
    }
    
    @Override
    public void reload() {
        try {
            init(new ConfigurationFile("config/net.cfg"));
        } catch (IOException e) {
            Logman.logStackTrace("Could not find the server configuration while reloading! (Wtf man?!)", e);
        }
    }
    
    /**
     * Get datasource type
     * @return
     */
    public Database.Type getDatasourceType() {
        return backboneType;
    }
    
    /**
     * Get the database url
     * This might be null if the Datasource is not a password protected database type such as flatfile.
     * @return
     */
    public String getDatabaseUrl() {
        return cfg.getString("db");
    }
    
    /**
     * Get database user
     * This might be null if the Datasource is not a password protected database type such as flatfile.
     * @return
     */
    public String getDatabaseUser() {
        return cfg.getString("db-user");
    }
    
    /**
     * Get database password.
     * This might be null if the Datasource is not a password protected database type such as flatfile.
     * @return
     */
    public String getDatabasePassword() {
        return cfg.getString("db-password");
    }
}
