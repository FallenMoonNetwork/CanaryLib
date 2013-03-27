package net.canarymod.api.nbt;

import java.util.List;

/**
 * An NBT tag that stores a list of other NBT tags of the same type.
 * 
 * @author gregthegeek
 *
 */
public interface ListTag<T extends BaseTag> extends BaseTag, List<T> {
    
}
