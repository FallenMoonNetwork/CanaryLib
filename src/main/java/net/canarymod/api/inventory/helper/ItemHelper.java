package net.canarymod.api.inventory.helper;

import net.canarymod.Canary;
import net.canarymod.api.factory.NBTFactory;
import net.canarymod.api.nbt.CompoundTag;

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

}
