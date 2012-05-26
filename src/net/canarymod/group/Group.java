package net.canarymod.group;

import java.util.ArrayList;

import net.canarymod.permissionsystem.PermissionProvider;

/**
 * Represents a player group
 * 
 * @author Chris
 * 
 */
public class Group {

    /**
     * ID for retrieving permissions from the group-permission relation table
     */
    public int id;
    /**
     * Group Name
     */
    public String name;

    /**
     * Group Prefix/Color
     */
    public String prefix = "f";

    /**
     * The permission provider for querying permissions etc.
     */
    public PermissionProvider permissions;

    /**
     * List of groups this group inherits/has control over
     */
    public ArrayList<Group> childGroups = new ArrayList<Group>();
    
    /**
     * The parent group (the group this group is a child of).
     * Parents have control over their childs
     */
    public Group parent = null;

    /**
     * Is true if it's the default group
     */
    public boolean defaultGroup = false;

    /**
     * If true all players within this group ignore restrictions
     */
    public boolean ignoreRestrictions;

    /**
     * If true all players within this group have administrator privileges
     */
    public boolean administrator = false;

    /**
     * If false this player can not modify chests or furnaces and can not
     * destroy/create blocks
     */
    public boolean canModifyWorld = true;

    /**
     * Check if this group can ignore restrictions
     * @return
     */
    public boolean canIgnorerestrictions() {
        return permissions.queryPermission("canary.groups.canIgnoreRestrictions");
    }
    
    /**
     * Check if this group is an administrative groups
     * @return
     */
    public boolean isAdministratorGroup() {
        return permissions.queryPermission("canary.groups.administrator");
    }
    
    public boolean canModifyWorld() {
        return permissions.queryPermission("canary.groups.canModifyWorld");
    }
    /**
     * Check if this group has control over the given group, specifically, check
     * if the given group is a child of this group, or if this group is admin or
     * can ignore restrictions.<br>
     * 
     * @param g
     * @return
     */
    public boolean hasControlOver(Group g) {
        if (administrator || ignoreRestrictions) {
            return true;
        }
        if (this.name.equals(g.name)) {
            return true;
        }
        for (Group gr : childsToList()) {
            if (gr.name.equals(g.name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if this group has control over a group with the name given as String
     * @param gr
     * @return
     */
    public boolean hasControlOver(String gr) {
        if (administrator || ignoreRestrictions) {
            return true;
        }
        if (this.name.equals(gr)) {
            return true;
        }
        for (Group g : childsToList()) {
            if (g.name.equals(gr)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks in this group and its's parent (and the parent of the parent etc etc) if it has permission
     * and if the value is true. The first found "true" will be returned,
     * false if there was no "true" or the node had false as value (ie. this group does not have this permission)
     * @return
     */
    public boolean hasPermission(String permission) {
        boolean finalResult = false;
        for(Group g : parentsToList()) {
            finalResult = g.permissions.queryPermission(permission);
            if(finalResult) {
                return true;
            }
        }
        return finalResult;
    }
    
    public ArrayList<Group> childsToList() {
        ArrayList<Group> list = new ArrayList<Group>();
        walkChilds(list, this);
        return list;
    }
    
    /**
     * Returns all the parents from this group upwards
     * @return
     */
    public ArrayList<Group> parentsToList() {
        ArrayList<Group> parents = new ArrayList<Group>();
        walkParents(parents, this);
        return null;
    }
    
    private void walkParents(ArrayList<Group> list, Group group) {
        if(group.parent == null) {
            return; //Found topmost group
        }
        list.add(group.parent);
        walkParents(list, group.parent);
    }
    private void walkChilds(ArrayList<Group> list, Group group) {
        list.add(group);
        for(Group g : group.childGroups) {
            walkChilds(list, g);
        }
    }
}
