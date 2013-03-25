package net.canarymod.api;


/**
 * DyeColor
 * 
 * @author Jason (darkdiplomat)
 */
public enum DyeColor {
    
    WHITE, //
    ORANGE, //
    MAGENTA, //
    LIGHT_BLUE, //
    YELLOW, //
    LIME, //
    PINK, //
    GRAY, //
    LIGHT_GRAY, //
    CYAN, //
    PURPLE, //
    BLUE, //
    BROWN, //
    GREEN, //
    RED, //
    BLACK;
    
    /**
     * Gets the Color Code as used for Wool and Wolf Collars
     * 
     * @return color code
     */
    public int getColorCode() {
        return ordinal();
    }
    
    /**
     * Gets the Color Code as used for Dyes
     * 
     * @return dye color code
     */
    public int getDyeColorCode() {
        return ~ordinal() & 15;
    }

}
