package net.canarymod.api.world.blocks;

/**
 * Skull wrapper
 *
 * @author Jason (darkdiplomat)
 */
public interface Skull extends TileEntity {

    /**
     * Sets the Skull type, based on Entity Type id
     *
     * @return skull type
     */
    public int getSkullType();

    /**
     * Sets the skull type
     *
     * @param type
     *         the skull type
     */
    public void setSkullType(int type);

    /**
     * Gets the extra type of the Skull, typically a Player's name
     *
     * @return the extra type
     */
    public String getExtraType();

    /**
     * Sets the extra type of the Skull.
     *
     * @param extra
     *         the extra type
     */
    public void setExtraType(String extra);

    /**
     * Sets both the Skull and extra type data
     *
     * @param type
     *         the skull type
     * @param extra
     *         the extra type
     */
    public void setSkullAndExtraType(int type, String extra);

    /**
     * Gets the rotation of the skull
     *
     * @return rotation
     */
    public int getRotation();

    /**
     * Sets the rotatoin of the Skull
     *
     * @param rot
     *         the rotation
     */
    public void setRotation(int rot);

}
