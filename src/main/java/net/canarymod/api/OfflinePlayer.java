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
 *
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
     * @return
     */
    public Group getGroup();

    /**
     * get this players prefix
     * @return
     */
    public String getPrefix();

    /**
     * Check if there is an online player for this offline player
     * @return
     */
    public boolean isOnline();

    /**
     * Get the NBT tag for this offline player
     * @return
     */
    public CompoundTag getNBT();

    /**
     * Save changes made to this offline player.
     * <b>Important:</b> This will only apply if the player is not online!
     */
    public void save();

    /**
     * Check if this player has the given permission
     * @param path
     * @return
     */
    public boolean hasPermission(String path);

    /**
     * Set this players group
     * @param group
     */
    public void setGroup(Group group);

    /**
     * Add a group to this players sub groups
     * @param group
     */
    public void addGroup(Group group);

    /**
     * Remove this group from the player.<br>
     * You can NOT delete the default group.
     * @param group
     * @return
     */
    public boolean removeGroup(Group g);

    /**
     * Remove a group with this name from the player.<br>
     * You can NOT delete the default group.
     * @param group
     * @return
     */
    public boolean removeGroup(String g);

    /**
     * Set this players prefix
     * @param prefix
     */
    public void setPrefix(String prefix);

    /**
     * get the world the player logged off in
     * @return
     */
    public World getWorld();

    /**
     * get the position the player logged off at
     * @return
     */
    public Position getPosition();

    /**
     * Get this offline players name
     * @return
     */
    public String getName();

    /**
     * Check if this player is muted
     * @return
     */
    public boolean isMuted();

    /**
     * Mute or unmute this offline player
     * @param muted
     */
    public void setMuted(boolean muted);

    /**
     * Get all of this players groups
     * @return
     */
    public Group[] getPlayerGroups();

}
