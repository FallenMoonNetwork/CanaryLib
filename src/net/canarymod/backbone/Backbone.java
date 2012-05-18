package net.canarymod.backbone;

public interface Backbone {
    /**
     * The backbones datasource type
     * 
     * @author Chris
     * 
     */
    public enum Type {
        MYSQL,
        //SQLITE,
        //POSTGRESQL,
        FLATFILE;
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
    public System getSystem();

    /**
     * Returns the type of this backbone (mysql, flatfile, sqlite etc etc)
     * 
     * @return
     */
    public Type getType();

    /**
     * Get a backbone for the given name and type
     * 
     * @param name
     * @param type
     * @return
     */
    public Backbone getBackbone(System system, Type type);

    /**
     * Get a backbone for the given name of the globally used type
     * 
     * @param name
     * @return
     */
    public Backbone getBackbone(System system);
}
