package net.canarymod.api.world;


import java.util.HashMap;

import net.canarymod.Canary;


/**
 * Dynamic worldType list
 * @author Chris
 *
 */
public class DimensionType {
    
    // *** STATIC STUFF ***
    private static HashMap<String, DimensionType> typeList = new HashMap<String, DimensionType>(5); // 3 std dims and 2 extras
    
    public static void addType(String name, int id) {
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
     * 
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
    
    public static boolean typeExists(String name) {
        return typeList.containsKey(name);
    }
    // *** END STATIC STUFF ***
    
    private int id;
    private String name;
    // Make sure no-one can just instantiate a new world type
    private DimensionType(String name, int id) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean equals(Object ob) {
        if (!(ob instanceof DimensionType)) {
            return false;
        }
        DimensionType o = (DimensionType) ob;

        return o.name.equals(name) && o.id == id;
    }
    
    public int hashCode() {
        return name.hashCode() + id;
    }
    
    public String toString() {
        return name + ":" + id;
    }
}
