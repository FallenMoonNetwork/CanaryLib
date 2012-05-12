package net.canarymod.api.entity;

import net.canarymod.api.inventory.IInventory;
import net.canarymod.api.inventory.IItem;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Vector3D;
import net.canarymod.group.Group;
import net.canarymod.permissionsystem.PermissionProvider;

public interface IPlayer extends IEntityLiving {
    /**
     * Make the player chat
     * @param message
     */
    public void chat(String message);
    
    /**
     * Send a message to this player
     * @param message
     */
    public void sendMessage(String message);
    
    /**
     * Add to the level of exhaustion of a player
     * @param exhaustion
     */
    public void addExhaustion(float exhaustion);
    
    /**
     * Remove from the level of exhaustion (usually when someone eats something)
     * @param exhaustion
     */
    public void removeExhaustion(float exhaustion);
    
    /**
     * Retrieve the current exhaustion level for this player
     * @return
     */
    public float getExhaustionLevel();
    
    /**
     * Set this players food level
     * @param hunger
     */
    public void setHunger(int hunger);
    
    /**
     * Get this players food level
     * @param hunger
     */
    public int getHunger();
    
    /**
     * Add experience to the player
     * @param experience
     */
    public void addExperience(int experience);
    
    /**
     * Remove experience from the player
     * @param experience
     */
    public void removeExperience(int experience);
    
    /**
     * Get experience points for this player
     * @return
     */
    public int getExperience();
    
    /**
     * Checks if the player is asleep
     * @return
     */
    public boolean isSleeping();
    
    /**
     * Set this player to be sleeping or not
     * @param sleeping
     */
    public void setSleeping(boolean sleeping);
    
    /**
     * Checks if the player is sprinting
     * @return
     */
    public boolean isSprinting();
	
    /**
     * Mark this entity as spriting or not sprinting
     * @param sprinting
     */
    public void setSprinting(boolean sprinting);
    
    /**
     * Returns whether this entity is sneaking
     * @return
     */
    public boolean isSneaking();
	
    /**
     * Mark this entity as sneaking or not
     * @param sneaking
     */
    public void setSneaking(boolean sneaking);
    
    /**
     * Destroys the current item in hand
     */
    public void destroyItemHeld();
    
    /**
     * Returns the item that is currently in the players hands
     * @return
     */
    public IItem getItemHeld();
    
    /**
     * Make this player drop an item.
     * @param item
     */
    public void dropItem(IItem item);
    
    /**
     * Get the spawn position for the player
     * @return
     */
    public Vector3D getSpawnPosition();
    
    /**
     * Sets the spawn position
     * @param spawn
     */
    public void setSpawnPosition(Vector3D spawn);
    
    /**
     * Get this players name
     * @return
     */
    public String getName();
    
    /**
     * Get the IP for this player
     * @return
     */
    public String getIP();
    
    /**
     * Make this player execute the given command
     * @param command
     * @return true if the command executed successfully, false otherwise
     */
    public boolean executeCommand(String command);
    
    /**
     * Check if this player is allowed to fly
     * @return
     */
    public boolean canFly();
    
    /**
     * Check if this player is flying or not
     * @return
     */
    public boolean isFlying();
    
    /**
     * Set if this player is flying or not
     * @param flying
     */
    public void setFlying(boolean flying);
    
    /**
     * Send player a packet. This will throw exception if packet is invalid!
     * TODO: Add throws InvalidPacketException or something
     * @param packet
     */
    public void sendPacket(Object packet);
    /* ***************************************************************
     * CANARY API SPECIFIC THINGS XXX <- will mark a spot in eclipse
     * ***************************************************************/
    
    /**
     * Returns the base group for this player
     * @return
     */
    public Group getGroup();
    
    /**
     * Returns an array of all groups this player belongs to
     * @return
     */
    public Group[] getPlayerGroups();
    
    /**
     * Add a new group to this player
     * @param group
     */
    public void addToGroup(String group);
    
    /**
     * Add a new group to this player
     * @param group
     */
    public void addToGroup(Group group);
    
    /**
     * Remove the group with this name from the player
     * @param group
     */
    public void removeFromGroup(String group);
    
    /**
     * Remove this group from the player
     * @param group
     */
    public void removeFromGroup(Group group);
    
    /**
     * Check if the player has this permission
     * @param permission
     * @return
     */
    public boolean hasPermission(String permission);
    
    /**
     * Check if this player has the admin flag set
     * @return
     */
    public boolean isAdmin();
    
    /**
     * Check if this player can modify the world
     * @return
     */
    public boolean canModifyWorld();
    
    /**
     * Set whether this player can modify the world
     * @param canModify
     */
    public void setCanModifyWorld(boolean canModify);
    
    /**
     * Check if this player can bypass permission checks
     * @return
     */
    public boolean canIgnoreRestrictions();
    
    /**
     * Set whether this player can bypass permission checks
     * @param canIgnore
     */
    public void setCanIgnoreRestrictions(boolean canIgnore);
    
    /**
     * Check if this player is muted
     * @return
     */
    public boolean isMuted();
	
	/**
     * Mute or unmute this player
     * @param muted
     */
    public void setMuted(boolean muted);
    
    /**
     * Returns the PermissionProvider attached to this specific player
     * This may not contain any permissions if the player has no
     * player-specific permissions set
     * @return
     */
    public PermissionProvider getPermissionProvider();

    /**
     * Returns the world location for this player
     * @return
     */
    public Location getLocation();
    
    /**
     * Get player inventory
     * @return
     */
    public IInventory getInventory();
    
    /**
     * Give the player the item
     * @param item
     */
    public void giveItem(IItem item);

    /**
     * Check if player is in the given group
     * @param group
     * @param includeChilds True if you want to take inherited groups into account
     * @return true if the player is in the group, false otherwise
     */
    public boolean isInGroup(Group group, boolean includeChilds);
}
