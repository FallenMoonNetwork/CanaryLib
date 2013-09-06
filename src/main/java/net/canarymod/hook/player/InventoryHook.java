package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.hook.CancelableHook;

/**
 * Inventory hook. Contains information about a player either opening or closing an inventory
 *
 * @author Jason (darkdiplomat)
 */
public final class InventoryHook extends CancelableHook {

    private Player player;
    private Inventory inventory;
    private boolean closing;

    public InventoryHook(Player player, Inventory inventory, boolean closing) {
        this.player = player;
        this.inventory = inventory;
        this.closing = closing;
    }

    /**
     * Gets the {@link Player}
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the {@link Inventory}
     *
     * @return the inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    public boolean isClosing() {
        return closing;
    }

    @Override
    public final String toString() {
        return String.format("%s[Player=%s, Inventory=%s, Is Closing=%s]", getName(), player, inventory, closing);
    }
}
