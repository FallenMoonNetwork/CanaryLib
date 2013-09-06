package net.canarymod.api.factory;

import net.canarymod.api.nbt.BaseTag;
import net.canarymod.api.nbt.ByteArrayTag;
import net.canarymod.api.nbt.ByteTag;
import net.canarymod.api.nbt.CompoundTag;
import net.canarymod.api.nbt.DoubleTag;
import net.canarymod.api.nbt.FloatTag;
import net.canarymod.api.nbt.IntArrayTag;
import net.canarymod.api.nbt.IntTag;
import net.canarymod.api.nbt.ListTag;
import net.canarymod.api.nbt.LongTag;
import net.canarymod.api.nbt.NBTTagType;
import net.canarymod.api.nbt.ShortTag;
import net.canarymod.api.nbt.StringTag;

/**
 * NamedBinaryTag Manufacturing Factory
 *
 * @author Jason (darkdiplomat)
 */
public interface NBTFactory {

    /**
     * Creates a new {@link CompoundTag}
     *
     * @param name
     *         the name of the tag
     *
     * @return a new {@link CompoundTag}
     */
    CompoundTag newCompoundTag(String name);

    /**
     * Creates a new {@link ByteTag}
     *
     * @param name
     *         the name of the tag
     * @param value
     *         the {@code byte} value
     *
     * @return a new {@link ByteTag}
     */
    ByteTag newByteTag(String name, byte value);

    /**
     * Creates a new {@link ByteArrayTag}
     *
     * @param name
     *         the name of the tag
     * @param value
     *         the {@code byte[]} value
     *
     * @return a new {@link ByteArrayTag}
     */
    ByteArrayTag newByteArrayTag(String name, byte[] value);

    /**
     * Creates a new {@link DoubleTag}
     *
     * @param name
     *         the name of the tag
     * @param value
     *         the {@code double} value
     *
     * @return a new {@link DoubleTag}
     */
    DoubleTag newDoubleTag(String name, double value);

    /**
     * Creates a new {@link FloatTag}
     *
     * @param name
     *         the name of the tag
     * @param value
     *         the {@code float} value
     *
     * @return a new {@link FloatTag}
     */
    FloatTag newFloatTag(String name, float value);

    /**
     * Creates a new {@link IntTag}
     *
     * @param name
     *         the name of the tag
     * @param value
     *         the {@code int} value
     *
     * @return a new {@link IntTag}
     */
    IntTag newIntTag(String name, int value);

    /**
     * Creates a new {@link IntArrayTag}
     *
     * @param name
     *         the name of the tag
     * @param value
     *         the {@code int[]} value
     *
     * @return a new {@link IntArrayTag}
     */
    IntArrayTag newIntArrayTag(String name, int[] value);

    /**
     * Creates a new {@link ListTag}
     *
     * @param name
     *         the name of the tag
     *
     * @return a new {@link ListTag}
     */
    <E extends BaseTag> ListTag<E> newListTag(String name);

    /**
     * Creates a new {@link LongTag}
     *
     * @param name
     *         the name of the tag
     * @param value
     *         the {@code long} value
     *
     * @return a new {@link LongTag}
     */
    LongTag newLongTag(String name, long value);

    /**
     * Creates a new {@link ShortTag}
     *
     * @param name
     *         the name of the tag
     * @param value
     *         the {@code short} value
     *
     * @return a new {@link ShortTag}
     */
    ShortTag newShortTag(String name, short value);

    /**
     * Creates a new {@link DoubleTag}
     *
     * @param name
     *         the name of the tag
     * @param value
     *         the {@code String} value
     *
     * @return a new {@link StringTag}
     */
    StringTag newStringTag(String name, String value);

    /**
     * Creates a new {@link BaseTag} from the specifed type (unless type is UNKNOWN)
     *
     * @param type
     *         the {@link NBTTagType} to create
     * @param name
     *         the name of the tag
     * @param value
     *         the value of the tag if needed
     *
     * @return new {@link BaseTag} or null if invalid
     */
    BaseTag newTagFromType(NBTTagType type, String name, Object value);

}
