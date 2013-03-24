package net.canarymod.hook.player;


import net.canarymod.api.entity.EntityItem;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.CancelableHook;


/**
 * Item Hook. Contains EntityItem and Player
 * @author
 *
 */
public final class ItemUseHook extends CancelableHook {

    private EntityItem item;
    private Player player;

    public ItemUseHook(Player player, EntityItem item) {
        this.player = player;
        this.item = item;
    }

    /**
     * Gets the {@link Player}
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the {@link EntityItem}
     * @return
     */
    public EntityItem getItem() {
        return item;
    }

    @Override
    public Object[] getDataSet() {
        return new Object[] { player, item, isCanceled };
    }
}
