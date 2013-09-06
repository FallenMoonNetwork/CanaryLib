package net.canarymod.api.nbt;

/**
 * NBTTag Types enum
 *
 * @author Jason (darkdiplomat)
 */
public enum NBTTagType {
    UNKNOWN, //
    BYTE, //
    BYTE_ARRAY, //
    COMPOUND, //
    DOUBLE, //
    FLOAT, //
    INT, //
    INT_ARRAY, //
    LIST, //
    LONG, //
    SHORT, //
    STRING, //
    ;

    public static NBTTagType getTypeFromId(byte id) {
        switch (id) {
            case 1:
                return BYTE;
            case 2:
                return SHORT;
            case 3:
                return INT;
            case 4:
                return LONG;
            case 5:
                return FLOAT;
            case 6:
                return DOUBLE;
            case 7:
                return BYTE_ARRAY;
            case 8:
                return STRING;
            case 9:
                return LIST;
            case 10:
                return COMPOUND;
            case 11:
                return INT_ARRAY;
            default:
                return UNKNOWN;
        }
    }
}
