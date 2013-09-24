package net.canarymod.api.world;

import java.util.HashMap;

/**
 * Static class of WorldTypes
 *
 * @author Chris (damagefilter)
 */
public class WorldType {
    public static WorldType DEFAULT = new WorldType("DEFAULT");
    public static WorldType SUPERFLAT = new WorldType("FLAT");
    public static WorldType DEFAULT_1_1 = new WorldType("DEFAULT_1_1");
    public static WorldType LARGEBIOMES = new WorldType("LARGEBIOMES");

    private String string;
    private static HashMap<String, WorldType> types;

    private WorldType(String string) {
        if (types == null) {
            types = new HashMap<String, WorldType>();
        }
        this.string = string;
        types.put(string, this);
    }

    /**
     * Register a new WorldType.
     *
     * @param name
     *
     * @return
     */
    public static boolean registerWorldType(String name) {
        if (types.containsKey(name)) {
            return false;
        }
        types.put(name, new WorldType(name));
        return true;
    }

    @Override
    public String toString() {
        return string;
    }

    /**
     * get a worldType from string.
     * This may return null if the requested WorldType does not exist!
     *
     * @param string
     *
     * @return
     */
    public static WorldType fromString(String string) {
        for (String n : types.keySet()) {
            if (n.equals(string)) {
                return types.get(n);
            }
        }
        return null;
    }
}
