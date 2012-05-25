package net.canarymod.backbone;

import net.canarymod.Canary;
import net.canarymod.config.Configuration;
import net.canarymod.database.Database;
import net.canarymod.database.DatabaseFlatfile;

public class Backbone {
    
    private System system;
    private Database.Type type;
    
    public Backbone(System system, Database.Type type) {
        this.system = system;
        this.type = type;
    }
    
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
    public System getSystem() {
        return system;
    }

    /**
     * Returns the type of this backbone (mysql, flatfile, sqlite etc etc)
     * 
     * @return
     */
    public Database.Type getType() {
        return type;
    }

    /**
     * Get a backbone for the given name and type
     * 
     * @param name
     * @param type
     * @return
     */
    public static Backbone getBackbone(System system, Database.Type type) {
        switch(type) {
        case FLATFILE:
            return getFlatfileSystem(system);
        case MYSQL:
            return getMysqlSystem(system);
        }
        return null;
    }
    
    private static Backbone getMysqlSystem(System system) {
        throw new UnsupportedOperationException();
    }
    
    private static Backbone getFlatfileSystem(System system) {
        Database.Type type = Database.Type.FLATFILE;
        switch(system) {
        case BANS:
            return new BackboneBans(new DatabaseFlatfile(), type);
        default:
            return null;
        }
    }

    /**
     * Get a backbone for the given name of the globally used type
     * 
     * @param name
     * @return
     */
    public static Backbone getBackbone(System system) {
        Canary.config();
        Database.Type type = Configuration.getServerConfig().getDatasourceType();
        
        return getBackbone(system, type);
    }
}
