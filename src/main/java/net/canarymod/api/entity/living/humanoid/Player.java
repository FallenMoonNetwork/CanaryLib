package net.canarymod.api.entity.living.humanoid;

import net.canarymod.api.NetServerHandler;
import net.canarymod.api.Packet;
import net.canarymod.api.PlayerListEntry;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.EntityItem;
import net.canarymod.api.entity.living.EntityLiving;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.World;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.position.Direction;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Position;
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
public interface Player extends EntityLiving, MessageReceiver {

    /**
     * Initializes or re-initializes the permissions, groups and prefix data for this player
     */
    public void initPlayerData();

    /**
     * Make the player chat
     * 
     * @param message
     */
    public void chat(String message);

    /**
     * Send a message to this player
     * 
     * @param message
     */
    public void sendMessage(String message);

    /**
     * Add to the level of food exhaustion of a player
     * 
     * @param exhaustion
     */
    public void addExhaustion(float exhaustion);

    /**
     * Set the food exhaustion level to the specified value
     * 
     * @param exhaustion
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
     * Get this players name
     * 
     * @return the name
     */
    @Override
    public String getName();

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
     * Set this players home location
     * 
     * @param loc
     *            The new home location.
     */
    @Override
    public void setHome(Location loc);

    /**
     * Get this players home location
     */
    @Override
    public Location getHome();

    /**
     * Check if this player has a home location set
     */
    @Override
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
     * Check if this player is allowed to fly
     * 
     * @return {@code true} if can fly; {@code false} otherwise
     */
    public boolean canFly();

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
     * Returns the amount of damage vs the given entity with the item currently in hand.
     * Will return 1.0F if no item is held
     * 
     * @param entity
     *            The entity to check against.
     * @return damage vs entity
     */
    public int damageVsEntity(Entity entity);

    /**
     * Returns the amount of damage vs the given block with the item currently in hand.
     * 
     * @param block
     * @return damage vs block
     */
    public float damageVsBlock(Block block);

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
     * Returns the world location for this player
     * 
     * @return location
     */
    @Override
    public Location getLocation();

    /**
     * This returns the players coordinates but without
     * the informations of Location (pitch, rotation and world).
     * This is more lightweight for faster and more efficient position checking.
     * 
     * @return position
     */
    @Override
    public Position getPosition();

    /**
     * Get player inventory
     * 
     * @return inventory
     */
    public Inventory getInventory();

    /**
     * Get player enderchest inventory
     * 
     * @return enderchest inventory
     */
    public Inventory getEnderChestInventory();

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
     */
    public void giveItem(Item item);

    /**
     * Check if player is in the given group
     * 
     * @param group
     * @param parents
     *            True if you want to take parent groups into account
     * @return true if the player is in the group, false otherwise
     */
    public boolean isInGroup(Group group, boolean parents);

    /**
     * Check if player is in the given group
     * 
     * @param group
     * @param parents
     *            True if you want to take parent groups into account
     * @return true if the player is in the group, false otherwise
     */
    public boolean isInGroup(String group, boolean parents);

    /**
     * Teleport to this location
     * 
     * @param x
     * @param y
     * @param z
     */
    public void teleportTo(double x, double y, double z);

    /**
     * Teleport the player to the position at the given Vector3D
     * 
     * @param position
     */
    public void teleportTo(Position position);

    /**
     * Teleport to this coords in the given dimension
     * 
     * @param x
     * @param y
     * @param z
     * @param dim
     */
    public void teleportTo(double x, double y, double z, World dim);

    /**
     * Teleport to this location in the given world
     * 
     * @param x
     * @param y
     * @param z
     * @param pitch
     * @param rotation
     * @param dim
     */
    public void teleportTo(double x, double y, double z, float pitch, float rotation, World dim);

    /**
     * Teleport to this location and set pitch and rotation
     * 
     * @param x
     * @param y
     * @param z
     * @param pitch
     * @param rotation
     */
    public void teleportTo(double x, double y, double z, float pitch, float rotation);

    /**
     * Teleport to the specified location
     * 
     * @param location
     */
    public void teleportTo(Location location);

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
     * Notify this player with a message
     * (Sends player a red message!)
     * 
     * @param message
     */
    @Override
    public void notice(String message);

    /**
     * Return the color for this players name
     * 
     * @return prefix
     */
    public String getPrefix();

    /**
     * Set this players name color and prefix
     */
    public void setColor(String color);

    /**
     * Get the cardinal direction this player is looking at
     * 
     * @return direction
     */
    public Direction getCardinalDirection();

    /**
     * gets whether this player is in Creative
     * 
     * @return true if creative, false otherwise
     */
    public int getMode();

    /**
     * sets this players mode
     * 
     * @param mode
     */
    public void setMode(int mode);

    /**
     * gets whether this player is Damage Disabled
     * 
     * @return true if damage is disabled, false otherwise
     */
    public boolean isDamageDisabled();

    /**
     * sets whether this player is Damage Disabled
     * 
     * @param disable
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
     * @return True if player is in vehicle, false otherwise
     */
    public boolean isInVehicle();

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
