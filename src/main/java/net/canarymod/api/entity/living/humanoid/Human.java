package net.canarymod.api.entity.living.humanoid;

import net.canarymod.api.GameMode;
import net.canarymod.api.entity.EntityItem;
import net.canarymod.api.entity.living.LivingBase;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;

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
     *            the name to have the Player display
     */
    public void setDisplayName(String display);

    /**
     * Add to the level of food exhaustion of a player
     * 
     * @param exhaustion
     *            the exhaustion to add
     */
    public void addExhaustion(float exhaustion);

    /**
     * Set the food exhaustion level to the specified value
     * 
     * @param exhaustion
     *            the exhaustion to set
     */
    public void setExhaustion(float exhaustion);

    /**
     * Retrieve the current exhaustion level for this player
     * 
     * @return exhaustion level
     */
    public float getExhaustionLevel();

    /**
     * Set this players food level
     * 
     * @param hunger
     *            the hunger to set
     */
    public void setHunger(int hunger);

    /**
     * Get this players food level
     * 
     * @return the food level.
     */
    public int getHunger();

    /**
     * Add experience to the player
     * 
     * @param experience
     */
    public void addExperience(int experience);

    /**
     * Remove experience from the player
     * 
     * @param experience
     */
    public void removeExperience(int experience);

    /**
     * Get experience points for this player
     * 
     * @return experience
     */
    public int getExperience();

    /**
     * Set the experience of this player
     * 
     * @param xp
     */
    public void setExperience(int xp);

    /**
     * Get the current level of this player.
     * 
     * @return level
     */
    public int getLevel();

    /**
     * Checks if the player is asleep
     * 
     * @return true if player is in bed, false otherwise
     */
    public boolean isSleeping();

    /**
     * Check if this player is deeply sleeping,
     * that is: Player is in bed and the screen has fully faded out.
     * 
     * @return true if fully asleep, false otherwise
     */
    public boolean isDeeplySleeping();

    /**
     * Destroys the current item in hand
     */
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
     */
    public void dropItem(Item item);

    /**
     * Check if this player is flying or not
     * 
     * @return {@code true} if flying; {@code false} otherwise
     */
    public boolean isFlying();

    /**
     * Set if this player is flying or not
     * 
     * @param flying
     */
    public void setFlying(boolean flying);

    /**
     * Get player inventory
     * 
     * @return inventory
     */
    public Inventory getInventory();

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
     *            the {@link Item} to give
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
     */
    public void setPrefix(String prefix);

    /**
     * Gets the {@link GameMode} for the Player
     * 
     * @return the Player's {@link GameMode}
     */
    public GameMode getMode();

    /**
     * Gets the {@link GameMode} ID for the Player
     * 
     * @return 0 for Survival; 1 for Creative; 2 for Adventure
     */
    public int getModeId();

    /**
     * Sets the Player's {@link GameMode}
     * 
     * @param mode
     *            the {@link GameMode} to set
     */
    public void setMode(GameMode mode);

    /**
     * Sets the Player's {@link GameMode}
     * 
     * @param mode
     *            0 for Survival; 1 for Creative; 2 for Adventure
     */
    public void setModeId(int mode);

    /**
     * gets whether this player is Damage Disabled
     * 
     * @return {@code true} if damage is disabled; {@code false} otherwise
     */
    public boolean isDamageDisabled();

    /**
     * sets whether this player is Damage Disabled
     * 
     * @param disable
     *            {@code true} for disabled; {@code false} otherwise
     */
    public void setDamageDisabled(boolean disable);

    /**
     * If this player is blocking (with a sword)
     * 
     * @return {@code true} if blocking; {@code false} otherwise
     */
    public boolean isBlocking();

    /**
     * Check if this player is in a vehicle or not
     * 
     * @return {@code true} if player is in vehicle; {@code false} otherwise
     */
    public boolean isInVehicle();

}
