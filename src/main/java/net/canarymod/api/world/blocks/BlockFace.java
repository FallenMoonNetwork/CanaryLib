package net.canarymod.api.world.blocks;

/**
 * A BlockFace is a side of a block
 *
 * @author Chris (damagefilter)
 */
public enum BlockFace {
    BOTTOM((byte) 0), TOP((byte) 1), EAST((byte) 2), WEST((byte) 3), NORTH((byte) 4), SOUTH((byte) 5), UNKNOWN((byte) -1);

    private byte normal;

    BlockFace(byte direction) {
        normal = direction;
    }

    /**
     * Return this faces normal direction (The byte value to this face)
     *
     * @return the face byte value
     */
    public byte getByte() {
        return normal;
    }

    /**
     * Get a BlockFace from byte
     *
     * @param normal
     *         the facing byte value
     *
     * @return the {@link BlockFace}
     */
    public static BlockFace fromByte(byte normal) {
        switch (normal) {
            case 0:
                return BOTTOM;

            case 1:
                return TOP;

            case 2:
                return EAST;

            case 3:
                return WEST;

            case 4:
                return NORTH;

            case 5:
                return SOUTH;

            default:
                return UNKNOWN;
        }
    }
}
