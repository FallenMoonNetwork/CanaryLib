package net.canarymod.user;

import java.util.ArrayList;

import net.canarymod.chat.Colors;
import net.canarymod.permissionsystem.PermissionProvider;

/**
 * Represents a player group
 *
 * @author Chris (damagefilter)
 */
public class Group {

    /** ID for retrieving permissions from the database */
    private int id;

    /** Group Name */
    private String name;

    /** Group Prefix/Color */
    private String prefix = null;

    /**
     * The fully qualified world name valid for this group.
     * If this group is valid for all worlds, this may be null
     */
    private String worldName = null;

    /** The permission provider for querying permissions etc. */
    private PermissionProvider permissions;

    /** List of groups this group inherits/has control over */
    private ArrayList<Group> childGroups = new ArrayList<Group>();

    /**
     * The parent group (the group this group is a child of).
     * Parents have control over their childs
     */
    private Group parent = null;

    /** Is true if it's the default group */
    private boolean defaultGroup = false;

    /**
     * Check if this group can ignore restrictions
     *
     * @return {@code true} if can Ignore Restrictions
     */
    public boolean canIgnorerestrictions() {
        return hasPermission("canary.super.ignoreRestrictions");
    }

    /**
     * Check if this group is an administrative groups
     *
     * @return {@code true} if administrator group
     */
    public boolean isAdministratorGroup() {
        return hasPermission("canary.super.administrator");
    }

    /**
     * Checks if this group can build
     *
     * @return {@code true} if can build
     */
    public boolean canBuild() {
        return hasPermission("canary.world.build");
    }

    /**
     * Check if this group has control over the given group, specifically, check
     * if the given group is a child of this group, or if this group is admin or
     * can ignore restrictions.<br>
     *
     * @param g
     *         the group to check control of
     *
     * @return {@code true} if has control over
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
     *
     * @return {@code true} if has permission
     */
    public boolean hasPermission(String permission) {
        // NOTE: to whoever comes by and thinks, hey a permission check hook is missing:
        // Permission check hooks are fired in all MessageReceivers.
        // Doing it here too would fire a hook for the same request twice.
        if (permissions.pathExists(permission)) {
            return permissions.queryPermission(permission);
        }
        // if(permissions.queryPermission(permission)) {
        // return true;
        // }

        for (Group g : parentsToList()) {
            if (g.permissions.pathExists(permission)) {
                return g.permissions.queryPermission(permission);
            }
        }
        return false;
    }

    /**
     * Returns all the children groups
     *
     * @return the list of children groups
     */
    public ArrayList<Group> childsToList() {
        ArrayList<Group> list = new ArrayList<Group>();

        walkChilds(list, this);
        return list;
    }

    /**
     * Returns all the parents from this group upwards
     *
     * @return the list of parent groups
     */
    public ArrayList<Group> parentsToList() {
        ArrayList<Group> parents = new ArrayList<Group>();
        if (this.parent == null || this.parent == this) {
            return parents;
        }
        if (!parents.contains(this.parent)) {
            parents.add(this.parent);
        }
        walkParents(parents, this.parent);
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

    /**
     * Gets the prefix of the Group
     *
     * @return the prefix
     */
    public String getPrefix() {
        return prefix != null ? prefix : Colors.WHITE;
    }

    /**
     * Sets the prefix of the Group
     *
     * @param prefix
     *         the prefix to set
     */
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
     *
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
     *
     * @return
     */
    public ArrayList<Group> getChildren() {
        return childGroups;
    }

    /** @return the worldName */
    public String getWorldName() {
        return worldName;
    }

    /**
     * @param worldName
     *         the worldName to set
     */
    public void setWorldName(String worldName) {
        if (this.parent != null && !this.parent.getWorldName().equals(worldName)) {
            return; //TODO: Throw exception?
        }
        this.worldName = worldName;
        for (Group g : childGroups) {
            g.setWorldName(worldName);
        }
    }

    public boolean isGlobal() {
        return this.worldName == null;
    }
}
