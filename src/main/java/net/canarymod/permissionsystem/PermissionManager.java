package net.canarymod.permissionsystem;

import net.canarymod.api.entity.Player;
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
    
    /**
     * Remove all permission relations for the user with the given name
     * @param name
     */
    public void removeRelationsFromUser(String name) {
        backbone.removeRelationFromUser(name);
    }
    
    /**
     * Remove all permission relations for the group with the given name
     * @param name
     */
    public void removeRelationsFromGroup(String name) {
        backbone.removeRelationFromGroup(name);
    }
    
    public int addPermission(String path, boolean value) {
        return backbone.addPermission(path, value);
    }
    
}
