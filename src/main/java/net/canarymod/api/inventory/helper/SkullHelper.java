package net.canarymod.api.inventory.helper;

import static net.canarymod.api.nbt.NBTTagType.STRING;

import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;

/**
 * Skull helper
 *
 * @author Jason (darkdiplomat)
 */
public class SkullHelper extends ItemHelper {
    private SkullHelper() {
    } // This class should never be constructed

    public enum SkullType {
        /** Skeleton Skull */
        SKELETON, //
        /** Wither Skull */
        WITHER, //
        /** Zombie Skull */
        ZOMBIE, //
        /** Player Skull */
        PLAYER, //
        /** Creeper Skull */
        CREEPER, //
        ;
    }

    /**
     * Checks if the Skull has a owner
     *
     * @param skull
     *         the skull {@link Item}
     *
     * @return {@code true} if has owner; {@code false} if not
     */
    public static boolean hasOwner(Item skull) {
        if (skull == null || !validSkull(skull.getType()) || getSkullType(skull) != SkullType.PLAYER) {
            return false;
        }
        if (!verifyTags(skull, "SkullOwner", STRING, false)) {
            return false;
        }
        String owner = skull.getDataTag().getString("SkullOwner");
        return !owner.isEmpty();
    }

    /**
     * Gets the name of the owner if present
     *
     * @param skull
     *         the skull {@link Item}
     *
     * @return the owner's name or {@code null} if no owner
     */
    public static String getOwner(Item skull) {
        if (skull == null || !validSkull(skull.getType()) || getSkullType(skull) != SkullType.PLAYER) {
            return null;
        }
        if (!verifyTags(skull, "SkullOwner", STRING, false)) {
            return null;
        }
        return skull.getDataTag().getString("SkullOwner");
    }

    /**
     * Sets the owner of the skull.<br>
     * Providing a null owner will remove the current owner
     *
     * @param skull
     *         the skull {@link Item}
     * @param owner
     *         the owner to be set, or null to remove the owner
     */
    public static void setOwner(Item skull, String owner) {
        if (skull == null || !validSkull(skull.getType()) || getSkullType(skull) != SkullType.PLAYER) {
            return;
        }
        if (!verifyTags(skull, "SkullOwner", STRING, false) && owner == null) {
            return;
        }
        if (owner == null) {
            skull.getDataTag().remove("SkullOwner");
        }
        else {
            skull.getDataTag().put("SkullOwner", owner);
        }
    }

    /**
     * Gets the SkullType for the Skull
     *
     * @param skull
     *         the skull {@link Item}
     *
     * @return the SkullType
     */
    public static SkullType getSkullType(Item skull) {
        if (skull == null || !validSkull(skull.getType())) {
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

    private static boolean validSkull(ItemType type) {
        if (type == ItemType.SkeletonHead) {
            switch (type.getData()) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }
}
