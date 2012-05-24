package net.canarymod.backbone;

import java.util.ArrayList;

import net.canarymod.database.Database;
import net.canarymod.database.DatabaseRow;
import net.canarymod.database.DatabaseTable;
import net.canarymod.group.Group;

/**
 * Backbone to the ban System. This contains NO logic, it is only the data
 * source access!
 * 
 * @author Chris
 * 
 */
public class BackboneGroups extends Backbone {

    Database db;
    
    public BackboneGroups(Database database, Database.Type type) {
        super(Backbone.System.GROUPS, type);
        db = database;
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
        DatabaseTable table = db.getTable("groups");
        DatabaseRow newData = table.addRow();
        
        newData.setStringCell("name", group.name);
        newData.setStringCell("prefix", group.prefix);
        newData.setStringCell("parent", group.parent.name);
        newData.setBooleanCell("administrator", group.administrator);
        newData.setBooleanCell("ignoreRestrictions", group.ignoreRestrictions);
        newData.setBooleanCell("isDefaultGroup", group.defaultGroup);
        newData.setBooleanCell("canModifyWorld", group.canModifyWorld);
    }

    private boolean groupExists(Group group) {
        DatabaseTable table = db.getTable("groups");
        DatabaseRow[] newData = table.getFilteredRows("name", group.name);
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
        DatabaseTable table = db.getTable("groups");
        DatabaseRow[] newData = table.getFilteredRows("name", group.name);
        if(newData != null && newData.length > 0) {
            for(DatabaseRow row : newData) {
                table.removeRow(row);
            }
        }
    }

    /**
     * Update a Group.
     * 
     * @param Group
     */
    public void updateGroup(Group group) {
        DatabaseTable table = db.getTable("groups");
        DatabaseRow[] newData = table.getFilteredRows("name", group.name);
        if(newData != null && newData.length == 1) {
            DatabaseRow row = newData[0];
            if(row.getStringCell("name").hashCode() != group.name.hashCode()) {
                for(Group g : group.childGroups) {
                    g.parent = group;
                    row.setStringCell("name", group.name);
                    updateGroup(g);
                }
            }
            row.setStringCell("prefix", group.prefix);
            row.setStringCell("parent", group.parent.name);
            row.setBooleanCell("administrator", group.administrator);
            row.setBooleanCell("ignoreRestrictions", group.ignoreRestrictions);
            row.setBooleanCell("isDefaultGroup", group.defaultGroup);
            row.setBooleanCell("canModifyWorld", group.canModifyWorld);
        }
    }
    
    private Group loadParents(String parent, ArrayList<Group> existingGroups) {
        DatabaseTable table = db.getTable("groups");
        DatabaseRow[] groupRows = table.getFilteredRows("name", parent);
        //Check if we have that group already
        for(Group g : existingGroups) {
            if(g.name.equals(parent)) {
                return g;
            }
        }
        if(groupRows != null && groupRows.length == 1) {
            for(DatabaseRow row : groupRows) {
                Group group = new Group();
                group.administrator = row.getBooleanCell("administrator");
                group.canModifyWorld = row.getBooleanCell("canModifyWorld");
                group.defaultGroup = row.getBooleanCell("defaultGroup");
                group.ignoreRestrictions = row.getBooleanCell("ignoreRestrictions");
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
        DatabaseTable table = db.getTable("groups");
        DatabaseRow[] groupRows = table.getAllRows();
        if(groupRows != null && groupRows.length > 0) {
            for(DatabaseRow row : groupRows) {
                Group group = new Group();
                group.administrator = row.getBooleanCell("administrator");
                group.canModifyWorld = row.getBooleanCell("canModifyWorld");
                group.defaultGroup = row.getBooleanCell("defaultGroup");
                group.ignoreRestrictions = row.getBooleanCell("ignoreRestrictions");
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
    
    
}
