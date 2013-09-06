package net.canarymod.api.nbt;

/**
 * An NBT tag that stores a byte.
 *
 * @author gregthegeek
 */
public interface ByteTag extends BaseTag {

    /**
     * Returns the value associated with this tag.
     *
     * @return the byte value
     */
    public byte getValue();

    /**
     * Sets the value associated with this tag.
     *
     * @param value
     *         the byte value
     */
    public void setValue(byte value);

    /** {@inheritDoc} */
    @Override
    public ByteTag copy();

}
