package net.canarymod.api.nbt;

/**
 * An NBT tag that stores an integer.
 *
 * @author gregthegeek
 */
public interface IntTag extends BaseTag {

    /**
     * Returns the value associated with this tag.
     *
     * @return the integer value
     */
    public int getValue();

    /**
     * Sets the value associated with this tag.
     *
     * @param value
     *         the integer value
     */
    public void setValue(int value);

    /** {@inheritDoc} */
    @Override
    public IntTag copy();

}
