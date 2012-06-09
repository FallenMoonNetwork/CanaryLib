package net.canarymod.api.entity;

import net.canarymod.api.NetServerHandler;
import net.canarymod.api.Packet;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.Dimension;
import net.canarymod.api.world.position.Direction;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Vector3D;
import net.canarymod.permissionsystem.PermissionProvider;
import net.canarymod.user.Group;

public interface Player extends EntityLiving {
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
     * Add to the level of exhaustion of a player
     * 
     * @param exhaustion
     */
    public void addExhaustion(float exhaustion);

    /**
     * Remove from the level of exhaustion (usually when someone eats something)
     * 
     * @param exhaustion
     */
    public void removeExhaustion(float exhaustion);

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
     * @param hunger
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
     * Checks if the player is asleep
     * 
     * @return
     */
    public boolean isSleeping();

    /**
     * Set this player to be sleeping or not
     * 
     * @param sleeping
     */
    public void setSleeping(boolean sleeping);

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
     * @return
     */
    public String getName();

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
    public Location getLocation();
    
    /**
     * This returns the players coordinates but without 
     * the informations of Location (pitch, rotation and world).
     * This is more lightweight for faster and more efficient position checking.
     * @return
     */
    public Vector3D getPosition();

    /**
     * Get player inventory
     * 
     * @return
     */
    public Inventory getInventory();

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
     * @param includeParents
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
    public void teleportTo(Vector3D position);

    /**
     * Teleport to this coords in the given dimension
     * 
     * @param x
     * @param y
     * @param z
     * @param dim
     */
    public void teleportTo(double x, double y, double z, Dimension dim);

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
    public void teleportTo(double x, double y, double z, float pitch, float rotation, Dimension dim);

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
    public void notify(String message);
    
    /**
     * Return the color for this players name
     * @return
     */
    public String getColor();
    
    /**
     * Get the cardinal direction this player is looking at
     * @return
     */
    public Direction getCardinalDirection();
}
