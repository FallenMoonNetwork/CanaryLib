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
     *            the permission path
     * @return {@code true} if has permission; {@code false} otherwise
     */
    public boolean hasPermission(String path);

    /**
     * Set this players group
     * 
     * @param group
     */
    public void setGroup(Group group);

    /**
     * Add a group to this players sub groups
     * 
     * @param group
     */
    public void addGroup(Group group);

    /**
     * Remove this group from the player.<br>
     * You can NOT delete the default group.
     * 
     * @param group
     * @return {@code true} if successful; {@code false} otherwise
     */
    public boolean removeGroup(Group g);

    /**
     * Remove a group with this name from the player.<br>
     * You can NOT delete the default group.
     * 
     * @param group
     * @return {@code true} if successful; {@code false} otherwise
     */
    public boolean removeGroup(String g);

    /**
     * Set this players prefix
     * 
     * @param prefix
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
     */
    public void setMuted(boolean muted);

    /**
     * Get all of this players groups
     * 
     * @return Group array
     */
    public Group[] getPlayerGroups();

}
