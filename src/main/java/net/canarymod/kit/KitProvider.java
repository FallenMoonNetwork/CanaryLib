package net.canarymod.kit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.canarymod.backbone.BackboneKits;

/**
 * Add, remove and get kits
 *
 * @author Chris (damagefilter)
 */
public class KitProvider {
    private ArrayList<Kit> kits;
    private BackboneKits backbone;

    public KitProvider() {
        backbone = new BackboneKits();
        kits = backbone.loadKits();
    }

    /**
     * Add new kit
     *
     * @param kit
     */
    public void addKit(Kit kit) {
        Kit test = getKit(kit.getName());

        if (test != null) {
            kits.remove(test);
        }
        backbone.addKit(kit);
        kits.add(kit);
    }

    /**
     * Remove a kit
     *
     * @param kit
     */
    public void removeKit(Kit kit) {
        backbone.removeKit(kit);
        kits.remove(kit);
    }

    public void updateKit(Kit kit) {
        Kit k = getKit(kit.getName());

        if (k != null) {
            kits.remove(k);
        }
        kits.add(kit);
        backbone.updateKit(kit);
    }

    /**
     * Returns warp that has the given name or null if not exists
     *
     * @param name
     *
     * @return
     */
    public Kit getKit(String name) {
        for (Kit g : kits) {
            if (g.getName().equals(name)) {
                return g;
            }
        }
        return null;
    }

    /**
     * Return all loaded kits as non-modifiable list
     *
     * @return
     */
    public List<Kit> getAllKits() {
        return Collections.unmodifiableList(kits);
    }

    public void reload() {
        kits.clear();
        kits = backbone.loadKits();
    }
}
