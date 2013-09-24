package net.canarymod.hook.system;

import net.canarymod.hook.CancelableHook;

/** 
 * Called when a client ping the server 
 *
 * @author greatman
 */
public class ServerListPingHook extends CancelableHook {

    private String motd;
    private int maxPlayers;
    private int currentPlayers;

    public ServerListPingHook(String motd, int currentPlayers, int maxPlayers) {
        this.motd = motd;
        this.maxPlayers = maxPlayers;
        this.currentPlayers = currentPlayers;
    }

    /**
     * Retrieve the MOTD that will be sent to the client
     *
     * @return The MOTD that will be sent to the client
     */
    public String getMotd() {
        return motd;
    }

    /**
     * Retrieve the maximum amount of players the server allows that will be sent to the client.
     *
     * @return The maximum amount of players
     */
    public int getMaxPlayers() {
        return maxPlayers;
    }

    /**
     * Retrieve the current amount of players connected to the server that will be sent to the client.
     *
     * @return The current amount of players
     */
    public int getCurrentPlayers() {
        return currentPlayers;
    }

    /**
     * Set the MOTD that will be sent to the client
     *
     * @param motd
     *         The new MOTD
     */
    public void setMotd(String motd) {
        this.motd = motd;
    }

    /**
     * Set the maximum amount of player the server allows that will be sent to the client. <b>Please note that this only fakes the value. It doesn't modify the real value.</b>
     *
     * @param maxPlayers
     *         The maximum amount of player the server allows
     */
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    /**
     * Set the current amount of players connected to the server that will be sent to the client
     *
     * @param currentPlayers
     *         the current amount of players connected to the server
     */
    public void setCurrentPlayers(int currentPlayers) {
        this.currentPlayers = currentPlayers;
    }
}
