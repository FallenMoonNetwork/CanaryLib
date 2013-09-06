package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.slot.ButtonPress;
import net.canarymod.api.inventory.slot.GrabMode;
import net.canarymod.api.inventory.slot.SecondarySlotType;
import net.canarymod.api.inventory.slot.SlotType;
import net.canarymod.hook.CancelableHook;

/**
 * Slot click hook
 * <p/>
 * Called when a {@link Player} clicks a slot or outside a inventory window
 *
 * @author Jason (darkdiplomat)
 */
public final class SlotClickHook extends CancelableHook {

    private Player player;
    private Inventory inventory;
    private Item item;
    private SlotType slot_type;
    private SecondarySlotType secondary_slot_type;
    private GrabMode grab_mode;
    private ButtonPress button;
    private short slot_id, trans_num;
    private boolean update = true;

    /**
     * Constructs a new SlotClickHook
     *
     * @param player
     *         the {@link Player} clicking
     * @param inventory
     *         the {@link Inventory} thats open
     * @param item
     *         the {@link Item} in the slot clicked
     * @param slot_type
     *         the {@link SlotType} being clicked
     * @param secondary_slot_type
     *         the {@link SecondarySlotType} being clicked
     * @param grab_mode
     *         the {@link GrabMode} of the link
     * @param button
     *         the {@link ButtonPress}
     * @param slot_id
     *         the slot id
     * @param trans_num
     *         the transaction number
     */
    public SlotClickHook(Player player, Inventory inventory, Item item, SlotType slot_type, SecondarySlotType secondary_slot_type, GrabMode grab_mode, ButtonPress button, short slot_id, short trans_num) {
        this.player = player;
        this.inventory = inventory;
        this.item = item;
        this.slot_type = slot_type;
        this.secondary_slot_type = secondary_slot_type;
        this.grab_mode = grab_mode;
        this.button = button;
        this.slot_id = slot_id;
        this.trans_num = trans_num;
    }

    /**
     * Gets the {@link Player} doing the clicking
     *
     * @return the {@link Player} clicking
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the {@link Inventory} that is open and being clicked in
     *
     * @return the open {@link Inventory}
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Gets the {@link Item} in the slot clicked
     *
     * @return the {@link Item} clicked; {@code null} if there isn't an item in the slot
     */
    public Item getItem() {
        return item;
    }

    /**
     * Gets the type of Slot being clicked
     *
     * @return the {@link SlotType} clicked
     */
    public SlotType getSlotType() {
        return slot_type;
    }

    /**
     * Gets the secondary type of the Slot being clicked
     *
     * @return the {@link SecondarySlotType} clicked
     */
    public SecondarySlotType getSecondarySlotType() {
        return secondary_slot_type;
    }

    /**
     * Gets the {@link GrabMode} used in the click
     *
     * @return the {@link GrabMode}
     */
    public GrabMode getGrabMode() {
        return grab_mode;
    }

    /**
     * Gets the button pressed
     *
     * @return The {@link ButtonPress}
     */
    public ButtonPress getButtonPress() {
        return button;
    }

    /**
     * Gets the id of the slot clicked
     *
     * @return the slot id
     */
    public short getSlotId() {
        return slot_id;
    }

    /**
     * Gets the transaction number (increments up by 1 while the window is open)
     *
     * @return the transaction number
     */
    public short getTransactionNumber() {
        return trans_num;
    }

    /**
     * Gets whether to notify the client of a update if canceled
     *
     * @return {@code true} if notify; {@code false} otherwise
     */
    public boolean doUpdate() {
        return update;
    }

    /**
     * Sets whether to notify the client of an update
     *
     * @param update
     *         {@code true} to notify; {@code false} otherwise
     */
    public void setDoUpdate(boolean update) {
        this.update = update;
    }

    /** {@inheritDoc} */
    @Override
    public final String toString() {
        return String.format("%s[Player=%s Inventory=%s Item=%s SlotType=%s SecondarySlotType=%s GrabMode=%s Button=%s SlotId=%d Update=%b]", this.getName(), player, inventory, item, slot_type, secondary_slot_type, grab_mode, button, slot_id, update);
    }
}
