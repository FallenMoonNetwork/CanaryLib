package net.canarymod.backbone;

import java.util.HashMap;

import net.canarymod.Canary;
import net.canarymod.database.Database;
import net.canarymod.database.DatabaseRow;
import net.canarymod.database.DatabaseTable;
import net.canarymod.group.Group;
import net.canarymod.permissionsystem.PermissionNode;
import net.canarymod.permissionsystem.PermissionProvider;

/**
 * Backbone to the permissions System. This contains NO logic, it is only the
 * data source access!
 * 
 * @author Jos
 * 
 */
public class BackbonePermissions extends Backbone {
    
    public BackbonePermissions(Database.Type type) {
        super(Backbone.System.PERMISSIONS, type);
    }
    
    public PermissionProvider loadGroupPermissions(String name) {
        DatabaseRow[] permissions = Canary.db().getRelatedRows("groups", "permissions", "groupId", "pnid", "name", name);
        PermissionProvider provider = new PermissionProvider();
        for(DatabaseRow row : permissions) {
            provider.addPermission(row.getStringCell("path"), row.getBooleanCell("value"), false, row.getIntCell("pnid"));
        }
        return provider;
    }
    
    public PermissionProvider loadPlayerPermissions(String name) {
        DatabaseRow[] permissions = Canary.db().getRelatedRows("users", "permissions", "userId", "pnid", "name", name);
        PermissionProvider provider = new PermissionProvider();
        for(DatabaseRow row : permissions) {
            provider.addPermission(row.getStringCell("path"), row.getBooleanCell("value"), false,row.getIntCell("pnid"));
        }
        return provider;
    }
    
    public void saveGroupPermissions(Group g) {
        PermissionProvider permissions = g.permissions;
        HashMap<String,PermissionNode> permissionList = permissions.getPermissionMap();
        for(String key : permissionList.keySet()) {
            DatabaseTable permissionTable = Canary.db().getTable("permissions");
            DatabaseTable groups = Canary.db().getTable("groups");
            
        }
    }

}
