package net.canarymod.backbone;

import java.util.ArrayList;

import net.canarymod.bansystem.Ban;

/**
 * Backbone to the ban System. This contains NO logic, it is only the data
 * source access!
 * 
 * @author Chris
 * 
 */
public interface BackboneBans extends Backbone {

    /**
     * Add a new Ban to the list of bans.
     * 
     * @param ban
     */
    public void addBan(Ban ban);

    /**
     * Lift a ban that was issued for the player with the given name or IP
     * 
     * @param name
     */
    public void liftBan(String subject);

    /**
     * Lift an IP ban.
     * 
     * @param subject
     */
    public void liftIpBan(String subject);

    /**
     * Get a ban for this player name
     * 
     * @param name
     * @return Returns a ban object if that ban was found, null otherwise
     */
    public Ban getBan(String name);

    /**
     * Update a ban.
     * 
     * @param ban
     */
    public void updateBan(Ban ban);

    /**
     * Load and return all recorded bans
     * 
     * @return
     */
    public ArrayList<Ban> loadBans();
}
