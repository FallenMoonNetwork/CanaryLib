package net.canarymod.api.nbt;

import java.util.Collection;

/**
 * An NBT tag that stores a collection of NBT tags associated with strings.
 * 
 * @author gregthegeek
 */
public interface CompoundTag extends BaseTag {

    public Collection<BaseTag> values();

    public void put(String key, BaseTag value);

    public void put(String key, byte value);

    public void put(String key, short value);

    public void put(String key, int value);

    public void put(String key, long value);

    public void put(String key, float value);

    public void put(String key, double value);

    public void put(String key, String value);

    public void put(String key, byte[] value);

    public void put(String key, int[] value);

    public void put(String key, CompoundTag value);

    public void put(String key, boolean value);

    public BaseTag get(String key);

    public boolean containsKey(String key);

    public byte getByte(String key);

    public short getShort(String key);

    public int getInt(String key);

    public long getLong(String key);

    public float getFloat(String key);

    public double getDouble(String key);

    public String getString(String key);

    public byte[] getByteArray(String key);

    public int[] getIntArray(String key);

    public CompoundTag getCompoundTag(String key);

    public <T extends BaseTag> ListTag<T> getListTag(String key);

    public boolean getBoolean(String key);

    public void remove(String key);

    public boolean isEmpty();

    public CompoundTag copy();
}
