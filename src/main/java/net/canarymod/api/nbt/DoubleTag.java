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
     * @return
     */
    public double getValue();

    /**
     * Sets the value associated with this tag.
     * 
     * @param value
     */
    public void setValue(double value);

    public DoubleTag copy();

}
