package net.canarymod.hook.player;

import net.canarymod.api.entity.EntityItem;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.CancelableHook;

/**
 * Item Hook. Contains EntityItem and Player
 *
 * @author
 */
public final class ItemPickupHook extends CancelableHook {

    private EntityItem item;
    private Player player;

    public ItemPickupHook(Player player, EntityItem item) {
        this.player = player;
        this.item = item;
    }

    /**
     * Gets the {@link Player}
     *
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the {@link EntityItem}
     *
     * @return
     */
    public EntityItem getItem() {
        return item;
    }

    @Override
    public final String toString() {
        return String.format("%s[Player=%s, Item=%s]", getName(), player, item);
    }
}
