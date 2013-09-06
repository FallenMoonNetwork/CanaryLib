package net.canarymod.api;

import net.canarymod.api.nbt.CompoundTag;
import net.canarymod.api.world.World;
import net.canarymod.api.world.position.Position;
import net.canarymod.permissionsystem.PermissionProvider;
import net.canarymod.user.Group;

/**
 * An offline player. This is an offline version of a registered player.
 * OfflinePlayer contains information about groups, permissions, world and
 * location where the player has logged out, according to data on disk.
 *
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public interface OfflinePlayer {
    /**
     * Get the permission provider that is responsible for the player
     *
     * @return PermissionProvider
     */
    public PermissionProvider getPermissionProvider();

    /**
     * Get this players main group
     *
     * @return {@link Group}
     */
    public Group getGroup();

    /**
     * get this players prefix
     *
     * @return {@link String}
     */
    public String getPrefix();

    /**
     * Check if there is an online player for this offline player
     *
     * @return {@code true} if online; {@code false} otherwise
     */
    public boolean isOnline();

    /**
     * Get the NBT tag for this offline player
     *
     * @return {@link CompoundTag}
     */
    public CompoundTag getNBT();

    /**
     * Save changes made to this offline player.
     * <b>Important:</b> This will only apply if the player is not online!
     */
    public void save();

    /**
     * Check if this player has the given permission
     *
     * @param path
     *         the permission path
     *
     * @return {@code true} if has permission; {@code false} otherwise
     */
    public boolean hasPermission(String path);

    /**
     * Set this players group
     *
     * @param group
     *         the {@link Group} to set
     */
    public void setGroup(Group group);

    /**
     * Add a group to this players sub groups
     *
     * @param group
     *         the {@link Group} to add
     */
    public void addGroup(Group group);

    /**
     * Remove this group from the player.<br>
     * You can NOT delete the default group.
     *
     * @param g
     *         the {@link Group} to remove
     *
     * @return {@code true} if successful; {@code false} otherwise
     */
    public boolean removeGroup(Group g);

    /**
     * Remove a group with this name from the player.<br>
     * You can NOT delete the default group.
     *
     * @param g
     *         the name of the {@link Group} to remove
     *
     * @return {@code true} if successful; {@code false} otherwise
     */
    public boolean removeGroup(String g);

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
     * Set this players prefix
     *
     * @param prefix
     *         the prefix to be set
     */
    public void setPrefix(String prefix);

    /**
     * get the world the player logged off in
     *
     * @return {@link World}
     */
    public World getWorld();

    /**
     * get the position the player logged off at
     *
     * @return {@link Position}
     */
    public Position getPosition();

    /**
     * Get this offline players name
     *
     * @return name
     */
    public String getName();

    /**
     * Check if this player is muted
     *
     * @return {@code true} if muted; {@code false} otherwise
     */
    public boolean isMuted();

    /**
     * Mute or unmute this offline player
     *
     * @param muted
     *         {@code true} to mute; {@code false} to unmute
     */
    public void setMuted(boolean muted);

    /**
     * Get all of this players groups
     *
     * @return Group array
     */
    public Group[] getPlayerGroups();

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

}
