package net.canarymod.user;


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
     * ID for retrieving permissions from the database
     */
    private int id;

    /**
     * Group Name
     */
    private String name;

    /**
     * Group Prefix/Color
     */
    private String prefix = "f";

    /**
     * The permission provider for querying permissions etc.
     */
    private PermissionProvider permissions;

    /**
     * List of groups this group inherits/has control over
     */
    private ArrayList<Group> childGroups = new ArrayList<Group>();

    /**
     * The parent group (the group this group is a child of).
     * Parents have control over their childs
     */
    private Group parent = null;

    /**
     * Is true if it's the default group
     */
    private boolean defaultGroup = false;

    /**
     * Check if this group can ignore restrictions
     * @return
     */
    public boolean canIgnorerestrictions() {
        return hasPermission("canary.super.ignoreRestrictions");
    }

    /**
     * Check if this group is an administrative groups
     * @return
     */
    public boolean isAdministratorGroup() {
        return hasPermission("canary.super.administrator");
    }

    public boolean canBuild() {
        return hasPermission("canary.world.build");
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
        if (isAdministratorGroup() || canIgnorerestrictions()) {
            return true;
        }
        if (this.name.equals(g.name)) {
            return true;
        }
        for (Group gr : g.childsToList()) {
            if (gr.name.equals(this.name)) {
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

        for (Group g : parentsToList()) {
            finalResult = g.permissions.queryPermission(permission);
            if (finalResult) {
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

        parents.add(this);
        walkParents(parents, this);
        return parents;
    }

    private void walkParents(ArrayList<Group> list, Group group) {
        if (group.parent == null) {
            return; // Found topmost group
        }
        list.add(group.parent);
        walkParents(list, group.parent);
    }

    private void walkChilds(ArrayList<Group> list, Group group) {
        list.add(group);
        for (Group g : group.childGroups) {
            walkChilds(list, g);
        }
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public boolean isDefaultGroup() {
        return defaultGroup;
    }

    public void setDefaultGroup(boolean defaultGroup) {
        this.defaultGroup = defaultGroup;
    }

    public PermissionProvider getPermissionProvider() {
        return permissions;
    }

    public void setPermissionProvider(PermissionProvider provider) {
        this.permissions = provider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean hasParent() {
        return parent != null;
    }

    public Group getParent() {
        return parent;
    }

    /**
     * Set a new parent.
     * This will remove the group from its old parent
     * and add it to the nwe parents childs list
     * @param group
     */
    public void setParent(Group group) {
        if (parent != null) {
            parent.detachChild(this);
        }
        parent = group;
        if (parent != null) {
            parent.addChild(this);
        }
    }

    public void addChild(Group g) {
        childGroups.add(g);
    }

    public void detachChild(Group g) {
        childGroups.remove(g);
    }

    /**
     * Return am live list of children.
     * Modify this list only if you know what you're doing.
     * For changing group inheritance use setParent();
     * @return
     */
    public ArrayList<Group> getChildren() {
        return childGroups;
    }
}
