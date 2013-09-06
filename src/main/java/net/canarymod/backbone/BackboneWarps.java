package net.canarymod.backbone;

import java.util.ArrayList;
import java.util.List;

import net.canarymod.Canary;
import net.canarymod.ToolBox;
import net.canarymod.api.world.position.Location;
import net.canarymod.database.DataAccess;
import net.canarymod.database.Database;
import net.canarymod.database.exceptions.DatabaseReadException;
import net.canarymod.database.exceptions.DatabaseWriteException;
import net.canarymod.user.Group;
import net.canarymod.warp.Warp;

/**
 * Backbone to the warps system This contains NO logic, it is only the data
 * source access!
 *
 * @author Chris
 */
public class BackboneWarps extends Backbone {

    public BackboneWarps() {
        super(Backbone.System.WARPS);
        try {
            Database.get().updateSchema(new WarpDataAccess());
        }
        catch (DatabaseWriteException e) {
            Canary.logStacktrace("Failed to update database schema", e);
        }
    }

    private boolean warpExists(Warp warp) {
        WarpDataAccess data = new WarpDataAccess();

        try {
            Database.get().load(data, new String[]{ "name" }, new Object[]{ warp.getName() });
        }
        catch (DatabaseReadException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
        return data.hasData();
    }

    /**
     * Creates a groups array.
     * IMPORTANT NOTE: This requires the groups backbone to be loaded already!
     *
     * @param groups
     *         the {@link Group}(s)
     *
     * @return group array
     */
    private Group[] makeGroupArray(List<String> groups) {
        Group[] data = new Group[groups.size()];

        for (int i = 0; i < groups.size(); ++i) {
            data[i] = Canary.usersAndGroups().getGroup(groups.get(i));
        }
        return data;
    }

    /**
     * Add a new Warp to the list of Warps.
     *
     * @param warp
     *         Warp instance to add to the data source.
     */
    public void addWarp(Warp warp) {
        if (warpExists(warp)) {
            updateWarp(warp);
            return;
        }
        WarpDataAccess data = new WarpDataAccess();

        data.groups = warp.getGroupsAsString();
        data.isPlayerHome = warp.isPlayerHome();
        data.location = warp.getLocation().toString();
        data.name = warp.getName();
        data.owner = warp.getOwner();

        try {
            Database.get().insert(data);
        }
        catch (DatabaseWriteException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
    }

    /**
     * Remove a Warp from the data source
     *
     * @param warp
     *         Warp instance to remove from the data source.
     */
    public void removeWarp(Warp warp) {
        try {
            Database.get().remove("warp", new String[]{ "name", "location" }, new Object[]{ warp.getName(), warp.getLocation().toString() });
        }
        catch (DatabaseWriteException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
    }

    /**
     * Update a Warp
     *
     * @param warp
     *         Warp instance to update to the data source.
     */
    public void updateWarp(Warp warp) {
        WarpDataAccess data = new WarpDataAccess();

        data.groups = warp.getGroupsAsString();
        data.isPlayerHome = warp.isPlayerHome();
        data.location = warp.getLocation().toString();
        data.name = warp.getName();
        data.owner = warp.getOwner();
        try {
            Database.get().update(data, new String[]{ "name" }, new Object[]{ warp.getName() });
        }
        catch (DatabaseWriteException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
    }

    /**
     * Load and return all warps
     *
     * @return An ArrayList containing all loaded Warp instances.
     */
    public ArrayList<Warp> loadWarps() {
        ArrayList<Warp> warps = new ArrayList<Warp>();
        ArrayList<DataAccess> daos = new ArrayList<DataAccess>();

        try {
            Database.get().loadAll(new WarpDataAccess(), daos, new String[]{ }, new Object[]{ });
            for (DataAccess dao : daos) {
                WarpDataAccess data = (WarpDataAccess) dao;
                Group[] groups = makeGroupArray(data.groups);
                String owner = ToolBox.stringToNull(data.owner);
                String name = data.name;
                boolean playerHome = data.isPlayerHome;
                Location loc = Location.fromString(data.location);
                Warp warp;

                if (owner != null) {
                    warp = new Warp(loc, name, owner, playerHome);
                }
                else if (groups != null && groups.length > 0) {
                    warp = new Warp(loc, groups, name);
                }
                else {
                    // assume this is a public warp
                    warp = new Warp(loc, name);
                }
                warps.add(warp);
            }
        }
        catch (DatabaseReadException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }

        return warps;
    }
}
