package net.canarymod.permissionsystem;


import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.backbone.BackbonePermissions;
import net.canarymod.user.Group;


/**
 * This handles the loading of permission providers for groups and players
 * @author Chris Ksoll
 *
 */
public class PermissionManager {
    BackbonePermissions backbone;
    public PermissionManager() {
        backbone = new BackbonePermissions();
    }

    /**
     * Load the permission set for the group with the given name.
     * @param name
     * @return
     */
    public PermissionProvider getGroupsProvider(String name) {
        return backbone.loadGroupPermissions(name);
    }

    /**
     * Load the permission set for the group with te given name.
     * @param name
     * @return
     */
    public PermissionProvider getPlayerProvider(String name) {
        return backbone.loadPlayerPermissions(name);
    }

    public void savePermissionsFromGroup(Group g) {
        backbone.saveGroupPermissions(g);
    }

    public void savePermissionsFromPlayer(Player p) {
        backbone.saveUserPermissions(p);
    }

    public void removePermissions(String path) {
        backbone.removePermission(path);
    }

    public void removePlayerPermission(String path, Player player) {
        backbone.removePermission(path, player.getName(), true);
        player.getPermissionProvider().reload();
    }

    public void removeGroupPermission(String path, Group group) {
        backbone.removePermission(path, group.getName(), false);
        group.getPermissionProvider().reload();

    }

    public int addPermission(String path, boolean value, String owner, String type) {
        return backbone.addPermission(path, value, owner, type);
    }

}
