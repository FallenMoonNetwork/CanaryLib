package net.canarymod.group;

import net.canarymod.permissionsystem.PermissionProvider;

/**
 * Represents a player group
 * @author Chris
 *
 */
public class Group {
    /**
     * Group ID - used for database transactions
     */
    public int      id;

    /**
     * Group Name
     */
    public String   name;

    /**
     * Group Prefix/Color
     */
    public String   prefix;

    /**
     * The permission provider for querying permissions etc.
     */
    public PermissionProvider permissions;

    /**
     * List of groups this group inherits/has control over
     */
    public Group[] inheritedGroups; 

    /**
     * Is true if it's the default group
     */
    public boolean  defaultGroup;

    /**
     * If true all players within this group ignore restrictions
     */
    public boolean  ignoreRestrictions;

    /**
     * If true all players within this group have administrator privileges
     */
    public boolean  administrator;

    /**
     * If false this player can not modify chests or furnaces and can not
     * destroy/create blocks
     */
    public boolean  canModifyWorld = true;
    
    /**
     * Check if this group has control over the given group,
     * specifically, check if the given group is a child of this group,
     * or if this group is admin or can ignore restrictions.<br>
     * Note: A plugin can define its own control thing as all Group memebers are public.
     * @param g
     * @return
     */
    public boolean hasControlOver(Group g) {
        if(administrator || ignoreRestrictions) {
            return true;
        }
        if(this.name.equals(g.name)) {
            return true;
        }
        //TODO: Preorder traversal over child groups!!
        for(Group gr : inheritedGroups) {
            if(gr.name.equals(g.name)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasControlOver(String gr) {
        if(administrator || ignoreRestrictions) {
            return true;
        }
        if(this.name.equals(gr)) {
            return true;
        }
       // TODO: Preorder traversal over child groups!!
        for(Group g : inheritedGroups) {
            if(g.name.equals(gr)) {
                return true;
            }
        }
        return false;
    }
}
