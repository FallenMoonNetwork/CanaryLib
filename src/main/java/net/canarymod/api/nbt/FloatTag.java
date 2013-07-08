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
     * @return
     */
    public float getValue();

    /**
     * Sets the value associated with this tag.
     * 
     * @param value
     */
    public void setValue(float value);

    public FloatTag copy();

}
