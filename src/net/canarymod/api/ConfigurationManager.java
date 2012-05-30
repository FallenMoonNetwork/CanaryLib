package net.canarymod.api;

import java.util.ArrayList;

import net.canarymod.api.entity.Player;

/**
 * Interface for the server configuration manager.
 * It's important to note that this has nothing to do with the canarymod configurations!
 * @author Chris Ksoll
 *
 */
public interface ConfigurationManager {
    
    /**
     * Sends a packet to all people in the given world (retrieved by its name)
     * @param world
     * @param packet
     */
    public void sendPacketToAllInWorld(String world, Packet packet);
    
    /**
     * Get the numbers of online players
     * @return
     */
    public int getNumPlayersOnline();
    
    /**
     * Get a player by this name.
     * Note: This method does not match player names.
     * You need to provide the exact player name
     * @param name
     * @return
     */
    public Player getPlayerByName(String name);
    
    /**
     * Get a list of all players that are currently online
     * @return
     */
    public ArrayList<Player> getAllPlayers();
    
    /**
     * Get the number of max. allowed players on this server.
     * @return
     */
    public int getMaxPlayers();
    
    /**
     * Mark a block to be updated with the next tick
     * @param x
     * @param y
     * @param z
     * @param dimension
     * @param world
     */
    public void markBlockNeedsUpdate(int x, int y, int z, int dimension, String world);
    
}
