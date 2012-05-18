package net.canarymod.backbone;

import java.util.ArrayList;

import net.canarymod.group.Group;

/**
 * Backbone to the ban System. This contains NO logic, it is only the data
 * source access!
 * 
 * @author Chris
 * 
 */
public interface BackboneGroups extends Backbone {

    /**
     * Add a new Group to the list of Groups.
     * 
     * @param Group
     */
    public void addGroup(Group group);

    /**
     * emove a group from the data source
     * 
     * @param group
     */
    public void removeGroup(Group group);

    /**
     * Get a Group for this player name
     * 
     * @param name
     * @return Returns a Group object if that Group was found, null otherwise
     */
    public Group getGroup(String name);

    /**
     * Update a Group.
     * 
     * @param Group
     */
    public void updateGroup(Group group);

    /**
     * Load and return all recorded bans
     * 
     * @return
     */
    public ArrayList<Group> loadGroups();
}
