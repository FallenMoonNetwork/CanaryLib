package net.canarymod.backbone;

import java.util.ArrayList;

import net.canarymod.database.Database;
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
    }

    /**
     * emove a group from the data source
     * 
     * @param group
     */
    public void removeGroup(Group group) {
    }

    /**
     * Get a Group for this player name
     * 
     * @param name
     * @return Returns a Group object if that Group was found, null otherwise
     */
    public Group getGroup(String name) {
        return null;
    }

    /**
     * Update a Group.
     * 
     * @param Group
     */
    public void updateGroup(Group group) {
    }

    /**
     * Load and return all recorded bans
     * 
     * @return
     */
    public ArrayList<Group> loadGroups() {
        return null;
    }
}
