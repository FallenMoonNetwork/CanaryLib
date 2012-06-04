package net.canarymod.permissionsystem;

import net.canarymod.backbone.BackbonePermissions;
import net.canarymod.config.Configuration;
import net.canarymod.group.Group;

/**
 * This handles the loading of permission providers for groups and players
 * @author Chris Ksoll
 *
 */
public class PermissionLoader {
    BackbonePermissions backbone;
    public PermissionLoader() {
        backbone = new BackbonePermissions(Configuration.getServerConfig().getDatasourceType());
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
        
    }
    
    
}
