package net.canarymod.warp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.position.Location;
import net.canarymod.backbone.BackboneWarps;

/**
 * Access to the backbone for the whitelist
 *
 * @author Chris (damagefilter)
 */
public class WarpProvider {
    private ArrayList<Warp> warps;
    private BackboneWarps backbone;

    public WarpProvider() {
        backbone = new BackboneWarps();
        warps = backbone.loadWarps();
    }

    /**
     * Add new warp
     *
     * @param warp
     */
    public void addWarp(Warp warp) {
        Warp test = getWarp(warp.getName());

        if (test != null) {
            warps.remove(test);
        }
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
    public void setHome(Player player, Location location) {
        Warp w = getHome(player);

        if (w != null) {
            w.setLocation(location);
            backbone.updateWarp(w);
        }
        else {
            Warp newWarp = new Warp(location, "HOME_" + player.getName().toUpperCase(), player.getName(), true);

            warps.add(newWarp);
            backbone.addWarp(newWarp);
        }
    }

    /**
     * Returns warp that has the given name or null if not exists
     *
     * @param name
     *
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
     *
     * @return
     */
    public Warp getHome(Player player) {
        return getHome(player.getName());
    }

    /**
     * Return home for a player with this name
     *
     * @param player
     *
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
     *
     * @return
     */
    public List<Warp> getAllWarps() {
        return Collections.unmodifiableList(warps);
    }

    public boolean warpExists(String name) {
        for (Warp w : warps) {
            if (w.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void reload() {
        warps.clear();
        warps = backbone.loadWarps();
    }
}
