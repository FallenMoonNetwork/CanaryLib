package net.canarymod.api.nbt;

/**
 * An NBT tag that stores an array of integers.
 * 
 * @author gregthegeek
 */
public interface IntArrayTag extends BaseTag {

    /**
     * Returns the value associated with this tag.
     * 
     * @return
     */
    public int[] getValue();

    /**
     * Sets the value associated with this tag.
     * 
     * @param value
     */
    public void setValue(int[] value);

}
