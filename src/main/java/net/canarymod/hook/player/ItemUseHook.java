package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;

/**
 * Item use hook. Called when a {@link Player} uses an {@link Item}
 *
 * @author Jason (darkdiplomat)
 */
public final class ItemUseHook extends CancelableHook {

    private Item item;
    private Player player;
    private Block clicked;

    public ItemUseHook(Player player, Item item, Block clicked) {
        this.player = player;
        this.item = item;
        this.clicked = clicked;
    }

    /**
     * Gets the {@link Player}
     *
     * @return the {@link Player} doing the clicking
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the {@link Item}
     *
     * @return the {@link Item}
     */
    public Item getItem() {
        return item;
    }

    /**
     * Gets the {@link Block} being clicked on, or Air if no block is being clicked
     *
     * @return the {@link Block} being clicked
     */
    public Block getBlockClicked() {
        return clicked;
    }

    @Override
    public final String toString() {
        return String.format("%s[Player=%s, Item=%s, Block=%s]", getName(), player, item, clicked);
    }
}
