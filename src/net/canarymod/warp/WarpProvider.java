package net.canarymod.warp;

import java.util.ArrayList;

import net.canarymod.api.entity.IPlayer;
import net.canarymod.backbone.IBackbone;
import net.canarymod.backbone.IBackboneWarps;

public class WarpProvider {
    private ArrayList<Warp> warps;
    private IBackboneWarps backbone;
    
    public WarpProvider(IBackbone bone, IBackbone.Type type) {
        backbone = (IBackboneWarps) bone.getBackbone(IBackbone.System.WARPS, type);
        warps = backbone.loadWarps();
    }
    
    /**
     * Add new warp
     * @param warp
     */
    public void addWarp(Warp warp) {
        backbone.addWarp(warp);
        warps.add(warp);
    }
    
    /**
     * Remove a warp
     * @param warp
     */
    public void removeWarp(Warp warp) {
        backbone.removeWarp(warp);
        warps.remove(warp);
    }
    
    /**
     * Set home for player, this updates a player home if there already is one
     * @param player
     */
    public void setHome(IPlayer player) {
        Warp w = getHome(player);
        if(w != null) {
            warps.remove(w);
        }
        Warp newWarp = new Warp(player.getLocation(), player.getName(), player);
        warps.add(newWarp);
        backbone.updateWarp(newWarp);
    }
    
    /**
     * Returns warp that has the given name or
     * null if not exists
     * @param name
     * @return
     */
    public Warp getWarp(String name) {
        for(Warp g : warps) {
            if(g.getName().equals(name)) {
                if(!g.isPlayerHome()) {
                    return g;
                }
            }
        }
        return null;
    }
    
    /**
     * Returns this players home
     * @param player
     * @return
     */
    public Warp getHome(IPlayer player) {
        return getHome(player.getName());
    }
    
    /**
     * Return home for a player with this name
     * @param player
     * @return
     */
    public Warp getHome(String player) {
        for(Warp g : warps) {
            if(g.isPlayerHome()) {
                if(g.getOwner().equals(player)) {
                    return g;
                }
            }
        }
        return null;
    }
}
