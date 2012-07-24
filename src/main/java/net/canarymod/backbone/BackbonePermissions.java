package net.canarymod.backbone;

import java.util.ArrayList;
import net.canarymod.Canary;
import net.canarymod.api.entity.Player;
import net.canarymod.database.DatabaseRow;
import net.canarymod.database.DatabaseTable;
import net.canarymod.permissionsystem.PermissionNode;
import net.canarymod.permissionsystem.PermissionProvider;
import net.canarymod.user.Group;

/**
 * Backbone to the permissions System. This contains NO logic, it is only the
 * data source access!
 * 
 * @author Chris Ksoll
 * 
 */
public class BackbonePermissions extends Backbone {
    
    public BackbonePermissions() {
        super(Backbone.System.PERMISSIONS);
        createTables();
    }
    
    /**
     * Load permissions for a group
     * @param name
     * @return
     */
    public PermissionProvider loadGroupPermissions(String name) {
        DatabaseRow[] permissions = Canary.db().getRelatedRows("permissions", "groups", "name", "pnid", "name", name);
        PermissionProvider provider = new PermissionProvider();
        if(permissions == null) {
//            java.lang.System.out.println("permissions null for groups!");
            return provider;
        }
        for(DatabaseRow row : permissions) {
            provider.addPermission(row.getStringCell("path"), Boolean.parseBoolean(row.getStringCell("value")), Integer.parseInt(row.getStringCell("pnid")));
        }
        provider.setOwner(name);
        provider.setType(false);
        return provider;
    }
    
    /**
     * Load permissions for a player
     * @param name
     * @return
     */
    public PermissionProvider loadPlayerPermissions(String name) {
        DatabaseRow[] permissions = Canary.db().getRelatedRows("permissions", "users", "username", "pnid", "username", name);
        PermissionProvider provider = new PermissionProvider();
        if(permissions == null) {
//            java.lang.System.out.println("permissions null for players!");
            return provider;
        }
        for(DatabaseRow row : permissions) {
            provider.addPermission(row.getStringCell("path"), row.getBooleanCell("value"),row.getIntCell("pnid"));
        }
        provider.setOwner(name);
        provider.setType(true);
        return provider;
    }
    
    /**
     * Saves group permissions. This also adds new permissions + relations if there are any and
     * and updates existing ones
     * @param g
     */
    public void saveGroupPermissions(Group g) {
        Canary.db().prepare();
        PermissionProvider permissions = g.permissions;
        ArrayList<PermissionNode> permissionList = permissions.getPermissionMap();
        DatabaseRow[] permission = Canary.db().getTable("permissions").getAllRows();
        DatabaseTable permissionTable = Canary.db().getTable("permissions");
        DatabaseTable relation = Canary.db().getTable("permissions_groups_rel");
        for(PermissionNode node : permissionList) {
            ArrayList<PermissionNode> childs = new ArrayList<PermissionNode>(); 
            for(PermissionNode child : permissions.getChildNodes(node, childs)) {
                
                for(DatabaseRow prow : permission) {
                    
                    DatabaseRow[] tmp = permissionTable.getFilteredRows("pnId", String.valueOf(child.getId()));
                    if(tmp == null || tmp.length == 0 ) {
                        //new permission
                        DatabaseRow newRow = permissionTable.addRow();
                        newRow.setStringCell("path", child.getFullPath());
                        newRow.setBooleanCell("value", child.getValue());
                        DatabaseRow newRelation = relation.addRow();
                        newRelation.setStringCell("name", g.name);
                        newRelation.setIntCell("pnId", newRelation.getIntCell("id")); 
                        continue;
                    }
                    
                    if(child.getId() == prow.getIntCell("pnId") && child.getId() != -1) {
                        if(child.getValue() != prow.getBooleanCell("pnId")) { //only update if stuff has changed
                            prow.setBooleanCell("value", child.getValue());
                        }
                    }
                }
            }
        }
        Canary.db().execute();
    }
    
