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

    /**
     * Get whether the death message is enabled
     * @return true when enabled, false otherwise
     */
    public boolean isDeathMessageEnabled() {
    	return cfg.getBoolean("death-message", true);
    }
    
    /**
     * Get the default ban message
     * @return A string containing the default ban message
     */
    public String getDefaultBanMessage() {
    	return cfg.getString("default-ban-message", "You are banned from this server!");
    }
    
    /**
     * Get whether the server must log
     * @return true when enabled, false otherwise
     */
    public boolean isLogging() {
    	return cfg.getBoolean("logging",false);
    }

    /**
     * Get whether the player list is auto-updated
     * @return true if auto-updated, false otherwise. Default is false.
     */
    public boolean getPlayerlistAutoUpdate() {
    	return cfg.getBoolean("playerlist-autoupdate",false); 
    }
    
    /**
     * Get whether the player list is enabled.
     * @return true when enabled, false otherwise. Default is true.
     */
    public boolean isPlayerListEnabled() {
    	return cfg.getBoolean("playerlist-enabled",true);
    }
    
    /**
     * Get the number of ticks between playerlist updates
     * @return
     */
    public int getPlayerlistTicks() {
    	return cfg.getInt("playerlist-ticks",500);
    }
    
    /**
     * Get whether playerlist colors are enabled.
     * 
     * Note that using colors in the playerlist breaks usage of playername-completion in chat.
     * 
     * @return true when enabled, false otherwise. Default is true.
     */
    public boolean isPlayerlistColorsEnabled() {
    	return cfg.getBoolean("playerlist-usecolors", true);
    }
 
    /**
     * Get whether the reservelist is enabled
     * @return true when enabled, false otherwise. Default is false.
     */
    public boolean isReservelistEnabled() {
    	return cfg.getBoolean("reservelist",false);
    }
    
    /**
     * Get the message to be displayed when someone is not on the reserve list.
     * @return A string containing the message.
     */
    public String getReservelistMessage() {
    	return cfg.getString("reservelist-message","Not on reserve list.");
    }
    
    /**
     * Get whether home-saving is enabled.
     * @return true when enabled, false otherwise. Default is true.
     */
    public boolean isSaveHomesEnabled() {
    	return cfg.getBoolean("save-homes",true);
    }
    
    /**
     * Get whether 'Unkown Command' must be shown when an unkown command is used.
     * @return True when enabled, false otherwise. Default is true.
     */
    public boolean getShowUnkownCommand() {
    	return cfg.getBoolean("show-unkown-command",true);
    }
    
    /**
     * Get the message shown to players who are not whitelisted.
     * @return A string containing the message.
     */
    public String getWhitelistMessage() {
    	return cfg.getString("whitelist-message","Not on whitelist.");
    }
    
    /**
     * Get whether the whitelist is enabled.
     * @return True when enabled, false otherwise. Default is false.
     */
    public boolean isWhitelistEnabled() {
    	return cfg.getBoolean("whitelist",false);
    }
    
    /**
     * Get the message of the day, the message shown in the server list.
     * @return A string containing the message
     */
    public String getMotd() {
    	return cfg.getString("motd", "A Minecraft Server");
    }
}
