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
 */
public class BackboneBans extends Backbone {

    public BackboneBans() {
        super(Backbone.System.BANS);
        try {
            Database.get().updateSchema(new BanDataAccess());
        }
        catch (DatabaseWriteException e) {
            Canary.logStacktrace("Failed to update Database Schema!", e);
        }
    }

    private boolean banExists(Ban ban) {
        BanDataAccess data = new BanDataAccess();

        try {
            Database.get().load(data, new String[]{ "player" }, new Object[]{ ban.getSubject() });
        }
        catch (DatabaseReadException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
        return data.hasData();
    }

    /**
     * Add a new Ban to the list of bans.
     *
     * @param ban
     *         The ban to add.
     */
    public void addBan(Ban ban) {
        if (banExists(ban)) {
            updateBan(ban);
            return;
        }
        BanDataAccess data = new BanDataAccess();

        data.player = ban.getSubject();
        data.banningPlayer = ban.getBanningPlayer();
        data.unbanDate = ban.getTimestamp();
        data.reason = ban.getReason();
        data.ip = ban.getIp();
        try {
            Database.get().insert(data);
        }
        catch (DatabaseWriteException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
    }

    /**
     * Lift a ban that was issued for the player with the given name
     *
     * @param subject
     *         Player name to unban.
     */
    public void liftBan(String subject) {
        try {
            Database.get().remove("ban", new String[]{ "player" }, new Object[]{ subject });
        }
        catch (DatabaseWriteException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
    }

    /**
     * Lift an IP ban.
     *
     * @param subject
     *         IP Address to unban.
     */
    public void liftIpBan(String subject) {
        try {
            Database.get().remove("ban", new String[]{ "ip" }, new Object[]{ subject });
        }
        catch (DatabaseWriteException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
    }

    /**
     * Get a ban for this player name.
     * This may return null if the ban does not exist
     *
     * @param name
     *         Ban for the player with the given name or null if none.
     *
     * @return Returns a ban object if that ban was found, null otherwise
     */
    public Ban getBan(String name) {
        BanDataAccess data = new BanDataAccess();

        try {
            Database.get().load(data, new String[]{ "player" }, new Object[]{ name });
        }
        catch (DatabaseReadException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
        if (!data.hasData()) {
            return null;
        }
        Ban newBan = new Ban();
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
     *         Ban instance to update.
     */
    public void updateBan(Ban ban) {
        BanDataAccess data = new BanDataAccess();

        try {
            Database.get().load(data, new String[]{ "player" }, new Object[]{ ban.getSubject() });
            if (data.hasData()) {
                data.banningPlayer = ban.getBanningPlayer();
                data.ip = ban.getIp();
                data.player = ban.getSubject();
                data.reason = ban.getReason();
                data.unbanDate = ban.getTimestamp();
                Database.get().update(data, new String[]{ "player" }, new Object[]{ ban.getSubject() });
            }

        }
        catch (DatabaseReadException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
        catch (DatabaseWriteException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }

    }

    /**
     * Load and return all recorded bans
     *
     * @return An array list of all recorded ban instances.
     */
    public ArrayList<Ban> loadBans() {
        ArrayList<Ban> banList = new ArrayList<Ban>();
        ArrayList<DataAccess> dataList = new ArrayList<DataAccess>();

        try {
            Database.get().loadAll(new BanDataAccess(), dataList, new String[]{ }, new Object[]{ });
            for (DataAccess da : dataList) {
                BanDataAccess data = (BanDataAccess) da;
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
            Canary.logStacktrace(e.getMessage(), e);
        }
        return banList;
    }
}
