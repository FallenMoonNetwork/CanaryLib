package net.canarymod.api.world.blocks;

/**
 * Block Material
 * <p/>
 * Contains technical information about a {@link Block}
 *
 * @author Jason (darkdiplomat)
 */
public interface BlockMaterial {

    /**
     * Checks if the material is liquid.
     *
     * @return {@code true} if liquid; {@code false} otherwise
     */
    public boolean isLiquid();

    /**
     * Checks if the material is solid.
     *
     * @return {@code true} if solid; {@code false} otherwise
     */
    public boolean isSolid();

    /**
     * Checks if the material is placed on dirt/grass, will prevent grass growth and kill current grass
     *
     * @return {@code true} if prevents; {@code false} otherwise
     */
    public boolean canPreventGrassGrowth();

    /**
     * Checks if the material is burnable
     *
     * @return {@code true} if burns; {@code false} otherwise
     */
    public boolean canBurn();

    /**
     * Checks if the material can be replaced with other blocks placed (ie: tall grass, snow, water, lava)
     *
     * @return {@code true} if replaceable; {@code false} otherwise
     */
    public boolean isReplaceable();

    /**
     * Indicate if the material is opaque
     *
     * @return {@code true} if opaque; {@code false} otherwise
     */
    public boolean isOpaque();

    /**
     * Determines if the material can be harvested without a tool (or with the wrong tool)
     *
     * @return {@code true} if no tool required; {@code false} otherwise
     */
    public boolean noToolRequired();

    /**
     * Returns the mobility information of the material, 0 = free, 1 = can't push but can move over, 2 = total
     * immobility and stop pistons.
     *
     * @return 0 = free, 1 = can't push but can move over, 2 = no movement
     */
    public int getMobility();

    /**
     * Checks to see if the material can be harvested it in any case.
     *
     * @return {@code true} if always; {@code flase} if not
     */
    public boolean isAlwaysHarvested();

    /**
     * Indicates if the material is translucent
     *
     * @return {@code true} if translucent; {@code false} otherwise
     */
    public boolean isTranslucent();

}
