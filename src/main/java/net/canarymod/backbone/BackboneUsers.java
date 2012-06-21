package net.canarymod.backbone;

import java.util.HashMap;

import net.canarymod.Canary;
import net.canarymod.api.entity.Player;
import net.canarymod.database.DatabaseRow;
import net.canarymod.database.DatabaseTable;

/**
 * Backbone to the ban System. This contains NO logic, it is only the data
 * source access!
 * 
 * @author Chris Ksoll
 * 
 */
public class BackboneUsers extends Backbone {

    public BackboneUsers() {
        super(Backbone.System.USERS);
    }
    
    /**
     * Add a new Group to the list of Groups.
     * 
     * @param Group
     */
    public void addUser(Player player) {
        Canary.db().prepare();
        if(userExists(player)) {
            updatePlayer(player);
            return;
        }
        DatabaseRow newData = getTable().addRow();
        
        newData.setStringCell("username", player.getName());
        newData.setStringCell("prefix", player.getColor());
        newData.setStringCell("group", player.getGroup().name);
        Canary.db().execute();
    }

    /**
     * Get whether a user exists
     * @param player
     * @return true if user exists, false otherwise
     */
    private boolean userExists(Player player) {
        DatabaseRow[] newData = getTable().getFilteredRows("username", player.getName());
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
        Canary.db().prepare();
        DatabaseTable table = getTable();
        DatabaseRow[] newData = table.getFilteredRows("username", player.getName());
        if(newData != null && newData.length > 0) {
            for(DatabaseRow row : newData) {
                table.removeRow(row);
                Canary.permissionManager().removeRelationsFromUser(player.getName());
            }
        }
        Canary.db().execute();
    }

    /**
     * Update a Group.
     * 
     * @param Group
     */
    public void updatePlayer(Player player) {
        Canary.db().prepare();
        DatabaseRow[] newData = getTable().getFilteredRows("username", player.getName());
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
        Canary.db().execute();
    }
    
    /**
     * Load and return String array sets.
     * Each Array in the hashMap value has prefix and group for a player, in that order. 
     * 
     * @return
     */
    public HashMap<String, String[]> loadUsers() {
        HashMap<String, String[]> players = new HashMap<String, String[]>();
        DatabaseRow[] userRows = getTable().getAllRows();
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

    /**
     * Get the users table. If the table does not exist, create it with the relation table
     * @return
     */
    private DatabaseTable getTable() {
        DatabaseTable table = Canary.db().getTable("users");
        if(table == null) {
            Canary.db().prepare();
            table = Canary.db().addTable("users");
            table.appendColumn("username", DatabaseTable.ColumnType.STRING);
            table.appendColumn("group", DatabaseTable.ColumnType.STRING);
            table.appendColumn("prefix", DatabaseTable.ColumnType.STRING);
            table.appendColumn("iplist", DatabaseTable.ColumnType.STRING);
            
            DatabaseTable relTable = Canary.db().addTable("permissions_users_rel");
            relTable.appendColumn("pnid", DatabaseTable.ColumnType.INTEGER);
            relTable.appendColumn("username", DatabaseTable.ColumnType.STRING);
            Canary.db().execute();
        }
        
        return table;
    }
}