    /**
     * Save user permissions to file and add new ones if needed + update relations
     * @param p
     */
    public void saveUserPermissions(Player p) {
        Canary.db().prepare();
        PermissionProvider permissions = p.getPermissionProvider();
        ArrayList<PermissionNode> permissionList = permissions.getPermissionMap();
        DatabaseRow[] permission = Canary.db().getTable("permissions").getAllRows();
        DatabaseTable permissionTable = Canary.db().getTable("permissions");
        DatabaseTable relation = Canary.db().getTable("permissions_users_rel");
        for(PermissionNode node : permissionList) {
            ArrayList<PermissionNode> childs = new ArrayList<PermissionNode>(); 
            for(PermissionNode child : permissions.getChildNodes(node, childs)) {
                
                for(DatabaseRow prow : permission) {
                    
                    DatabaseRow[] tmp = permissionTable.getFilteredRows("pnId", String.valueOf(child.getId()));
                    if(tmp == null || tmp.length == 0 ) {
                        //new permission
                        DatabaseRow newRow = permissionTable.addRow();
                        newRow.setStringCell("path", child.getFullPath());
                        newRow.setBooleanCell("value", child.getValue());
                        DatabaseRow newRelation = relation.addRow();
                        newRelation.setStringCell("name", p.getName());
                        newRelation.setIntCell("pnId", newRelation.getIntCell("id")); 
                        continue;
                    }
                    
                    if(child.getId() == prow.getIntCell("pnId") && child.getId() != -1) {
                        if(child.getValue() != prow.getBooleanCell("pnId")) { //only update if stuff has changed
                            prow.setBooleanCell("value", child.getValue());
                        }
                    }
                }
            }
        }
        Canary.db().execute();
    }
    
    /**
     * Remove a permission from database. This also removes any relations to groups and players
     * @param path
     */
    public void removePermission(String path) {
        Canary.db().prepare();
        DatabaseTable permissionTable = Canary.db().getTable("permissions");
        DatabaseRow[] rs = permissionTable.getFilteredRows("path", path);
        DatabaseTable groupRel = Canary.db().getTable("permissions_groups_rel");
        DatabaseTable userRel = Canary.db().getTable("permissions_users_rel");
        
        for(DatabaseRow row : rs) {
          //Remove relations to groups
            DatabaseRow[] groupRelations = Canary.db().getTable("permissions_groups_rel").getFilteredRows("pnid", String.valueOf(row.getIntCell("pnid")));
            for(DatabaseRow rel : groupRelations) {
                groupRel.removeRow(rel);
            }
            
            //Remove relations to users
            DatabaseRow[] userRelations = Canary.db().getTable("permissions_users_rel").getFilteredRows("pnid", String.valueOf(row.getIntCell("pnid")));
            for(DatabaseRow rel : userRelations) {
                userRel.removeRow(rel);
            }
            
            //remove from the actual permission table
            permissionTable.removeRow(row);
        }
        Canary.db().execute();
    }
    
    public void removeRelationFromUser(String name) {
        Canary.db().prepare();
        DatabaseTable userRel = Canary.db().getTable("permissions_users_rel");
        DatabaseRow[] result = userRel.getFilteredRows("name", name);
        for(DatabaseRow row : result) {
            userRel.removeRow(row);
        }
        Canary.db().execute();
    }
    
    public void removeRelationFromGroup(String name) {
        Canary.db().prepare();
        DatabaseTable userRel = Canary.db().getTable("permissions_groups_rel");
        DatabaseRow[] result = userRel.getFilteredRows("name", name);
        for(DatabaseRow row : result) {
            userRel.removeRow(row);
        }
        Canary.db().execute();
    }
    
    /**
     * Add a new Permission to database and return its proper object.
     * If the permission already exists, it will return the existing permission node
     * @param path
     * @param value
     * @return
     */
    public int addPermission(String path, boolean value) {
        Canary.db().prepare();
        DatabaseRow[] result = Canary.db().getTable("permissions").getFilteredRows("path", path);
        if(result == null || result.length == 0) {
            DatabaseRow newEntry = Canary.db().getTable("permissions").addRow();
            newEntry.setStringCell("path", path);
            newEntry.setBooleanCell("value", value);
            Canary.db().execute();
            return newEntry.getIntCell("pnid");
        }
        else {
            DatabaseRow n = result[0];
            n.setBooleanCell("value", value);
            Canary.db().execute();
            return n.getIntCell("pnid");
        }
        
    }
    
    /**
     * Create the tables for permissions
     */
    private void createTables() {
        if(Canary.db().getTable("permissions") != null) return;
        
        Canary.db().prepare();
        DatabaseTable table = Canary.db().addTable("permissions");
        table.appendColumn("pnid", DatabaseTable.ColumnType.INTEGER);
        table.appendColumn("path", DatabaseTable.ColumnType.STRING);
        table.appendColumn("value", DatabaseTable.ColumnType.BOOLEAN);
        Canary.db().execute();
    }
}
