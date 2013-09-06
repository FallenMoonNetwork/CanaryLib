package net.canarymod.api.nbt;

import java.util.List;

/**
 * An NBT tag that stores a list of other NBT tags of the same type.
 *
 * @param <E>
 *         the {@link BaseTag} object type for the list
 *
 * @author gregthegeek
 */
public interface ListTag<E extends BaseTag> extends BaseTag, List<E> {

    /** {@inheritDoc} */
    @Override
    public ListTag<E> copy();

}
