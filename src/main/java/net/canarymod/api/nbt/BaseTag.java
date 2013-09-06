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
     * @return name
     */
    public String getName();

    /**
     * Sets the name associated with this NBT tag.
     *
     * @param name
     *         the name of the tag to set
     */
    public void setName(String name);

    /**
     * Returns the ID number of the type of tag this is.
     *
     * @return the tag id
     */
    public byte getTypeId();

    /**
     * Copies the Tag
     *
     * @return a new instance of the tag being copied
     */
    public <T> BaseTag copy();

}
