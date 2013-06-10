package net.canarymod.api.entity;

/**
 * Painting warpper
 * 
 * @author Jason (darkdiplomat)
 */
public interface Painting extends HangingEntity {

    // This enum should mirror that of OEnumArt DO NOT REORGINIZE!
    public enum ArtType {
        Kebab, //
        Aztec, //
        Alban, //
        Aztec2, //
        Bomb, //
        Plant, //
        Wasteland, //
        Pool, //
        Courbet, //
        Sea, //
        Sunset, //
        Creebet, //
        Wanderer, //
        Graham, //
        Match, //
        Bust, //
        Stage, //
        Void, //
        SkullAndRoses, //
        Fighters, //
        Pointer, //
        Pigscene, //
        BurningSkull, //
        Skeleton, //
        DonkeyKong;
    }

    /**
     * Gets the type of Art this painting is
     * 
     * @return type
     */
    public ArtType getArtType();

    /**
     * Sets the type of art this painting is
     * 
     * @param type
     */
    public void setArtType(ArtType type);

    /**
     * Gets the x-wise size of this painting
     * 
     * @return sizex
     */
    public int getSizeX();

    /**
     * Gets the y-wise size of this painting
     * 
     * @return sizey
     */
    public int getSizeY();

    /**
     * Gets the x-wise offset of this painting
     * 
     * @return offsetx
     */
    public int getOffsetX();

    /**
     * Gets the y-wise offset of this painting
     * 
     * @return offsety
     */
    public int getOffsetY();

}
