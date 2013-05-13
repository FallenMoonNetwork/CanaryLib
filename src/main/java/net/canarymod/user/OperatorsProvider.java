package net.canarymod.user;

import java.util.ArrayList;
import net.canarymod.backbone.BackboneOperators;

public class OperatorsProvider {
    private BackboneOperators backboneOps;
    private ArrayList<String> ops;

    public OperatorsProvider() {
        backboneOps = new BackboneOperators();
        ops = backboneOps.loadOps();
    }

    /**
     * Reload the whitelist from database
     */
    public void reload() {
        ops = backboneOps.loadOps();
    }

    /**
     * Check if a given player is whitelisted.
     * @param player
     * @return
     */
    public boolean isOpped(String player) {
        return ops.contains(player);
    }

    /**
     * Adds a new whitelist entry
     * @param name
     */
    public void addPlayer(String name) {
        if (!ops.contains(name)) {
            ops.add(name);
            backboneOps.addOpEntry(name);
        }
    }

    /**
     * Removes the given player from the whitelist
     * @param name
     */
    public void removePlayer(String name) {
        if (ops.contains(name)) {
            ops.remove(name);
            backboneOps.removeOpEntry(name);
        }
    }

    /**
     * gets the current size of the whitelist
     * @return
     */
    public int getSize() {
        return ops.size();
    }
}
