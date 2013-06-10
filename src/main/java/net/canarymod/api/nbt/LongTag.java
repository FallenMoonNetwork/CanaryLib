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
     * @return
     */
    public long getValue();

    /**
     * Sets the value associated with this tag.
     * 
     * @param value
     */
    public void setValue(long value);

}
