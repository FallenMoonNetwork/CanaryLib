package net.canarymod.backbone;

import java.util.ArrayList;

import net.canarymod.Canary;
import net.canarymod.bansystem.Ban;
import net.canarymod.database.DatabaseRow;
import net.canarymod.database.DatabaseTable;

/**
 * Backbone to the ban System. This contains NO logic, it is only the data
 * source access!
 * 
 * @author Chris
 * 
 */
public class BackboneBans extends Backbone {
    
    public BackboneBans() {
        super(Backbone.System.BANS);
    }
    
    private boolean banExists(Ban ban) {
        DatabaseRow[] rows = getTable().getFilteredRows("name", ban.getSubject());
        if(rows == null || rows.length == 0) {
            return false;
        }
        return true;
    }
    
    /**
     * Add a new Ban to the list of bans.
     * 
     * @param ban
     */
    public void addBan(Ban ban) {
        if(banExists(ban)) {
            updateBan(ban);
            return;
        }
        DatabaseRow newData = getTable().addRow();
        newData.setStringCell("name", ban.getSubject());
        newData.setLongCell("timestamp", ban.getTimestamp());
        newData.setStringCell("reason", ban.getReason());
        newData.setStringCell("ip", ban.getIp());
    }

    /**
     * Lift a ban that was issued for the player with the given name or IP
     * 
     * @param name
     */
    public void liftBan(String subject) {
        DatabaseTable table = getTable();
        DatabaseRow[] toLift = table.getFilteredRows("name", subject);
        if(toLift != null) {
            for(DatabaseRow row : toLift) {
                table.removeRow(row);
            }
        }
    }

    /**
     * Lift an IP ban.
     * 
     * @param subject (IP)
     */
    public void liftIpBan(String subject) {
        DatabaseTable table = getTable();
        DatabaseRow[] toLift = table.getFilteredRows("ip", subject);
        if(toLift != null) {
            for(DatabaseRow row : toLift) {
                table.removeRow(row);
            }
        }
    }

    /**
     * Get a ban for this player name.
     * This may return null if the ban does not exist
     * 
     * @param name
     * @return Returns a ban object if that ban was found, null otherwise
     */
    public Ban getBan(String name) {
        Ban newBan = null;
        DatabaseRow[] toLift = getTable().getFilteredRows("name", name);
        
        if(toLift != null && toLift.length > 0) {
          //It should only be one
            newBan = new Ban();
            DatabaseRow row = toLift[0];
            newBan.setReason(row.getStringCell("reason"));
            newBan.setSubject(row.getStringCell("name"));
            newBan.setIp(row.getStringCell("ip"));
            newBan.setTimestamp(row.getLongCell("timestamp"));
        }
        return newBan;
    }

    /**
     * Update a ban.
     * 
     * @param ban
     */
    public void updateBan(Ban ban) {
        DatabaseRow[] rows = getTable().getFilteredRows("name", ban.getSubject());
        //It's only this one
        if(rows != null && rows.length > 0) {
            DatabaseRow row = rows[0];
            row.setStringCell("reason", ban.getReason());
            row.setStringCell("ip", ban.getIp());
            row.setLongCell("timestamp", ban.getTimestamp());
        }
    }

    /**
     * Load and return all recorded bans
     * 
     * @return
     */
    public ArrayList<Ban> loadBans() {
        ArrayList<Ban> banList = new ArrayList<Ban>();
        DatabaseTable table = getTable();
        
        DatabaseRow[] rows = table.getAllRows();
        if(rows != null) {
            for(DatabaseRow row : rows) {
                Ban ban = new Ban();
                ban.setIp(row.getStringCell("ip"));
                ban.setReason(row.getStringCell("reason"));
                ban.setSubject(row.getStringCell("name"));
                ban.setTimestamp(row.getLongCell("timestamp"));
                banList.add(ban);
            }
        }
        return banList;
    }
    
    private DatabaseTable getTable() {
        DatabaseTable table = Canary.db().getTable("bans");
        
        if(table == null) {
            Canary.db().prepare();
            table = Canary.db().addTable("bans");
            table.appendColumn("name", DatabaseTable.ColumnType.STRING);
            table.appendColumn("reason", DatabaseTable.ColumnType.STRING);
            table.appendColumn("timestamp", DatabaseTable.ColumnType.LONG);
            table.appendColumn("ip", DatabaseTable.ColumnType.STRING);
            Canary.db().execute();
        }
        
        return table;
    }
}
