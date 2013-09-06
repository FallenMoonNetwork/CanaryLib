package net.canarymod.user;

import java.util.ArrayList;

import net.canarymod.backbone.BackboneReservelist;

/**
 * Reserve List Provider
 *
 * @author Jason (darkdiplomat)
 */
public class ReservelistProvider {
    private BackboneReservelist backbonereservelist;
    private ArrayList<String> reservelist;

    public ReservelistProvider() {
        backbonereservelist = new BackboneReservelist();
        reservelist = backbonereservelist.loadReservelist();
    }

    /** Reload the reservelist from database */
    public void reload() {
        reservelist = backbonereservelist.loadReservelist();
    }

    /**
     * Check if a given player is reservelist.
     *
     * @param player
     *         the player's name to check
     *
     * @return {@code true}
     */
    public boolean isSlotReserved(String player) {
        return reservelist.contains(player);
    }

    /**
     * Adds a new whitelist entry
     *
     * @param name
     */
    public void addPlayer(String name) {
        if (!reservelist.contains(name)) {
            reservelist.add(name);
            backbonereservelist.addSlotReservation(name);
        }
    }

    /**
     * Removes the given player from the reservelist
     *
     * @param name
     */
    public void removePlayer(String name) {
        if (reservelist.contains(name)) {
            reservelist.remove(name);
            backbonereservelist.removeReservelistEntry(name);
        }
    }

    /**
     * gets the current size of the reservelist
     *
     * @return
     */
    public int getSize() {
        return reservelist.size();
    }
}
