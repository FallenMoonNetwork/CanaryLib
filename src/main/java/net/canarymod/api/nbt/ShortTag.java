package net.canarymod.api.nbt;

/**
 * An NBT tag that stores a short.
 *
 * @author gregthegeek
 */
public interface ShortTag extends BaseTag {

    /**
     * Returns the value associated with this tag.
     *
     * @return the short value
     */
    public short getValue();

    /**
     * Sets the value associated with this tag.
     *
     * @param value
     *         the short value
     */
    public void setValue(short value);

    /** {@inheritDoc} */
    @Override
    public ShortTag copy();

}
