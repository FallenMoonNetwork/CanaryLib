package net.canarymod.api;

import net.canarymod.api.world.IWorldManager;

/**
 * CanaryMod Server.<br>
 * This handles communication to the server and provides a couple of useful information
 * @author Chris
 * @author Jos Kuijpers
 *
 */
public interface IServer extends Runnable{
    
    /**
     * Get an Integer Value from the server.properties 
     * @param key
     * @param defaultValue
     * @return
     */
    public int getInt(String key, int defaultValue);
    
    /**
     * Get a String value from the server.properties
     * @param key
     * @param defaultValue
     * @return
     */
    public String getString(String key, String defaultValue);
    
    /**
     * Get the current host name for this server
     * @return
     */
    public String getHostname();
    
    /**
     * Get the amount of players that are online
     * @return
     */
    public int getNumPlayersOnline();
    
    /**
     * Get the number of max. allowed players
     * @return
     */
    public int getMaxPlayers();
    
    /**
     * Get a list of names from players that are currently online
     * @return
     */
    public String[] getPlayerNameList();
    
    /**
     * Get the default world name
     * @return
     */
    public String getDefaultWorldName();
    
    /**
     * Get the world container
     * @return
     */
    public IWorldManager getWorldManager();
    
    /**
     * Handles a command issued by RCON
     * @param command
     * @return
     */
    public String handleRemoteCommand(String command); //TODO: Rly?
}
