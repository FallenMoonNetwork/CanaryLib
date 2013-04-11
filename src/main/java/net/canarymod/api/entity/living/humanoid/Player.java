package net.canarymod.api.entity.living.humanoid;


import net.canarymod.api.NetServerHandler;
import net.canarymod.api.Packet;
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
import net.canarymod.permissionsystem.PermissionProvider;
import net.canarymod.user.Group;


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
     * @return
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
     * @return
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
     * @return
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
     * @return
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
     * @return
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
     * @param loc The new home location.
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
     * @return
     */
    public String getIP();

    /**
     * Returns all allowed IPs for this player as Array
     * @return
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
     * @return
     */
    public boolean canFly();

    /**
     * Check if this player is flying or not
     *
     * @return
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
     * @return
     */
    public NetServerHandler getNetServerHandler();

    /**
     * Returns the amount of damage vs the given entity with the item currently in hand.
     * Will return 1.0F if no item is held
     * @param entity The entity to check against.
     * @return
     */
    public int damageVsEntity(Entity entity);

    /**
     * Returns the amount of damage vs the given block with the item currently in hand.
     * @param block
     * @return
     */
    public float damageVsBlock(Block block);

    /* ***************************************************************
     * CANARY API SPECIFIC THINGS XXX <- will mark a spot in eclipse
     * **************************************************************
     */

    /**
     * Returns the base group for this player
     *
     * @return
     */
    public Group getGroup();

    /**
     * Returns an array of all groups this player belongs to
     *
     * @return
     */
    public Group[] getPlayerGroups();

    /**
     * Add a new group to this player
     *
     * @param group
     */
    public void setGroup(Group group);

    /**
     * Check if the player has this permission
     *
     * @param permission
     * @return
     */
    @Override
    public boolean hasPermission(String permission);

    /**
     * Check if this player has the admin flag set
     *
     * @return
     */
    public boolean isAdmin();

    /**
     * Check if this player can modify the world
     *
     * @return
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
     * @return
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
     * @return
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
     * @return
     */
    public PermissionProvider getPermissionProvider();

    /**
     * Returns the world location for this player
     *
     * @return
     */
    @Override
    public Location getLocation();

    /**
     * This returns the players coordinates but without
     * the informations of Location (pitch, rotation and world).
     * This is more lightweight for faster and more efficient position checking.
     * @return
     */
    @Override
    public Position getPosition();

    /**
     * Get player inventory
     *
     * @return
     */
    public Inventory getInventory();

    /**
     * Get player enderchest inventory
     *
     * @return
     */
    public Inventory getEnderChestInventory();

    /**
     * Drop all of this players inventory
     * @return
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
     * Kick this player
     * @param reason
     */
    public void kick(String reason);

    /**
     * Notify this player with a message
     * (Sends player a red message!)
     * @param message
     */
    @Override
    public void notice(String message);

    /**
     * Return the color for this players name
     * @return
     */
    public String getPrefix();

    /**
     * Set this players name color and prefix
     */
    public void setColor(String color);
    /**
     * Get the cardinal direction this player is looking at
     * @return
     */
    public Direction getCardinalDirection();

    /**
     * gets whether this player is in Creative
     * @return true if creative, false otherwise
     */
    public int getMode();

    /**
     * sets this players mode
     * @param mode
     */
    public void setMode(int mode);

    /**
     * gets whether this player is Damage Disabled
     * @return true if damage is disabled, false otherwise
     */
    public boolean isDamageDisabled();

    /**
     * sets whether this player is Damage Disabled
     * @param disable
     */
    public void setDamageDisabled(boolean disable);

    /**
     * If this player is blocking (with a sword)
     * @return
     */
    public boolean isBlocking();

    /**
     * Check if this player is in a vehicle or not
     * @return True if player is in vehicle, false otherwise
     */
    public boolean isInVehicle();
}
