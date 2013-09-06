package net.canarymod.api.nbt;

/**
 * An NBT tag that stores a double.
 *
 * @author gregthegeek
 */
public interface DoubleTag extends BaseTag {

    /**
     * Returns the value associated with this tag.
     *
     * @return the double value
     */
    public double getValue();

    /**
     * Sets the value associated with this tag.
     *
     * @param value
     *         the double value
     */
    public void setValue(double value);

    /** {@inheritDoc} */
    @Override
    public DoubleTag copy();

}
