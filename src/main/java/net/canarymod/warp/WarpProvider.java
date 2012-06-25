package net.canarymod.warp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.canarymod.api.entity.Player;
import net.canarymod.backbone.Backbone;
import net.canarymod.backbone.BackboneWarps;
import net.canarymod.database.Database;

public class WarpProvider {
    private ArrayList<Warp> warps;
    private BackboneWarps backbone;

    public WarpProvider(Backbone bone, Database.Type type) {
        backbone = new BackboneWarps();
        warps = backbone.loadWarps();
    }

    /**
     * Add new warp
     * 
     * @param warp
     */
    public void addWarp(Warp warp) {
        backbone.addWarp(warp);
        warps.add(warp);
    }

    /**
     * Remove a warp
     * 
     * @param warp
     */
    public void removeWarp(Warp warp) {
        backbone.removeWarp(warp);
        warps.remove(warp);
    }

    /**
     * Set home for player, this updates a player home if there already is one
     * 
     * @param player
     */
    public void setHome(Player player) {
        Warp w = getHome(player);
        if (w != null) {
            warps.remove(w);
        }
        Warp newWarp = new Warp(player.getLocation(), player.getName(), player);
        warps.add(newWarp);
        backbone.updateWarp(newWarp);
    }

    /**
     * Returns warp that has the given name or null if not exists
     * 
     * @param name
     * @return
     */
    public Warp getWarp(String name) {
        for (Warp g : warps) {
            if (g.getName().equals(name)) {
                if (!g.isPlayerHome()) {
                    return g;
                }
            }
        }
        return null;
    }

    /**
     * Returns this players home
     * 
     * @param player
     * @return
     */
    public Warp getHome(Player player) {
        return getHome(player.getName());
    }

    /**
     * Return home for a player with this name
     * 
     * @param player
     * @return
     */
    public Warp getHome(String player) {
        for (Warp g : warps) {
            if (g.isPlayerHome()) {
                if (g.getOwner().equals(player)) {
                    return g;
                }
            }
        }
        return null;
    }
    
    /**
     * Return a non-modifiable list of all available warps
     * @return
     */
    public List<Warp> getAllWarps() {
        return Collections.unmodifiableList(warps);
    }
}
