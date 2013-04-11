package net.canarymod.api;

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
     * Get this players active group
     * @return
     */
    public Group getGroup();

    /**
     * get this players prefix
     * @return
     */
    public String getPrefix();

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
     * Mute or unmite this offline player
     * @param muted
     */
    public void setMuted(boolean muted);

}
