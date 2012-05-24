package net.canarymod.backbone;

import net.canarymod.database.Database;

public interface Backbone {
    /**
     * The backbone system.
     * 
     * @author Chris
     * 
     */
    public enum System {
        BANS, GROUPS, WARPS, KITS, PERMISSIONS;
    }

    /**
     * Get the System of this backbone
     * 
     * @return
     */
    public System getSystem();

    /**
     * Returns the type of this backbone (mysql, flatfile, sqlite etc etc)
     * 
     * @return
     */
    public Database.Type getType();

    /**
     * Get a backbone for the given name and type
     * 
     * @param name
     * @param type
     * @return
     */
    public Backbone getBackbone(System system, Database.Type type);

    /**
     * Get a backbone for the given name of the globally used type
     * 
     * @param name
     * @return
     */
    public Backbone getBackbone(System system);
}
