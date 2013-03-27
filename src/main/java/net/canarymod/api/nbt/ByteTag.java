package net.canarymod.api.nbt;

/**
 * An NBT tag that stores a byte.
 * 
 * @author gregthegeek
 *
 */
public interface ByteTag extends BaseTag {
    
    /**
     * Returns the value associated with this tag.
     * @return
     */
    public byte getValue();
    
    /**
     * Sets the value associated with this tag.
     * @param value
     */
    public void setValue(byte value);

}
