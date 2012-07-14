package net.canarymod.config;

import java.io.File;
import java.io.IOException;

import net.canarymod.Logman;

/**
 * 
 * @author Jos Kuijpers
 *
 */
public class NetworkConfiguration implements ConfigurationContainer {
    private ConfigurationFile cfg;
    
    public NetworkConfiguration(String path) {
        ConfigurationFile file = new ConfigurationFile(path); 
        if(!file.exists()) {
            Logman.logInfo("Could not find the network configuration at " + path + ", creating default.");
            NetworkConfiguration.createDefault();
        }
        init(file);
    }
    
    public NetworkConfiguration(ConfigurationFile cfg) {
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
        init(new ConfigurationFile("config/net.cfg"));
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
        config = new ConfigurationFile("config"+File.separatorChar+"net.cfg");
        if(!config.exists()) {
            if(!config.create()) {
                Logman.logSevere("Failed to create default network configuration.");                   
            }
        }
        
        config.setInt("view-distance",10);
        config.setString("server-ip","0.0.0.0");
        config.setBoolean("online-mode",true);
        config.setInt("max-players",20);
        config.setBoolean("enable-rcon",false);
        config.setInt("server-port",25565);
        config.setBoolean("enable-query",false);
        
        try {
            config.save();
        }
        catch(IOException ioe) {
            Logman.logStackTrace("Failed to create default network configuration.", ioe);
        }
    }
    
    /**
     * Get the port number used to receive player-connections
     * @return
     */
    public int getPort() {
        return cfg.getInt("server-port", 25565);
    }
    
    /**
     * Get whether server query-ing is enabled
     * @return
     */
    public boolean isQueryEnabled() {
    	return cfg.getBoolean("enable-query", false);
    }
    
    /**
     * Whether Remote CONtrol is enabled.
     * @return
     */
    public boolean isRconEnabled() {
    	return cfg.getBoolean("enable-rcon", false);
    }
    
    /**
     * Whether the server is in online mode.
     * 
     * When a server is in online mode, all players are verificated
     * against the servers of Mojang. This will ensure all players have paid.
     * When allowing unpaid users, the server is vulnerable to griefing and attacks.
     * 
     * @return
     */
    public boolean isOnlineMode() {
    	return cfg.getBoolean("online-mode", true);
    }
    
    /**
     * Get the IP address which to server binds to
     * @return
     */
    public String getBindIp() {
    	return cfg.getString("server-ip", "");
    }
    
    /**
     * Get maximum amount of player allowed online
     * @return
     */
    public int getMaxPlayers() {
    	return cfg.getInt("max-players", 20);
    }
    
    /**
     * Get the port used for remote control
     * @return
     */
    public int getRconPort() {
    	return cfg.getInt("rcon.port",0);
    }
    
    /**
     * Get the password used for remote control
     * @return
     */
    public String getRconPassword() {
    	return cfg.getString("rcon.password","");
    }
    
    /**
     * Get the port used for query
     * @return
     */
    public int getQueryPort() {
    	return cfg.getInt("query.port",0);
    }
    
    /**
     * Get the view distance of clients: maximum radius of loaded chunks around a player
     * @return view distance
     */
    public int getViewDistance() {
    	return cfg.getInt("view-distance", 10);
    }
}
