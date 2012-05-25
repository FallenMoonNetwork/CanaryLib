package net.canarymod.config;

import java.io.IOException;

import net.canarymod.Logman;

public class NetworkConfiguration implements ConfigurationContainer {
    private ConfigurationFile cfg;
    
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
        try {
            init(new ConfigurationFile("config/net.cfg"));
        } catch (IOException e) {
            Logman.logStackTrace("Could not find the server configuration while reloading! (Wtf man?!)", e);
        }
    }
    
    /**
     * Creates the default configuration
     */
    public static void createDefault() {
    	
    }
    
    /**
     * Get the port number used to receive player-connections
     * @return
     */
    public int getPort() {
        return cfg.getInt("port", 22025);
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
    	return cfg.getString("server-ip", "0.0.0.0");
    }
    
    /**
     * Get maximum amount of player allowed online
     * @return
     */
    public int getMaxPlayers() {
    	return cfg.getInt("max-players", 20);
    }
}
