package net.canarymod.backbone;

import java.util.HashMap;

import net.canarymod.Canary;
import net.canarymod.api.entity.Player;
import net.canarymod.database.Database;
import net.canarymod.database.DatabaseRow;
import net.canarymod.database.DatabaseTable;
import net.canarymod.permissionsystem.PermissionNode;
import net.canarymod.permissionsystem.PermissionProvider;
import net.canarymod.user.Group;

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
    
    /**
     * Load permissions for a group
     * @param name
     * @return
     */
    public PermissionProvider loadGroupPermissions(String name) {
        DatabaseRow[] permissions = Canary.db().getRelatedRows("groups", "permissions", "name", "pnId", "name", name);
        PermissionProvider provider = new PermissionProvider();
        for(DatabaseRow row : permissions) {
            provider.addPermission(row.getStringCell("path"), row.getBooleanCell("value"), row.getIntCell("pnid"));
        }
        return provider;
    }
    
    /**
     * Load permissions for a player
     * @param name
     * @return
     */
    public PermissionProvider loadPlayerPermissions(String name) {
        DatabaseRow[] permissions = Canary.db().getRelatedRows("users", "permissions", "name", "pnId", "name", name);
        PermissionProvider provider = new PermissionProvider();
        for(DatabaseRow row : permissions) {
            provider.addPermission(row.getStringCell("path"), row.getBooleanCell("value"),row.getIntCell("pnid"));
        }
        return provider;
    }
    
    /**
     * Saves group permissions. This also adds new permissions + relations if there are any and
     * and updates existing ones
     * @param g
     */
    public void saveGroupPermissions(Group g) {
        PermissionProvider permissions = g.permissions;
        HashMap<String,PermissionNode> permissionList = permissions.getPermissionMap();
        DatabaseRow[] permission = Canary.db().getTable("permissions").getAllRows();
        DatabaseTable permissionTable = Canary.db().getTable("permissions");
        DatabaseTable relation = Canary.db().getTable("groups_permissions_rel");
        for(String key : permissionList.keySet()) {
            PermissionNode pnode = permissionList.get(key);
            for(DatabaseRow prow : permission) {
                
                if(permissionTable.getFilteredRows("pnId", String.valueOf(pnode.getId())).length == 0) {
                    //new permission
                    DatabaseRow newRow = permissionTable.addRow();
                    newRow.setStringCell("path", pnode.getFullPath());
                    newRow.setBooleanCell("value", pnode.getValue());
                    DatabaseRow newRelation = relation.addRow();
                    newRelation.setStringCell("name", g.name);
                    newRelation.setIntCell("pnId", newRelation.getIntCell("id")); 
                    continue;
                }
                
                if(pnode.getId() == prow.getIntCell("pnId") && pnode.getId() != -1) {
                    if(pnode.getValue() != prow.getBooleanCell("pnId")) { //only update if stuff has changed
                        prow.setBooleanCell("value", pnode.getValue());
                    }
                }
            }
            
        }
    }
    
    /**
     * Save user permissions to file and add new ones if needed + update relations
     * @param p
     */
    public void saveUserPermissions(Player p) {
        PermissionProvider permissions = p.getPermissionProvider();
        HashMap<String,PermissionNode> permissionList = permissions.getPermissionMap();
        DatabaseRow[] permission = Canary.db().getTable("permissions").getAllRows();
        DatabaseTable permissionTable = Canary.db().getTable("permissions");
        DatabaseTable relation = Canary.db().getTable("users_permissions_rel");
        for(String key : permissionList.keySet()) {
            PermissionNode pnode = permissionList.get(key);
            for(DatabaseRow prow : permission) {
                
                if(permissionTable.getFilteredRows("pnId", String.valueOf(pnode.getId())).length == 0) {
                    //new permission
                    DatabaseRow newRow = permissionTable.addRow();
                    newRow.setStringCell("path", pnode.getFullPath());
                    newRow.setBooleanCell("value", pnode.getValue());
                    DatabaseRow newRelation = relation.addRow();
                    
                    newRelation.setStringCell("name", p.getName());
                    newRelation.setIntCell("pnId", newRelation.getIntCell("id")); 
                    continue;
                }
                
                if(pnode.getId() == prow.getIntCell("pnId") && pnode.getId() != -1) {
                    if(pnode.getValue() != prow.getBooleanCell("pnId")) { //only update if stuff has changed
                        prow.setBooleanCell("value", pnode.getValue());
                    }
                }
            }
            
        }
    }
    
    /**
     * Remove a permission from database. This also removes any relations to groups and players
     * @param path
     */
    public void removePermission(String path) {
        DatabaseTable permissionTable = Canary.db().getTable("permissions");
        DatabaseRow[] rs = permissionTable.getFilteredRows("path", path);
        DatabaseTable groupRel = Canary.db().getTable("groups_permissions_rel");
        DatabaseTable userRel = Canary.db().getTable("users_permissions_rel");
        
        for(DatabaseRow row : rs) {
          //Remove relations to groups
            DatabaseRow[] groupRelations = Canary.db().getTable("groups_permissions_rel").getFilteredRows("pnid", String.valueOf(row.getIntCell("pnid")));
            for(DatabaseRow rel : groupRelations) {
                groupRel.removeRow(rel);
            }
            
            //Remove relations to users
            DatabaseRow[] userRelations = Canary.db().getTable("groups_permissions_rel").getFilteredRows("pnid", String.valueOf(row.getIntCell("pnid")));
            for(DatabaseRow rel : userRelations) {
                userRel.removeRow(rel);
            }
            
            //remove from the actual permission table
            permissionTable.removeRow(row);
        }
    }
    
    public void removeRelationFromUser(String name) {
        DatabaseTable userRel = Canary.db().getTable("users_permissions_rel");
        DatabaseRow[] result = userRel.getFilteredRows("name", name);
        for(DatabaseRow row : result) {
            userRel.removeRow(row);
        }
    }
    
    public void removeRelationFromGroup(String name) {
        DatabaseTable userRel = Canary.db().getTable("groups_permissions_rel");
        DatabaseRow[] result = userRel.getFilteredRows("name", name);
        for(DatabaseRow row : result) {
            userRel.removeRow(row);
        }
    }
}
