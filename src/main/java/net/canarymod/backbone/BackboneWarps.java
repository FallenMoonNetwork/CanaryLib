package net.canarymod.backbone;

import java.util.ArrayList;

import net.canarymod.Canary;
import net.canarymod.api.world.position.Location;
import net.canarymod.database.DatabaseRow;
import net.canarymod.database.DatabaseTable;
import net.canarymod.user.Group;
import net.canarymod.warp.Warp;

/**
 * Backbone to the warps system This contains NO logic, it is only the data
 * source access!
 * 
 * @author Chris
 * 
 */
public class BackboneWarps extends Backbone {
    
    public BackboneWarps() {
        super(Backbone.System.WARPS);
    }

    private boolean warpExists(Warp warp) {
        DatabaseRow[] rows = getTable().getFilteredRows("name", warp.getName());
        if(rows == null || rows.length == 0) {
            return false;
        }
        return true;
    }
    
    private String createGroupsList(Group[] groups) {
        if(groups == null) {
            return "null";
        }
        StringBuilder list = new StringBuilder();
        for(Group g : groups) {
            list.append(g.name).append(",");
        }
        if(list.length() > 0) {
            list.deleteCharAt(list.length() - 1);
            return list.toString();
        }
        return "null";
    }
    
    private Group[] createGroupArray(String groupList) {
        //XXX NOTE: This REQUIRES the Groups backbone to be loaded already!!!
        String[] split = groupList.split(",");
        Group[] groups = new Group[split.length];
        for(int i = 0; i < split.length; i++) {
            Group g = Canary.usersAndGroups().getGroup(split[i]);
            if(g != null) {
                groups[i] = g;
            }
            else {
                groups[i] = Canary.usersAndGroups().getDefaultGroup();
            }
        }
        return groups;
    }
    
    private String stringToNull(String s) {
        if(s == null || s.equalsIgnoreCase("null")) {
            return null;
        }
        else {
            return s;
        }
    }
    /**
     * Add a new Warp to the list of Warps.
     * 
     * @param Warp
     */
    public void addWarp(Warp warp) {
        if(warpExists(warp)) {
            updateWarp(warp);
            return;
        }
        Canary.db().prepare();
        DatabaseRow newData = getTable().addRow();
        newData.setStringCell("name", warp.getName());
        newData.setStringCell("location", warp.getLocation().toString());
        newData.setStringCell("owner", warp.getOwner());
        newData.setStringCell("groups", createGroupsList(warp.getGroups()));
        Canary.db().execute();
    }

    /**
     * Remove a Warp from the data source
     * 
     * @param Warp
     */
    public void removeWarp(Warp warp) {
        Canary.db().prepare();
        DatabaseTable table = getTable();
        DatabaseRow[] newData = table.getFilteredRows("name",warp.getName());
        if(newData != null && newData.length > 0) {
            for(DatabaseRow row : newData) {
                table.removeRow(row);
            }
        }
        Canary.db().execute();
    }

    /**
     * Update a Warp
     * 
     * @param Warp
     */
    public void updateWarp(Warp warp) {
        Canary.db().prepare();
        DatabaseRow[] rows = getTable().getFilteredRows("name", warp.getName());
        //It's only this one
        if(rows != null && rows.length > 0) {
            DatabaseRow row = rows[0];
            row.setStringCell("location", warp.getLocation().toString());
            row.setStringCell("owner", warp.getOwner());
            row.setStringCell("groups", createGroupsList(warp.getGroups()));
        }
        Canary.db().execute();
    }

    /**
     * Load and return all warps
     * 
     * @return
     */
    public ArrayList<Warp> loadWarps() {
        ArrayList<Warp> warps = new ArrayList<Warp>();
        Canary.db().prepare();
        DatabaseRow[] rows = getTable().getAllRows();
        if(rows != null && rows.length > 0) {
            for(DatabaseRow row : rows) {
                String name = row.getStringCell("name");
                Location loc = Location.fromString(row.getStringCell("location"));
                String owner = stringToNull(row.getStringCell("owner"));
                Group[] groups = createGroupArray(row.getStringCell("groups"));
                
                Warp warp = null;
                if(owner != null) {
                    warp = new Warp(loc, name, owner);
                }
                else if (groups != null && groups.length > 0){
                    warp = new Warp(loc, groups, name);
                }
                else {
                    //assume this is a public warp
                    warp = new Warp(loc, name);
                }
                warps.add(warp);
            }
        }
        return warps;
    }
    
    private DatabaseTable getTable() {
        Canary.db().prepare();
        DatabaseTable table = Canary.db().getTable("warps");
        
        if(table == null) {
            table = Canary.db().addTable("warps");
            table.appendColumn("name", DatabaseTable.ColumnType.STRING);
            table.appendColumn("location", DatabaseTable.ColumnType.STRING);
            table.appendColumn("owner", DatabaseTable.ColumnType.STRING);
            table.appendColumn("groups", DatabaseTable.ColumnType.STRING);
            
        }
        Canary.db().execute();
        return table;
    }
}
