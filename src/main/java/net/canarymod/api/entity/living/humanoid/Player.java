package net.canarymod.api.entity.living.humanoid;

import net.canarymod.api.GameMode;
import net.canarymod.api.NetServerHandler;
import net.canarymod.api.PlayerListEntry;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.packet.Packet;
import net.canarymod.api.world.blocks.Sign;
import net.canarymod.api.world.position.Direction;
import net.canarymod.api.world.position.Location;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.hook.player.TeleportHook.TeleportCause;
import net.canarymod.permissionsystem.PermissionProvider;
import net.canarymod.user.Group;

/**
 * Player wrapper
 *
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public interface Player extends Human, MessageReceiver {

    /** Initializes or re-initializes the permissions, groups and prefix data for this player */
    public void initPlayerData();

    /**
     * Sends a message as the Player
     *
     * @param message
     *         the message to send as the Player
     */
    public void chat(String message);

    /**
     * Get the spawn position for the player
     *
     * @return spawn position
     */
    public Location getSpawnPosition();

    /**
     * Sets the spawn position
     *
     * @param spawn
     *         the {@link Location} to spawn at
     */
    public void setSpawnPosition(Location spawn);

    /**
     * Set this players home location
     *
     * @param loc
     *         The new home location.
     */
    public void setHome(Location loc);

    /**
     * Get this players home location
     *
     * @return home {@link Location}
     */
    public Location getHome();

    /**
     * Check if this player has a home location set
     *
     * @return {@code true} if home is set; {@code false} if not
     */
    public boolean hasHome();

    /**
     * Get the IP for this player
     *
     * @return ip address
     */
    public String getIP();

    /**
     * Returns all allowed IPs for this player as Array
     *
     * @return allowed ips
     */
    public String[] getAllowedIPs();

    /**
     * Make this player execute the given command
     *
     * @param command
     *         the String array arguments
     *
     * @return true if the command executed successfully, false otherwise
     */
    public boolean executeCommand(String[] command);

    /**
     * Send player a packet. This will throw exception if packet is invalid!
     *
     * @param packet
     *         the {@link Packet} to send
     */
    public void sendPacket(Packet packet);

    /**
     * Get the NetServerHandler for this player
     *
     * @return {@link NetServerHandler}
     */
    public NetServerHandler getNetServerHandler();

    /**
     * Returns the main group for this player
     *
     * @return group
     */
    public Group getGroup();

    /**
     * Remove this group from the player.<br>
     * You can NOT delete the default group.
     *
     * @param group
     *         the {@link Group} to remove
     *
     * @return {@code true} if successful; {@code false} otherwise
     */
    public boolean removeGroup(Group group);

    /**
     * Remove a group with this name from the player.<br>
     * You can NOT delete the default group.
     *
     * @param group
     *         the group name to remove
     *
     * @return {@code true} if successful; {@code false} otherwise
     */
    public boolean removeGroup(String group);

    /**
     * Returns an array of all groups this player belongs to
     *
     * @return groups
     */
    public Group[] getPlayerGroups();

    /**
     * Set this players main group
     *
     * @param group
     *         the {@link Group} to set
     */
    public void setGroup(Group group);

    /**
     * Add a sub group to this player
     *
     * @param group
     *         the {@link Group} to add
     */
    public void addGroup(Group group);

    /**
     * Check if the player has this permission.
     * This will issue a PermissionCheck hook that means,
     * the returned result is not reliable.
     * However, this allows other Plugins to have a say in the permission lookup process.
     *
     * @param permission
     *         the string permission
     *
     * @return {@code true} if has permission; {@code false} otherwise
     */
    @Override
    public boolean hasPermission(String permission);

    /**
     * Check if a player has this permission.
     * This will not issue a PermissionCheck hook so the returned
     * result is reliable.
     */
    public boolean safeHasPermission(String permission);

    /**
     * Check if this player has the admin flag set
     *
     * @return {@code true} if is Admin; {@code false} otherwise
     */
    public boolean isAdmin();

    /**
     * Check if this player can modify the world
     *
     * @return {@code true} if can Build; {@code false} otherwise
     */
    public boolean canBuild();

    /**
     * Set whether this player can modify the world
     *
     * @param canModify
     *         {@code true} if can build; {@code false} if not
     */
    public void setCanBuild(boolean canModify);

    /**
     * Check if this player can bypass permission checks
     *
     * @return {@code true} if can ignore restrictions; {@code false} otherwise
     */
    public boolean canIgnoreRestrictions();

    /**
     * Set whether this player can bypass permission checks
     *
     * @param canIgnore
     *         {@code true} if can ignore restrictions; {@code false} if not
     */
    public void setCanIgnoreRestrictions(boolean canIgnore);

    /**
     * Check if this player is muted
     *
     * @return {@code true} if muted; {@code false} otherwise
     */
    public boolean isMuted();

    /**
     * Mute or unmute this player
     *
     * @param muted
     *         {@code true} for mute; {@code false} for unmute
     */
    public void setMuted(boolean muted);

    /**
     * Returns the PermissionProvider attached to this specific player This may
     * not contain any permissions if the player has no player-specific
     * permissions set
     *
     * @return permission provider
     */
    public PermissionProvider getPermissionProvider();

    /**
     * Get player enderchest inventory
     *
     * @return enderchest inventory
     */
    public Inventory getEnderChestInventory();

    /**
     * Check if player is in the given group
     *
     * @param group
     *         the {@link Group} to check
     * @param parents
     *         {@code true} if you want to take parent groups into account; {@code false} otherwise
     *
     * @return {@code true} if the player is in the group, {@code false} otherwise
     */
    public boolean isInGroup(Group group, boolean parents);

    /**
     * Check if player is in the given group
     *
     * @param group
     *         the name of the group to check
     * @param parents
     *         {@code true} if you want to take parent groups into account; {@code false} otherwise
     *
     * @return {@code true} if the player is in the group; {@code false} otherwise
     */
    public boolean isInGroup(String group, boolean parents);

    /**
     * Teleport to this location with specified cause
     * <p/>
     * If other Teleport methods are called, cause default to PLUGIN
     *
     * @param location
     *         the {@link Location} to teleport to
     * @param cause
     *         the {@link TeleportCause}]
     */
    public void teleportTo(Location location, TeleportCause cause);

    /**
     * Kick this player
     *
     * @param reason
     *         the string reasoning
     */
    public void kick(String reason);

    /**
     * Kick this player without a hook call
     *
     * @param reason
     *         the string reasoning
     */
    public void kickNoHook(String reason);

    /**
     * Get the cardinal direction this player is looking at
     *
     * @return direction
     */
    public Direction getCardinalDirection();

    /**
     * Gets the Player's ping
     *
     * @return the ping
     */
    public int getPing();

    /**
     * Gets a {@link PlayerListEntry} for the Player
     * <p/>
     * The initially set name is {@link Player#getDisplayName()}
     *
     * @param shown
     *         whether it should be shown by default
     *
     * @return {@link PlayerListEntry} for the Player
     *
     * @see PlayerListEntry
     */
    public PlayerListEntry getPlayerListEntry(boolean shown);

    /**
     * Sends a {@link PlayerListEntry} to the Player
     * <p/>
     * NOTE: The server needs to have PlayerList enabled in the configuration
     *
     * @param plentry
     *         the {@link PlayerListEntry} to send
     *
     * @see PlayerListEntry
     */
    public void sendPlayerListEntry(PlayerListEntry plentry);

    /**
     * Add to the level of food exhaustion of a player
     *
     * @param exhaustion
     *         the exhaustion to add
     */
    public void addExhaustion(float exhaustion);

    /**
     * Set the food exhaustion level to the specified value
     *
     * @param exhaustion
     *         the exhaustion to set
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
     *         the hunger to set
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
     *         the experience amount
     */
    public void addExperience(int experience);

    /**
     * Remove experience from the player
     *
     * @param experience
     *         the experience amount
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
     *         the experience amount
     */
    public void setExperience(int xp);

    /**
     * Get the current level of this player.
     *
     * @return level
     */
    public int getLevel();

    /**
     * Explicitly override this players level.
     *
     * @param level
     *         the new player level
     */
    public void setLevel(int level);

    /**
     * Add the specific amount of levels to the player.
     *
     * @param level
     *         amount of levels to add
     */
    public void addLevel(int level);

    /**
     * Explicitly remove the amount of levels from the player.
     *
     * @param level
     *         amount of levels to remove
     */
    public void removeLevel(int level);

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
     *         the {@link GameMode} to set
     */
    public void setMode(GameMode mode);

    /**
     * Sets the Player's {@link GameMode}
     *
     * @param mode
     *         0 for Survival; 1 for Creative; 2 for Adventure
     */
    public void setModeId(int mode);

    /** Refreshes mode */
    public void refreshCreativeMode();

    /** Sends update of PlayerCapabilities to player */
    public void updateCapabilities();

    /**
     * Sends the WindowOpenPacket to the Player with the specified Inventory
     * <p/>
     * NOTE: Some inventories may not work remotely
     *
     * @param inventory
     *         the {@link Inventory} to have opened
     */
    public void openInventory(Inventory inventory);

    /** Creates a fake Workbench and sends the WindowOpenPacket to the Player */
    public void createAndOpenWorkbench();

    /** Creates a fake Anvil and sends the WindowOpenPacket to the Player */
    public void createAndOpenAnvil();

    /**
     * Creates a fake EnchantmentTable and then sends the WindowOpenPacket to the Player
     *
     * @param bookshelves
     *         **Currently not implemented** Sets the number of bookshelves to fake around the enchantment table
     */
    public void createAndOpenEnchantmentTable(int bookshelves);

    /**
     * Opens the {@link Sign} edit window.
     * <p/>
     * NOTE: For changes to take effect, the {@link Sign} needs to be editable, or the Player set as owner
     *
     * @param sign
     *         the {@link Sign} to edit
     */
    public void openSignEditWindow(Sign sign);

    /**
     * Closes an open Inventory GUI if one is presently open.<br/>
     * This should also call the close method for that inventory.
     */
    public void closeWindow();

    /**
     * Gets the date and time the Player first joined (or when the player.dat was created) formatted as dd-MMM-yyyy HH:mm:ss
     *
     * @return first joined
     */
    public String getFirstJoined();

    /**
     * Gets the time played in seconds
     *
     * @return time played
     */
    public long getTimePlayed();

    /**
     * Gets the locale the player is using in their client. This is in the format of language_region e.g. en_US
     *
     * @return the player's locale
     */
    public String getLocale();
}
