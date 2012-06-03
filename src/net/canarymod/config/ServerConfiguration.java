package net.canarymod.config;

import java.io.IOException;

import net.canarymod.Logman;
import net.canarymod.database.Database;


public class ServerConfiguration implements ConfigurationContainer {
    private ConfigurationFile cfg;
    private Database.Type dataSourceType;

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
        return cfg.getString("default-world-name", "world");
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
    
    public int getSpawnProtectionSize() {
    	return cfg.getInt("spawn-protection-size",16);
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
