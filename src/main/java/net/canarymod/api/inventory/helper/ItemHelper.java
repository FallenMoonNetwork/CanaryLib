package net.canarymod.api.inventory.helper;

import net.canarymod.Canary;
import net.canarymod.api.factory.NBTFactory;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.nbt.CompoundTag;
import net.canarymod.api.nbt.NBTTagType;

/**
 * Item Helper
 *
 * @author Jason (darkdiplomat)
 */
abstract class ItemHelper {

    /** Extra static reference to the NBTFactory so we don't have to type the whole thing each time */
    protected final static NBTFactory NBT_FACTO = Canary.factory().getNBTFactory();

    /** base element tag, call copy() when using */
    protected final static CompoundTag TAG = NBT_FACTO.newCompoundTag("tag");

    /**
     * Verifies the NBTTags of the {@link Item} are as expected
     *
     * @param item
     *         the {@link Item} to verify tags for
     * @param tag
     *         the name given to the tag
     * @param nbt_type
     *         the expected {@link NBTTagType}
     * @param setTags
     *         whether to automatically generate missing tags
     *
     * @return {@code true} if good; {@code false} if not
     */
    protected final static boolean verifyTags(Item item, String tag, NBTTagType nbt_type, boolean setTags) {
        if (item == null) {
            return false;
        }
        if (!item.hasDataTag()) {
            if (!setTags) {
                return false;
            }
            item.setDataTag(TAG.copy());
        }
        if (!item.getDataTag().containsKey(tag)) {
            if (!setTags) {
                return false;
            }
            item.getDataTag().put(tag, NBT_FACTO.newTagFromType(nbt_type, tag, null));
        }
        return nbt_type == NBTTagType.getTypeFromId(item.getDataTag().get(tag).getTypeId());
    }
}
