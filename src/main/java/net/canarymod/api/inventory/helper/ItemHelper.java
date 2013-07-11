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

    /**
     * Extra static reference to the NBTFactory so we don't have to type the whole thing each time
     */
    protected final static NBTFactory NBT_FACTO = Canary.factory().getNBTFactory();

    /**
     * base element tag, call copy() when using
     */
    protected final static CompoundTag TAG = NBT_FACTO.newCompoundTag("tag");

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
        if (nbt_type != NBTTagType.getTypeFromId(item.getDataTag().get(tag).getTypeId())) {
            return false;
        }
        return true;
    }
}
