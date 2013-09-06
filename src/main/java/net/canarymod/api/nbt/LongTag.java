package net.canarymod.api.nbt;

/**
 * An NBT tag that stores a long.
 *
 * @author gregthegeek
 */
public interface LongTag extends BaseTag {

    /**
     * Returns the value associated with this tag.
     *
     * @return the long value
     */
    public long getValue();

    /**
     * Sets the value associated with this tag.
     *
     * @param value
     *         the long value
     */
    public void setValue(long value);

    /** {@inheritDoc} */
    @Override
    public LongTag copy();

}
