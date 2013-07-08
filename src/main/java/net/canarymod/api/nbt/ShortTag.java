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
     * @return
     */
    public short getValue();

    /**
     * Sets the value associated with this tag.
     * 
     * @param value
     */
    public void setValue(short value);

    public ShortTag copy();

}
