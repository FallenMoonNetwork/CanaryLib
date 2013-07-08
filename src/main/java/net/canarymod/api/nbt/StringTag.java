package net.canarymod.api.nbt;

/**
 * An NBT tag that stores a string.
 * 
 * @author gregthegeek
 */
public interface StringTag extends BaseTag {

    /**
     * Returns the value associated with this tag.
     * 
     * @return
     */
    public String getValue();

    /**
     * Sets the value associated with this tag.
     * 
     * @param value
     */
    public void setValue(String value);

    public StringTag copy();

}
