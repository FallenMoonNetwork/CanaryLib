package net.canarymod.api.nbt;

/**
 * An NBT tag that stores a float.
 *
 * @author gregthegeek
 */
public interface FloatTag extends BaseTag {

    /**
     * Returns the value associated with this tag.
     *
     * @return the float value
     */
    public float getValue();

    /**
     * Sets the value associated with this tag.
     *
     * @param value
     *         the float value
     */
    public void setValue(float value);

    /** {@inheritDoc} */
    @Override
    public FloatTag copy();

}
