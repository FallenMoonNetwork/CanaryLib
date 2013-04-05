package net.canarymod.backbone;


import java.util.ArrayList;
import net.canarymod.Canary;
import net.canarymod.database.DataAccess;
import net.canarymod.database.Database;
import net.canarymod.database.exceptions.DatabaseReadException;
import net.canarymod.database.exceptions.DatabaseWriteException;
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

    /**
     * Converts Strings with literal 'null' to null value.  If the string is not
     * null or the literal string 'null' then it returns the string.
     *
     * @param test String to test.
     * @return The string or null if test equals null or literal string 'null'
     */
    public String stringToNull(String test) {
        if (test == null) {
            return null;
        }
        if (test.equalsIgnoreCase("null")) {
            return null;
        }
        return test;
    }

    /**
     * Add a new Group to the list of Groups.
     *
     * @param Group The group instance to add.
     */
    public void addGroup(Group group) {
        if (groupExists(group)) {
            updateGroup(group);
            return;
        }
        GroupAccess data = new GroupAccess();

        data.isDefault = group.isDefaultGroup();
        data.prefix = group.getPrefix();
        data.name = group.getName();
        if (group.hasParent()) {
            data.parent = group.getParent().getName();
        }
        try {
            Database.get().insert(data);
        } catch (DatabaseWriteException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }
    }

    private boolean groupExists(Group group) {
        GroupAccess data = new GroupAccess();

        try {
            Database.get().load(data, new String[] { "name"}, new Object[] { group.getName()});
        } catch (DatabaseReadException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }

        return data.hasData();
    }

    /**
     * Remove a group from the data source
     *
     * @param group the Group instance to remove.
     */
    public void removeGroup(Group group) {
        try {
            Database.get().remove("group", new String[] { "name"}, new Object[] { group.getName()});
            Database.get().remove("permission", new String[] { "owner", "type"}, new Object[] { group.getName(), "group"});
        } catch (DatabaseWriteException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }

    }

    /**
     * Update a Group.
     *
     * @param Group The group instance to update to the database.
     */
    public void updateGroup(Group group) {
        if (!groupExists(group)) {
            Canary.logWarning("Group " + group.getName() + " was not updated, it does not exist!");
            return;
        }
        GroupAccess updatedData = new GroupAccess();

        updatedData.isDefault = group.isDefaultGroup();
        updatedData.prefix = group.getPrefix();
        updatedData.name = group.getName();
        if (group.hasParent()) {
            updatedData.parent = group.getParent().getName();
            for (Group g : group.getChildren()) {
                updateGroup(g);
            }
        }
        try {
            Database.get().update(updatedData, new String[] { "name"}, new Object[] { group.getName()});
        } catch (DatabaseWriteException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }
    }

    private Group loadParents(String parent, ArrayList<Group> existingGroups) {
        if (parent == null) {
            return null;
        }
        for (Group g : existingGroups) {
            if (g.getName().equals(parent)) {
                return g;
            }
        }
        GroupAccess data = new GroupAccess();

        try {
            Database.get().load(data, new String[] { "name"}, new Object[] { parent});
        } catch (DatabaseReadException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }
        if (data.hasData()) {
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
     * @param name name of the group to check.
     * @param list list of groups to check in.
     * @return true - the group is in the list<br>false - the group is not in the list.
     */
    private boolean alreadyInList(String name, ArrayList<Group> list) {
        for (Group g : list) {
            if (g.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Load and return all recorded groups
     *
     * @return An ArrayList containing all recorded groups.
     */
    public ArrayList<Group> loadGroups() {
        ArrayList<DataAccess> dataList = new ArrayList<DataAccess>();
        ArrayList<Group> groups = new ArrayList<Group>();

        try {
            Database.get().loadAll(new GroupAccess(), dataList, new String[] {}, new Object[] {});
            for (DataAccess da: dataList) {
                GroupAccess data = (GroupAccess) da;

                if (alreadyInList(data.name, groups)) {
                    continue;
                }
                Group g = new Group();

                g.setDefaultGroup(data.isDefault);
                g.setId(data.id);
                g.setName(data.name);
                if (!data.isDefault || !data.name.equals(data.parent)) {
                    g.setParent(loadParents(data.parent, groups));
                }
                g.setPrefix(data.prefix);
                groups.add(g);
            }
        } catch (DatabaseReadException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }

        return groups;
    }

    /**
     * Creates a set of default groups and puts them into the database
     */
    public static void createDefaults() {
        GroupAccess visitors = new GroupAccess();
        GroupAccess players = new GroupAccess();
        GroupAccess mods = new GroupAccess();
        GroupAccess admins = new GroupAccess();

        // make visitors group data
        visitors.isDefault = true;
        visitors.name = "visitors";
        visitors.parent = "visitors";
        visitors.prefix = "7";

        // make player group data
        players.isDefault = false;
        players.name = "players";
        players.parent = "visitors";
        players.prefix = "f";

        // make mod group data
        mods.isDefault = false;
        mods.name = "mods";
        mods.parent = "players";
        mods.prefix = "e";

        // make admins group data
        admins.isDefault = false;
        admins.name = "admins";
        admins.parent = "mods";
        admins.prefix = "c";
        try {
            Database.get().insert(visitors);
            Database.get().insert(players);
            Database.get().insert(mods);
            Database.get().insert(admins);
        } catch (DatabaseWriteException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }
        BackbonePermissions.createDefaultPermissionSet();
    }
}
