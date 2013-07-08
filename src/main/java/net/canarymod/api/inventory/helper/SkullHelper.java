package net.canarymod.api.inventory.helper;

import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;

/**
 * Skull helper
 * 
 * @author Jason (darkdiplomat)
 */
public class SkullHelper extends ItemHelper {
    private SkullHelper() {} // This class should never be constructed

    public enum SkullType {
        /**
         * Skeleton Skull
         */
        SKELETON, //
        /**
         * Wither Skull
         */
        WITHER, //
        /**
         * Zombie Skull
         */
        ZOMBIE, //
        /**
         * Player Skull
         */
        PLAYER, //
        /**
         * Creeper Skull
         */
        CREEPER, //
        ;
    }

    /**
     * Checks if the Skull has a owner
     * 
     * @return {@code true} if has owner; {@code false} if not
     */
    public static boolean hasOwner(Item skull) {
        if (skull == null) {
            return false;
        }
        if (skull.getType() != ItemType.Skull) {
            return false;
        }
        if (getSkullType(skull) != SkullType.PLAYER) {
            return false;
        }
        if (!skull.hasDataTag()) {
            return false;
        }
        if (!skull.getDataTag().containsKey("SkullOwner")) {
            return false;
        }
        String owner = skull.getDataTag().getString("SkullOwner");
        return !owner.isEmpty();
    }

    /**
     * Gets the name of the owner if present
     * 
     * @return the owner's name or {@code null} if no owner
     */
    public static String getOwner(Item skull) {
        if (skull == null) {
            return null;
        }
        if (skull.getType() != ItemType.Skull) {
            return null;
        }
        if (getSkullType(skull) != SkullType.PLAYER) {
            return null;
        }
        if (!skull.hasDataTag()) {
            return null;
        }
        if (!skull.getDataTag().containsKey("SkullOwner")) {
            return null;
        }
        return skull.getDataTag().getString("SkullOwner");
    }

    /**
     * Sets the owner of the skull.<br>
     * Providing a null owner will remove the current owner
     * 
     * @param owner
     *            the owner to be set, or null to remove the owner
     */
    public static void setOwner(Item skull, String owner) {
        if (skull == null) {
            return;
        }
        if (skull.getType() != ItemType.Skull) {
            return;
        }
        if (getSkullType(skull) != SkullType.PLAYER) {
            return;
        }
        if (!skull.hasDataTag() && owner != null) {
            skull.setDataTag(TAG.copy());
        }
        if (owner != null) {
            skull.getDataTag().put("SkullOwner", owner);
        }
        else if (skull.getDataTag().containsKey("SkullOwner")) {
            skull.getDataTag().remove("SkullOwner");
        }
    }

    /**
     * Gets the SkullType for the Skull
     * 
     * @return the SkullType
     */
    public static SkullType getSkullType(Item skull) {
        if (skull == null) {
            return null;
        }
        if (skull.getType() != ItemType.Skull) {
            return null;
        }
        switch (skull.getDamage()) {
            case 0:
                return SkullType.SKELETON;
            case 1:
                return SkullType.WITHER;
            case 2:
                return SkullType.ZOMBIE;
            case 3:
                return SkullType.PLAYER;
            case 4:
                return SkullType.CREEPER;
            default:
                return SkullType.SKELETON;
        }
    }
}
