package net.canarymod.api.world;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.canarymod.Canary;

/**
 * Dynamic worldType list
 *
 * @author Chris
 */
public class DimensionType {

    // *** STATIC STUFF ***
    private static HashMap<String, DimensionType> typeList = new HashMap<String, DimensionType>(5); // 3 std dims and 2 extras

    public static void registerType(String name, int id) {
        if (typeList.containsKey(name)) {
            Canary.logWarning("Tried to add existing world type, aborting! WorldType: " + name);
            return;
        }
        if (validateId(id)) {
            typeList.put(name, new DimensionType(name, id));
        } else {
            Canary.logWarning("WorldType ID is not unique! Id: " + id + ", Type: " + name + " - Creating unique ID from hashCode!");
            typeList.put(name, new DimensionType(name, name.hashCode()));
        }
    }

    public static void registerType(String name, int id, Class<ChunkProviderCustom> cpc) {
        if (typeList.containsKey(name)) {
            Canary.logWarning("Tried to add existing world type, aborting! WorldType: " + name);
            return;
        }
        if (validateId(id)) {
            typeList.put(name, new DimensionType(name, id, cpc));
        } else {
            Canary.logWarning("WorldType ID is not unique! Id: " + id + ", Type: " + name + " - Creating unique ID from hashCode!");
            typeList.put(name, new DimensionType(name, name.hashCode(), cpc));
        }
    }

    public static DimensionType fromName(String name) {
        return typeList.get(name);
    }

    public static DimensionType fromId(int id) {
        for (String name : typeList.keySet()) {
            if (typeList.get(name).getId() == id) {
                return typeList.get(name);
            }
        }
        return null;
    }

    /**
     * @param id
     * @return True if ID is unique, false otherwise
     */
    private static boolean validateId(int id) {
        for (String n : typeList.keySet()) {
            if (typeList.get(n).getId() == id) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if a type with the given name exists.
     * This is case sensitive
     * @param name
     * @return
     */
    public static boolean typeExists(String name) {
        return typeList.containsKey(name);
    }

    // *** END STATIC STUFF ***

    private int id;
    private String name;
    private Class<ChunkProviderCustom> cpc = null;

    // Make sure no-one can just instantiate a new world type
    private DimensionType(String name, int id) {
        this.id = id;
        this.name = name;
    }

    private DimensionType(String name, int id, Class<ChunkProviderCustom> cpc) {
        this.id = id;
        this.name = name;
        this.cpc = cpc;
    }

    /**
     * Get the ID of this dimension type. With this ID the DimensionType can be identified.
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Get the name of this dimension type
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if this dimension type has a custom chunk provider attached
     * @return
     */
    public boolean hasChunkProvider() {
        return cpc != null;
    }

    /**
     * Get the ChunkProviderCustom that is attached to this Dimensiontype.
     * Note that this returns null if the DimensionType has no provider attached
     * @return
     */
    public ChunkProviderCustom getChunkProvider() {
        try {
            return cpc.newInstance();
        }
        catch (InstantiationException ex) {
            Canary.logSevere(ex.getMessage());
        }
        catch (IllegalAccessException ex) {
            Canary.logSevere(ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean equals(Object ob) {
        if (!(ob instanceof DimensionType)) {
            return false;
        }
        DimensionType o = (DimensionType) ob;

        return o.name.equals(name) && o.id == id;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + id;
    }

    @Override
    public String toString() {
        return name + ":" + id;
    }
}
