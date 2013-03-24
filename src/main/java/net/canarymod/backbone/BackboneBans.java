package net.canarymod.backbone;

import java.util.ArrayList;

import net.canarymod.Canary;
import net.canarymod.bansystem.Ban;
import net.canarymod.database.DataAccess;
import net.canarymod.database.Database;
import net.canarymod.database.exceptions.DatabaseReadException;
import net.canarymod.database.exceptions.DatabaseWriteException;

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
        BanAccess data = new BanAccess();
        try {
            Database.get().load(data, new String[]{"player"}, new Object[] {ban.getSubject()});
        }
        catch (DatabaseReadException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }
        return data.hasData();
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
        BanAccess data = new BanAccess();
        data.player = ban.getSubject();
        data.banningPlayer = ban.getBanningPlayer();
        data.unbanDate = ban.getTimestamp();
        data.reason = ban.getReason();
        data.ip = ban.getIp();
        try {
            Database.get().insert(data);
        }
        catch (DatabaseWriteException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }
    }

    /**
     * Lift a ban that was issued for the player with the given name or IP
     *
     * @param tableName
     */
    public void liftBan(String subject) {
        try {
            Database.get().remove("ban", new String[]{"player"}, new Object[] {subject});
        }
        catch (DatabaseWriteException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }
    }

    /**
     * Lift an IP ban.
     *
     * @param subject (IP)
     */
    public void liftIpBan(String subject) {
        try {
            Database.get().remove("ban", new String[]{"ip"}, new Object[] {subject});
        }
        catch (DatabaseWriteException e) {
            Canary.logStackTrace(e.getMessage(), e);
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
        BanAccess data = new BanAccess();
        try {
            Database.get().load(data, new String[] {"player"}, new Object[] {name});
        }
        catch (DatabaseReadException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }
        if(!data.hasData()) {
            return null;
        }
        newBan = new Ban();
        newBan.setIp(data.ip);
        newBan.setIsIpBan(!data.ip.contains("xxx"));
        newBan.setReason(data.reason);
        newBan.setSubject(data.player);
        newBan.setTimestamp(data.unbanDate);
        newBan.setBanningPlayer(data.banningPlayer);
        return newBan;
    }

    /**
     * Update a ban.
     *
     * @param ban
     */
    public void updateBan(Ban ban) {
        BanAccess data = new BanAccess();
        try {
            Database.get().load(data, new String[] {"player"}, new Object[] {ban.getSubject()});
            if(data.hasData()) {
                data.banningPlayer = ban.getBanningPlayer();
                data.ip = ban.getIp();
                data.player = ban.getSubject();
                data.reason = ban.getReason();
                data.unbanDate = ban.getTimestamp();
                Database.get().update(data, new String[] {"player"}, new Object[] {ban.getSubject()});
            }

        }
        catch (DatabaseReadException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }
        catch (DatabaseWriteException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }

    }

    /**
     * Load and return all recorded bans
     *
     * @return
     */
    public ArrayList<Ban> loadBans() {
        ArrayList<Ban> banList = new ArrayList<Ban>();
        ArrayList<DataAccess> dataList = new ArrayList<DataAccess>();
        try {
            Database.get().loadAll(new BanAccess(), dataList, new String[]{}, new Object[]{});
            for(DataAccess da : dataList) {
                BanAccess data = (BanAccess) da;
                Ban ban = new Ban();
                ban.setBanningPlayer(data.banningPlayer);
                ban.setIp(data.ip);
                ban.setIsIpBan(!data.ip.contains("xxx"));
                ban.setReason(data.reason);
                ban.setSubject(data.player);
                ban.setTimestamp(data.unbanDate);
                banList.add(ban);
            }
        }
        catch (DatabaseReadException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }
        return banList;
    }
}
