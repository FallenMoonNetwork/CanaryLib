package net.canarymod.permissionsystem;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.backbone.BackbonePermissions;
import net.canarymod.user.Group;

/**
 * This handles the loading of permission providers for groups and players
 *
 * @author Chris (damagefilter)
 */
public class PermissionManager {
    BackbonePermissions backbone;

    public PermissionManager() {
        backbone = new BackbonePermissions();
    }

    /**
     * Load the permission set for the group with the given name.
     *
     * @param name
     * @param worldname
     *         The Fully qualified name of the world to load permissions for
     *
     * @return {@link PermissionProvider}
     */
    public PermissionProvider getGroupsProvider(String name, String worldname) {
        return backbone.loadGroupPermissions(name, worldname);
    }

    /**
     * Load the permission set for the group with the given name.
     *
     * @param name
     * @param worldname
     *         The Fully qualified name of the world to load permissions for
     *
     * @return {@link PermissionProvider}
     */
    public PermissionProvider getPlayerProvider(String name, String worldname) {
        return backbone.loadPlayerPermissions(name, worldname);
    }

    public void savePermissionsFromGroup(Group g) {
        backbone.saveGroupPermissions(g);
    }

    public void savePermissionsFromPlayer(Player p) {
        backbone.saveUserPermissions(p);
    }

    public void removePermissions(String path, String world) {
        backbone.removePermission(path, world);
    }

    public void removePlayerPermission(String path, Player player) {
        backbone.removePermission(path, player.getName(), player.getWorld().getFqName(), true);
        removePlayerPermission(path, player.getName(), player.getWorld().getFqName());
        player.getPermissionProvider().reload();
    }

    public void removeGroupPermission(String path, Group group) {
        backbone.removePermission(path, group.getName(), group.getWorldName(), false);
        group.getPermissionProvider().reload();

    }

    public int addPermission(String path, boolean value, String owner, String type, String world) {
        return backbone.addPermission(path, value, owner, type, world);
    }

    public void removePlayerPermission(String path, String player, String world) {
        backbone.removePermission(path, player, world, true);
    }

}
