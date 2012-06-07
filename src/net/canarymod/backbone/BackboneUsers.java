package net.canarymod.backbone;

import java.util.HashMap;

import net.canarymod.Canary;
import net.canarymod.api.entity.Player;
import net.canarymod.database.Database;
import net.canarymod.database.DatabaseRow;
import net.canarymod.database.DatabaseTable;

/**
 * Backbone to the ban System. This contains NO logic, it is only the data
 * source access!
 * 
 * @author Chris
 * 
 */
public class BackboneUsers extends Backbone {

    Database db;
    Database.Type type;
    
    public BackboneUsers(Database database, Database.Type type) {
        super(Backbone.System.USERS, type);
        db = database;
        this.type = type;
    }
    /**
     * Add a new Group to the list of Groups.
     * 
     * @param Group
     */
    public void addUser(Player player) {
        if(userExists(player)) {
            updatePlayer(player);
            return;
        }
        DatabaseTable table = db.getTable("users");
        DatabaseRow newData = table.addRow();
        
        newData.setStringCell("username", player.getName());
        newData.setStringCell("prefix", player.getColor());
        newData.setStringCell("group", player.getGroup().name);
    }

    private boolean userExists(Player player) {
        DatabaseTable table = db.getTable("users");
        DatabaseRow[] newData = table.getFilteredRows("username", player.getName());
        if(newData != null && newData.length > 0) {
            return true;
        }
        return false;
    }
    /**
     * Remove a group from the data source
     * 
     * @param group
     */
    public void removeUser(Player player) {
        DatabaseTable table = db.getTable("users");
        DatabaseRow[] newData = table.getFilteredRows("username", player.getName());
        if(newData != null && newData.length > 0) {
            for(DatabaseRow row : newData) {
                table.removeRow(row);
                Canary.permissionManager().removeRelationsFromUser(player.getName());
            }
        }
    }

    /**
     * Update a Group.
     * 
     * @param Group
     */
    public void updatePlayer(Player player) {
        DatabaseTable table = db.getTable("users");
        DatabaseRow[] newData = table.getFilteredRows("username", player.getName());
        if(newData != null && newData.length == 1) {
            DatabaseRow row = newData[0];
            row.setStringCell("prefix", player.getColor());
            row.setStringCell("group", player.getGroup().name);
            StringBuilder ips = new StringBuilder();
            for(String ip : player.getAllowedIPs()) {
                ips.append(ip).append(",");
            }
            ips.deleteCharAt(ips.length()-1); //remove last comma
            row.setStringCell("iplist", ips.toString());
        }
    }
    /**
     * Load and return String array sets.
     * Each Array in the hashMap value has prefix and group for a player, in that order. 
     * 
     * @return
     */
    public HashMap<String, String[]> loadUsers() {
        HashMap<String, String[]> players = new HashMap<String, String[]>();
        DatabaseTable table = db.getTable("users");
        DatabaseRow[] userRows = table.getAllRows();
        if(userRows != null && userRows.length > 0) {
            for(DatabaseRow row : userRows) {
                String[] content = new String[3];
                content[0] = row.getStringCell("prefix");
                content[1] = row.getStringCell("group");
                content[2] = row.getStringCell("iplist");
                players.put(row.getStringCell("username"), content);
            }
        }
        return players;
    }

}
