package net.canarymod.api.entity.living.humanoid;

import net.canarymod.api.entity.EntityItem;
import net.canarymod.api.entity.living.LivingBase;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.PlayerInventory;

/**
 * Human interface
 * Shared methods between Players and NPCs
 *
 * @author Jason (darkdiplomat)
 */
public interface Human extends LivingBase {

    /**
     * Gets the Player's name displayed to others
     *
     * @return Player's display name
     */
    public String getDisplayName();

    /**
     * Sets the Player's name display name
     *
     * @param display
     *         the name to have the Player display
     */
    public void setDisplayName(String display);

    /** Destroys the current item in hand */
    public void destroyItemHeld();

    /**
     * Returns the item that is currently in the players hands
     *
     * @return item held
     */
    public Item getItemHeld();

    /**
     * Make this player drop an item.
     *
     * @param item
     *         the {@link Item} to drop
     */
    public void dropItem(Item item);

    /**
     * Get player inventory
     *
     * @return inventory
     */
    public PlayerInventory getInventory();

    /**
     * Drop all of this players inventory
     *
     * @return {@link EntityItem} array of dropped items
     */
    public EntityItem[] dropInventory();

    /**
     * Give the player the item
     *
     * @param item
     *         the {@link Item} to give
     */
    public void giveItem(Item item);

    /**
     * Return the color for this players name
     *
     * @return prefix
     */
    public String getPrefix();

    /**
     * Set this players name color and prefix
     *
     * @param prefix
     *         the prefix string to set
     */
    public void setPrefix(String prefix);

    /**
     * If this player is blocking (with a sword)
     *
     * @return {@code true} if blocking; {@code false} otherwise
     */
    public boolean isBlocking();

    /**
     * Gets the Capabilities of the Human entity (such as flying and invulnerability)
     *
     * @return the {@link HumanCapabilities}
     */
    public HumanCapabilities getCapabilities();
}
