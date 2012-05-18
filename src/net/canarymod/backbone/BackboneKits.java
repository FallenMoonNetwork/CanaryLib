package net.canarymod.backbone;

import java.util.ArrayList;

import net.canarymod.kit.Kit;

public interface BackboneKits extends Backbone {
    /**
     * Add a new Kit to the list of Kits.
     * 
     * @param Kit
     */
    public void addKit(Kit kit);

    /**
     * emove a Kit from the data source
     * 
     * @param Kit
     */
    public void removeKit(Kit kit);

    /**
     * Get a Kit for this player name
     * 
     * @param name
     * @return Returns a Kit object if that Kit was found, null otherwise
     */
    public Kit getKit(String name);

    /**
     * Update a Kit
     * 
     * @param Kit
     */
    public void updateKit(Kit kit);

    /**
     * Load and return all recorded bans
     * 
     * @return
     */
    public ArrayList<Kit> loadKits();
}
