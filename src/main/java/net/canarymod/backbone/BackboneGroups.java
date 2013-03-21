package net.canarymod.backbone;

import java.util.ArrayList;

import net.canarymod.Logman;
import net.canarymod.database.DataAccess;
import net.canarymod.database.Database;
import net.canarymod.database.DatabaseReadException;
import net.canarymod.database.DatabaseWriteException;
import net.canarymod.user.Group;

/**
 * Backbone to the groups System. This contains NO logic, it is only the data
 * source access!
 * 
 * @author Chris
 * 
 */
public class BackboneGroups extends Backbone {

    public BackboneGroups() {
        super(Backbone.System.GROUPS);
    }
    
    public String stringToNull(String test) {
        if(test == null) {
            return null;
        }
        if(test.equalsIgnoreCase("null")) {
            return null;
        }
        return test;
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
        GroupAccess data = new GroupAccess();
        
        data.isDefault = group.isDefaultGroup();
        data.permissionNodes = group.getPermissionProvider().getPermissionsAsStringList();
        data.prefix = group.getPrefix();
        data.name = group.getName();
        if(group.hasParent()) {
            data.parent = group.getParent().getName();
        }
        try {
            Database.get().insert(data);
        } catch (DatabaseWriteException e) {
            Logman.logStackTrace(e.getMessage(), e);
        }
    }

    private boolean groupExists(Group group) {
        GroupAccess data = new GroupAccess();
        try {
            Database.get().load(data, new String[]{"name"}, new Object[] {group.getName()});
        } catch (DatabaseReadException e) {
            Logman.logStackTrace(e.getMessage(), e);
        }
        
        return data.hasData();
    }
    /**
     * Remove a group from the data source
     * 
     * @param group
     */
    public void removeGroup(Group group) {
        try {
            Database.get().remove("group", new String[]{"name"}, new Object[] {group.getName()});
            Database.get().remove("permission", new String[] {"owner", "type"}, new Object[] {group.getName(), "group"});
        } catch (DatabaseWriteException e) {
            Logman.logStackTrace(e.getMessage(), e);
        }
        
    }

    /**
     * Update a Group.
     * 
     * @param Group
     */
    public void updateGroup(Group group) {
        if(!groupExists(group)) {
            Logman.logWarning("Group " + group.getName() + " was not updated, it does not exist!");
            return;
        }
        GroupAccess updatedData = new GroupAccess();
        
        updatedData.isDefault = group.isDefaultGroup();
        updatedData.permissionNodes = group.getPermissionProvider().getPermissionsAsStringList();
        updatedData.prefix = group.getPrefix();
        updatedData.name = group.getName();
        if(group.hasParent()) {
            updatedData.parent = group.getParent().getName();
            for(Group g : group.getChildren()) {
                updateGroup(g);
            }
        }
        try {
            Database.get().update(updatedData, new String[] {"name"}, new Object[] {group.getName()});
        } catch (DatabaseWriteException e) {
            Logman.logStackTrace(e.getMessage(), e);
        }
    }
    
    private Group loadParents(String parent, ArrayList<Group> existingGroups) {
        if(parent == null) {
            return null;
        }
        for(Group g : existingGroups) {
            if(g.getName().equals(parent)) {
                return g;
            }
        }
        GroupAccess data = new GroupAccess();
        try {
            Database.get().load(data, new String[] {"name"}, new Object[] {parent});
        } catch (DatabaseReadException e) {
            Logman.logStackTrace(e.getMessage(), e);
        }
        if(data.hasData()) {
            Group g = new Group();
            g.setDefaultGroup(data.isDefault);
            g.setId(data.id);
            g.setName(data.name);
            g.setParent(loadParents(data.parent, existingGroups));
            g.setPrefix(data.prefix);
            return g;
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
            if(g.getName().equals(name)) {
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
        ArrayList<DataAccess> dataList = new ArrayList<DataAccess>();
        ArrayList<Group> groups = new ArrayList<Group>();
        
        try {
            Database.get().loadAll(new GroupAccess(), dataList, new String[]{}, new Object[]{});
            for(DataAccess da: dataList) {
                GroupAccess data = (GroupAccess) da;
                if(alreadyInList(data.name, groups)) {
                    continue;
                }
                Group g = new Group();
                g.setDefaultGroup(data.isDefault);
                g.setId(data.id);
                g.setName(data.name);
                g.setParent(loadParents(data.parent, groups));
                g.setPrefix(data.prefix);
                groups.add(g);
            }
        } 
        catch (DatabaseReadException e) {
            Logman.logStackTrace(e.getMessage(), e);
        }
        
        return groups;
    }
    
}
