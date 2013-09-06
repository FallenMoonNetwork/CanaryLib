package net.canarymod.api.nbt;

import java.util.Collection;

/**
 * An NBT tag that stores a collection of NBT tags associated with strings.
 *
 * @author gregthegeek
 */
public interface CompoundTag extends BaseTag {

    /**
     * Gets the values inside the CompoundTag
     *
     * @return {@link Collection} of values
     */
    public Collection<BaseTag> values();

    /**
     * Puts a new Tag inside the CompoundTag
     *
     * @param key
     *         the name of the tag
     * @param value
     *         the {@link BaseTag} value
     */
    public void put(String key, BaseTag value);

    /**
     * Puts a byte value inside the CompoundTag
     *
     * @param key
     *         the name of the tag
     * @param value
     *         the byte value
     */
    public void put(String key, byte value);

    /**
     * Puts a short value inside the CompoundTag
     *
     * @param key
     *         the name of the tag
     * @param value
     *         the short value
     */
    public void put(String key, short value);

    /**
     * Puts an integer value inside the CompoundTag
     *
     * @param key
     *         the name of the tag
     * @param value
     *         the integer value
     */
    public void put(String key, int value);

    /**
     * Puts a long value inside the CompoundTag
     *
     * @param key
     *         the name of the tag
     * @param value
     *         the long value
     */
    public void put(String key, long value);

    /**
     * Puts a float value inside the CompoundTag
     *
     * @param key
     *         the name of the tag
     * @param value
     *         the float value
     */
    public void put(String key, float value);

    /**
     * Puts a double value inside the CompoundTag
     *
     * @param key
     *         the name of the tag
     * @param value
     *         the double value
     */
    public void put(String key, double value);

    /**
     * Puts a String value inside the CompoundTag
     *
     * @param key
     *         the name of the tag
     * @param value
     *         the String value
     */
    public void put(String key, String value);

    /**
     * Puts a byte array value inside the CompoundTag
     *
     * @param key
     *         the name of the tag
     * @param value
     *         the byte array value
     */
    public void put(String key, byte[] value);

    /**
     * Puts a int array value inside the CompoundTag
     *
     * @param key
     *         the name of the tag
     * @param value
     *         the int array value
     */
    public void put(String key, int[] value);

    /**
     * Puts a CompoundTag value inside the CompoundTag
     *
     * @param key
     *         the name of the tag
     * @param value
     *         the CompoundTag value
     */
    public void put(String key, CompoundTag value);

    /**
     * Puts a boolean value inside the CompoundTag
     *
     * @param key
     *         the name of the tag
     * @param value
     *         the boolean value
     */
    public void put(String key, boolean value);

    /**
     * Gets a {@link BaseTag} value from the CompoundTag
     *
     * @param key
     *         the name of the tag
     *
     * @return the {@link BaseTag}
     */
    public BaseTag get(String key);

    /**
     * Checks if the CompoundTag contains the given key
     *
     * @param key
     *         the key to check for
     *
     * @return {@code true} if contains the key; {@code false} if not
     */
    public boolean containsKey(String key);

    /**
     * Gets a byte value from the CompoundTag
     *
     * @param key
     *         the name of the tag
     *
     * @return the byte value
     */
    public byte getByte(String key);

    /**
     * Gets a short value from the CompoundTag
     *
     * @param key
     *         the name of the tag
     *
     * @return the short value
     */
    public short getShort(String key);

    /**
     * Gets a integer value from the CompoundTag
     *
     * @param key
     *         the name of the tag
     *
     * @return the integer value
     */
    public int getInt(String key);

    /**
     * Gets a long value from the CompoundTag
     *
     * @param key
     *         the name of the tag
     *
     * @return the long value
     */
    public long getLong(String key);

    /**
     * Gets a float value from the CompoundTag
     *
     * @param key
     *         the name of the tag
     *
     * @return the float value
     */
    public float getFloat(String key);

    /**
     * Gets a double value from the CompoundTag
     *
     * @param key
     *         the name of the tag
     *
     * @return the double value
     */
    public double getDouble(String key);

    /**
     * Gets a String value from the CompoundTag
     *
     * @param key
     *         the name of the tag
     *
     * @return the String value
     */
    public String getString(String key);

    /**
     * Gets a byte array value from the CompoundTag
     *
     * @param key
     *         the name of the tag
     *
     * @return the byte array value
     */
    public byte[] getByteArray(String key);

    /**
     * Gets a int array value from the CompoundTag
     *
     * @param key
     *         the name of the tag
     *
     * @return the int array value
     */
    public int[] getIntArray(String key);

    /**
     * Gets a CompoundTag value from the CompoundTag
     *
     * @param key
     *         the name of the tag
     *
     * @return the CompoundTag value
     */
    public CompoundTag getCompoundTag(String key);

    /**
     * Gets a {@link ListTag} value from the CompoundTag
     *
     * @param key
     *         the name of the tag
     *
     * @return the {@link ListTag} value
     */
    public <T extends BaseTag> ListTag<T> getListTag(String key);

    /**
     * Gets a boolean value from the CompoundTag
     *
     * @param key
     *         the name of the tag
     *
     * @return the boolean value
     */
    public boolean getBoolean(String key);

    /**
     * Removes a key from the CompoundTag
     *
     * @param key
     *         the key to remove
     */
    public void remove(String key);

    /**
     * Checks if the CompoundTag is empty
     *
     * @return {@code true} if empty; {@code false} if not
     */
    public boolean isEmpty();

    /** {@inheritDoc} */
    @Override
    public CompoundTag copy();
}
