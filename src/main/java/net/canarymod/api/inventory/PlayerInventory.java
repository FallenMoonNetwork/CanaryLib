package net.canarymod.api.inventory;

/**
 * Player and NPC Inventory wrapper interface
 *
 * @author Jason (darkdiplomat)
 */
public interface PlayerInventory extends Inventory {

    /**
     * Gets the {@link Item} in the Helmet slot
     *
     * @return the helmet {@link Item} or {@code null}
     */
    public Item getHelmetSlot();

    /**
     * Sets the Helmet slot
     *
     * @param item
     *         the {@link Item} to set
     */
    public void setHelmetSlot(Item item);

    /**
     * Gets the {@link Item} in the Chestplate slot
     *
     * @return the Chestplate {@link Item} or {@code null}
     */
    public Item getChestplateSlot();

    /**
     * Sets the Chestplate slot
     *
     * @param item
     *         the {@link Item} to set
     */
    public void setChestPlateSlot(Item item);

    /**
     * Gets the {@link Item} in the Leggings slot
     *
     * @return the Leggins {@link Item} or {@code null}
     */
    public Item getLeggingsSlot();

    /**
     * Sets the Leggings slot
     *
     * @param item
     *         the {@link Item} to set
     */
    public void setLeggingsSlot(Item item);

    /**
     * Gets the {@link Item} in the Boots slot
     *
     * @return the Boots {@link Item} or {@code null}
     */
    public Item getBootsSlot();

    /**
     * Sets the Boots slot
     *
     * @param item
     *         the {@link Item} to set
     */
    public void setBootsSlot(Item item);

    /**
     * Gets the selected hotbar slot's id
     *
     * @return the selected hotbar slot's id
     */
    public int getSelectedHotbarSlotId();

    /**
     * Gets the current {@link Item} in hand
     *
     * @return the {@link Item} in hand
     */
    public Item getItemInHand();
}
