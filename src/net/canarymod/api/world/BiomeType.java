package net.canarymod.api.world;

import java.util.HashMap;
import java.util.Map;

/**
 * enumeration of biome types
 * 
 * @author Jason Jones
 */
public enum BiomeType {
    OCEAN(0),
    PLAINS(1),
    DESERT(2),
    HILLS_EXTREME(3),
    FOREST(4),
    TAIGA(5),
    SWAMPLAND(6),
    RIVER(7),
    HELL(8),
    SKY(9),
    OCEAN_FROZEN(10),
    RIVER_FROZEN(11),
    PLAINS_ICE(12),
    MOUNTAINS_ICE(13),
    MUSHROOM_ISLAND(14),
    MUSHROOM_ISLAND_SHORE(15),
    BEACH(16),
    HILLS_DESERT(17),
    HILLS_FOREST(18),
    HILLS_TAIGA(19),
    HILLS_EXTREME_EDGE(20),
    JUNGLE(21),
    HILLS_JUNGLE(22);
    
    private byte id;
    private static Map<Byte, BiomeType> map;

    private BiomeType(int id) {
        this.id = (byte) id;
        add(this.id, this);
    }

    private static void add(byte type, BiomeType name) {
        if (map == null) {
            map = new HashMap<Byte, BiomeType>();
        }
        map.put(type, name);
    }

    public byte getId() {
        return id;
    }

    public static BiomeType fromId(final byte id) {
        return map.get(id);
    }
}
