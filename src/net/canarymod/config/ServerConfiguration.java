package net.canarymod.config;

import java.io.FileNotFoundException;
import java.io.IOException;

import net.canarymod.Logman;
import net.canarymod.database.Database;

/**
 * 
 * @author Jos Kuijpers
 *
 */
public class ServerConfiguration implements ConfigurationContainer {
    private ConfigurationFile cfg;
    private Database.Type dataSourceType;

    public ServerConfiguration(String path) {
        try {
            init(new ConfigurationFile(path));
        } catch (FileNotFoundException e) {
            Logman.logInfo("Could not find the server configuration at " + path + ", creating default.");
            ServerConfiguration.createDefault();
            try {
                init(new ConfigurationFile(path));    
            }
            catch(IOException ioe) {
                Logman.logStackTrace("Failed to load server configuration, even after creation!", e);                
            }
        } catch (IOException e) {
            Logman.logStackTrace("Failed to load server configuration!", e);
        }
    }
    
    public ServerConfiguration(ConfigurationFile cfg) {
        init(cfg);
    }
    
    private void init(ConfigurationFile cfg) {
        this.cfg = cfg;
        
        String typeVal = cfg.getString("data-source", "flatfile");
        if(typeVal.equalsIgnoreCase("mysql")) {
        	dataSourceType = Database.Type.MYSQL;
        }
        else {
            dataSourceType = Database.Type.FLATFILE;
        }
    }
    
    /**
     * Reloads the configuration file
     */
    @Override
    public void reload() {
        try {
            init(new ConfigurationFile("config/server.cfg"));
        } catch (IOException e) {
            Logman.logStackTrace("Could not find the server configuration while reloading!", e);
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
            config = new ConfigurationFile("config/server.cfg",true);
        }
        catch(IOException ioe) {
            Logman.logStackTrace("Failed to create default database configuration.", ioe);
            return;
        }

        config.setBoolean("reservelist",false);
        config.setString("protect-spam","default");
        config.setString("reservelist-message","Not on reserve list.");
        config.setBoolean("playerlist-enabled",true);
        config.setString("default-ban-message","You are banned from this server!");
        config.setString("data-source","flatfile");
        config.setBoolean("logging",false);
        config.setBoolean("playerlist-autoupdate",false);
        config.setBoolean("debug",false);
        config.setString("default-world-name","default");
        config.setBoolean("show-unknown-command",true);
        config.setBoolean("save-homes",true);
        config.setBoolean("death-message",true);
        config.setString("whitelist-message","Not on whitelist.");
        config.setString("motd","A Minecraft Server.");
        config.setInt("playerlist-ticks",500);
        config.setBoolean("playerlist-usecolors",true);
        config.setBoolean("whitelist",false);
        
        try {
            config.save();
        }
        catch(IOException ioe) {
            Logman.logStackTrace("Failed to create default server configuration.", ioe);
        }
    }
    
    /**
     * Get datasource type
     * @return
     */
    public Database.Type getDatasourceType() {
	        return dataSourceType;
    }
    
    /**
     * Get the default world name defined in the config
     * @return
     */
    public String getDefaultWorldName(){
        return cfg.getString("default-world-name", "default");
    }
    
    /**
     * Whether this server is in debug mode.
     * 
     * Use debug mode when developing plugins, CanaryAPI or CanaryMod.
     * @return
     */
    public boolean isDebugMode() {
        return cfg.getBoolean("debug-mode", false);
    }



    public boolean isDeathMessageEnabled() {
    	return cfg.getBoolean("death-message", true);
    }
    
    public String getDefaultBanMessage() {
    	return cfg.getString("default-ban-message", "You are banned from this server!");
    }
    
    public boolean isLogging() {
    	return cfg.getBoolean("logging",false);
    }

    /* to World */
    public int getNaturalSpawnRate() {
    	return cfg.getInt("natural-spawn-rate", 100);
    }

    public boolean getPlayerlistAutoUpdate() {
    	return cfg.getBoolean("playerlist-autoupdate",false); 
    }
    
    public boolean isPlayerListEnabled() {
    	return cfg.getBoolean("playerlist-enabled",true);
    }
    
    public int getPlayerlistTicks() {
    	return cfg.getInt("playerlist-ticks",500);
    }
    
    public boolean isPlayerlistColorsEnabled() {
    	return cfg.getBoolean("playerlist-usecolors", true);
    }
    
    //public boolean()
    //protect-spam=default
    
    public boolean isReservelistEnabled() {
    	return cfg.getBoolean("reservelist",false);
    }
    
    public String getReservelistMessage() {
    	return cfg.getString("reservelist-message","Not on reserve list.");
    }
    
    public boolean isSaveHomesEnabled() {
    	return cfg.getBoolean("save-homes",true);
    }
    
    public boolean getShowUnkownCommand() {
    	return cfg.getBoolean("show-unkown-command",true);
    }
    
    public String getWhitelistMessage() {
    	return cfg.getString("whitelist-message","Not on whitelist.");
    }
    
    public boolean isWhitelistEnabled() {
    	return cfg.getBoolean("whitelist",false);
    }
    
    public String getMotd() {
    	return cfg.getString("motd", "A Minecraft Server");
    }
}
