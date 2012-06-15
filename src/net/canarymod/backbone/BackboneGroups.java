package net.canarymod.backbone;

import java.util.ArrayList;

import net.canarymod.Canary;
import net.canarymod.database.DatabaseRow;
import net.canarymod.database.DatabaseTable;
import net.canarymod.user.Group;

/**
 * Backbone to the ban System. This contains NO logic, it is only the data
 * source access!
 * 
 * @author Chris
 * 
 */
public class BackboneGroups extends Backbone {

    public BackboneGroups() {
        super(Backbone.System.GROUPS);
    }
    /**
     * Add a new Group to the list of Groups.
     * 
     * @param Group
     */
    public void addGroup(Group group) {
        if(groupExists(group)) {
            updateGroup(group);
            return;
        }
        DatabaseRow newData = getTable().addRow();
        
        newData.setStringCell("name", group.name);
        newData.setStringCell("prefix", group.prefix);
        newData.setStringCell("parent", group.parent.name);
        newData.setBooleanCell("isDefaultGroup", group.defaultGroup);
    }

    private boolean groupExists(Group group) {
        DatabaseRow[] newData = getTable().getFilteredRows("name", group.name);
        if(newData != null && newData.length > 0) {
            return true;
        }
        return false;
    }
    /**
     * Remove a group from the data source
     * 
     * @param group
     */
    public void removeGroup(Group group) {
        DatabaseTable table = getTable();
        DatabaseRow[] newData = table.getFilteredRows("name", group.name);
        if(newData != null && newData.length > 0) {
            for(DatabaseRow row : newData) {
                table.removeRow(row);
                Canary.permissionManager().removeRelationsFromUser(group.name);
            }
        }
    }

    /**
     * Update a Group.
     * 
     * @param Group
     */
    public void updateGroup(Group group) {
        DatabaseRow[] newData = getTable().getFilteredRows("name", group.name);
        if(newData != null && newData.length == 1) {
            DatabaseRow row = newData[0];
            if(!row.getStringCell("name").equals(group.name)) {
                for(Group g : group.childGroups) {
                    g.parent = group;
                    row.setStringCell("name", group.name);
                    updateGroup(g);
                }
            }
            row.setStringCell("prefix", group.prefix);
            row.setStringCell("parent", group.parent.name);
            row.setBooleanCell("isDefaultGroup", group.defaultGroup);
        }
    }
    
    private Group loadParents(String parent, ArrayList<Group> existingGroups) {
        DatabaseRow[] groupRows = getTable().getFilteredRows("name", parent);
        //Check if we have that group already
        for(Group g : existingGroups) {
            if(g.name.equals(parent)) {
                return g;
            }
        }
        if(groupRows != null && groupRows.length == 1) {
            for(DatabaseRow row : groupRows) {
                Group group = new Group();
                group.id = row.getIntCell("id");
                group.defaultGroup = row.getBooleanCell("defaultGroup");
                group.name = row.getStringCell("name");
                group.parent = loadParents(row.getStringCell("parent"), existingGroups);
                group.prefix = row.getStringCell("prefix");
                existingGroups.add(group);
                return group;
            }
        }
        return null;
    }
    
    /**
     * Check if group with this name is already in the list.
     * That can happen because the list gets filled by 2 methods, 
     * @param name
     * @param list
     * @return
     */
    private boolean alreadyInList(String name, ArrayList<Group> list) {
        for(Group g : list) {
            if(g.name.equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Load and return all recorded groups
     * 
     * @return
     */
    public ArrayList<Group> loadGroups() {
        ArrayList<Group> groups = new ArrayList<Group>();
        DatabaseRow[] groupRows = getTable().getAllRows();
        if(groupRows != null && groupRows.length > 0) {
            for(DatabaseRow row : groupRows) {
                Group group = new Group();
                group.id = row.getIntCell("id");
                group.defaultGroup = row.getBooleanCell("isDefault");
                group.name = row.getStringCell("name");
                group.parent = loadParents(row.getStringCell("parent"), groups);
                group.prefix = row.getStringCell("prefix");
                if(!alreadyInList(group.name, groups)) {
                    groups.add(group);
                }
            }
        }
        
        //Sort out child relations
        for(Group current : groups) {
            for( Group g : groups) {
                if(g.parent != null && g.parent.name.equals(current.name)) {
                    current.childGroups.add(g);
                }
            }
        }
        return groups;
    }
    
    /**
     * Get the users table. If the table does not exist, create it with the relation table
     * @return
     */
    private DatabaseTable getTable() {
        DatabaseTable table = Canary.db().getTable("groups");
        if(table == null) {
            Canary.db().prepare();
            table = Canary.db().addTable("groups");
            table.appendColumn("id", DatabaseTable.ColumnType.INTEGER);
            table.appendColumn("name", DatabaseTable.ColumnType.STRING);
            table.appendColumn("parent", DatabaseTable.ColumnType.STRING);
            table.appendColumn("prefix", DatabaseTable.ColumnType.STRING);
            table.appendColumn("isdefault", DatabaseTable.ColumnType.BOOLEAN);
            
            DatabaseRow row = table.addRow();
            row.setIntCell("id",1);
            row.setStringCell("name","players");
            row.setBooleanCell("isdefault",true);
            
            DatabaseTable relTable = Canary.db().addTable("permissions_groups_rel");
            relTable.appendColumn("pnid", DatabaseTable.ColumnType.INTEGER);
            relTable.appendColumn("name", DatabaseTable.ColumnType.STRING);
            Canary.db().execute();
        }
        
        return table;
    }
}
