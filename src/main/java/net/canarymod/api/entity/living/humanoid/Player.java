package net.canarymod.api.entity.living.humanoid;

import net.canarymod.api.NetServerHandler;
import net.canarymod.api.Packet;
import net.canarymod.api.PlayerListEntry;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.world.position.Direction;
import net.canarymod.api.world.position.Location;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.hook.player.TeleportHook;
import net.canarymod.permissionsystem.PermissionProvider;
import net.canarymod.user.Group;

/**
 * Player wrapper
 * 
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public interface Player extends Human, MessageReceiver {

    /**
     * Initializes or re-initializes the permissions, groups and prefix data for this player
     */
    public void initPlayerData();

    /**
     * Sends a message as the Player
     * 
     * @param message
     *            the message to send as the Player
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
     */
    public void setSpawnPosition(Location spawn);

    /**
     * Set this players home location
     * 
     * @param loc
     *            The new home location.
     */
    public void setHome(Location loc);

    /**
     * Get this players home location
     */
    public Location getHome();

    /**
     * Check if this player has a home location set
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
     * @return true if the command executed successfully, false otherwise
     */
    public boolean executeCommand(String[] command);

    /**
     * Send player a packet. This will throw exception if packet is invalid!
     * 
     * @param packet
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
     * @return {@code true} if successful; {@code false} otherwise
     */
    public boolean removeGroup(Group group);

    /**
     * Remove a group with this name from the player.<br>
     * You can NOT delete the default group.
     * 
     * @param group
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
     */
    public void setGroup(Group group);

    /**
     * Add a sub group to this player
     * 
     * @param group
     */
    public void addGroup(Group group);

    /**
     * Check if the player has this permission.
     * This will issue a PermissionCheck hook that means,
     * the returned result is not reliable.
     * However, this allows other Plugins to have a say in the permission lookup process.
     * 
     * @param permission
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
     *            the {@link Group} to check
     * @param parents
     *            {@code true} if you want to take parent groups into account; {@code false} otherwise
     * @return {@code true} if the player is in the group, {@code false} otherwise
     */
    public boolean isInGroup(Group group, boolean parents);

    /**
     * Check if player is in the given group
     * 
     * @param group
     *            the name of the group to check
     * @param parents
     *            {@code true} if you want to take parent groups into account; {@code false} otherwise
     * @return {@code true} if the player is in the group; {@code false} otherwise
     */
    public boolean isInGroup(String group, boolean parents);

    /**
     * Teleport to this location with specified cause
     * <p>
     * If other Teleport methods are called, cause default to PLUGIN
     * 
     * @param location
     * @param cause
     */
    public void teleportTo(Location location, TeleportHook.TeleportCause cause);

    /**
     * Kick this player
     * 
     * @param reason
     */
    public void kick(String reason);

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
     * <p>
     * The initially set name is {@link Player#getDisplayName()}
     * 
     * @param shown
     *            whether it should be shown by default
     * @return {@link PlayerListEntry} for the Player
     * @see PlayerListEntry
     */
    public PlayerListEntry getPlayerListEntry(boolean shown);

    /**
     * Sends a {@link PlayerListEntry} to the Player
     * <p>
     * NOTE: The server needs to have PlayerList enabled in the configuration
     * 
     * @param plentry
     *            the {@link PlayerListEntry} to send
     * @see PlayerListEntry
     */
    public void sendPlayerListEntry(PlayerListEntry plentry);
}
