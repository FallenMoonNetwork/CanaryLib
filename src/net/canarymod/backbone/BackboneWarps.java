package net.canarymod.backbone;

import java.util.ArrayList;

import net.canarymod.warp.Warp;

/**
 * Backbone to the warps system This contains NO logic, it is only the data
 * source access!
 * 
 * @author Chris
 * 
 */
public interface BackboneWarps extends Backbone {

    /**
     * Add a new Warp to the list of Warps.
     * 
     * @param Warp
     */
    public void addWarp(Warp warp);

    /**
     * emove a Warp from the data source
     * 
     * @param Warp
     */
    public void removeWarp(Warp warp);

    /**
     * Get a Warp for this player name
     * 
     * @param name
     * @return Returns a Warp object if that Warp was found, null otherwise
     */
    public Warp getWarp(String name);

    /**
     * Update a Warp
     * 
     * @param Warp
     */
    public void updateWarp(Warp warp);

    /**
     * Load and return all recorded bans
     * 
     * @return
     */
    public ArrayList<Warp> loadWarps();
}
