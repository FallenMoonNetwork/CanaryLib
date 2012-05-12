package net.canarymod.kit;

import java.util.ArrayList;

import net.canarymod.backbone.IBackbone;
import net.canarymod.backbone.IBackboneKits;

public class KitProvider {
    private ArrayList<Kit> kits;
    private IBackboneKits backbone;
    
    public KitProvider(IBackbone bone, IBackbone.Type type) {
        backbone = (IBackboneKits) bone.getBackbone(IBackbone.System.KITS, type);
        kits = backbone.loadKits();
    }
    
    /**
     * Add new warp
     * @param warp
     */
    public void addWarp(Kit kit) {
        backbone.addKit(kit);
        kits.add(kit);
    }
    
    /**
     * Remove a warp
     * @param warp
     */
    public void removeWarp(Kit kit) {
        backbone.removeKit(kit);
        kits.remove(kit);
    }
    
    public void updateKit(Kit kit) {
        Kit k = getKit(kit.getName());
        if(k != null) {
            kits.remove(k);
        }
        kits.add(kit);
        backbone.updateKit(kit);
    }
    /**
     * Returns warp that has the given name or
     * null if not exists
     * @param name
     * @return
     */
    public Kit getKit(String name) {
        for(Kit g : kits) {
            if(g.getName().equals(name)) {
               return g;
            }
        }
        return null;
    }
}
