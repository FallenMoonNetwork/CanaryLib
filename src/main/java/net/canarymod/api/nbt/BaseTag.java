package net.canarymod.api.nbt;

/**
 * Base for all NBT storage tags.
 * 
 * @author gregthegeek
 */
public interface BaseTag {

    /**
     * Returns the name associated with this NBT tag.
     * 
     * @return
     */
    public String getName();

    /**
     * Sets the name associated with this NBT tag.
     * 
     * @param name
     */
    public void setName(String name);

    /**
     * Returns the ID number of the type of tag this is.
     * 
     * @return
     */
    public byte getTypeId();

}
