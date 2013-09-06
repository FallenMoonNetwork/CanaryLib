package net.canarymod.backbone;

import java.util.ArrayList;

import net.canarymod.Canary;
import net.canarymod.database.DataAccess;
import net.canarymod.database.Database;
import net.canarymod.database.exceptions.DatabaseReadException;
import net.canarymod.database.exceptions.DatabaseWriteException;

/**
 * Backbone to the whitelist system. This contains NO logic, it is only the data
 * source access!
 *
 * @author Chris (damagefilter)
 */
public class BackboneWhitelist extends Backbone {

    public BackboneWhitelist() {
        super(Backbone.System.WHITELIST);
        try {
            Database.get().updateSchema(new WhitelistDataAccess());
        }
        catch (DatabaseWriteException e) {
            Canary.logStacktrace("Failed to update database schema", e);
        }
    }

    /**
     * Checks if the player is whitelisted
     *
     * @param player
     *         the player's name to check
     *
     * @return {@code true} if whitelisted; {@code false} otherwise
     */
    public boolean isWhitelisted(String player) {
        WhitelistDataAccess data = new WhitelistDataAccess();

        try {
            Database.get().load(data, new String[]{ "player" }, new Object[]{ player });
        }
        catch (DatabaseReadException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
        return data.hasData();
    }

    /**
     * Add a new whitelist entry
     *
     * @param player
     *         the player's name
     */
    public void addWhitelistEntry(String player) {
        if (isWhitelisted(player)) {
            return;
        }
        WhitelistDataAccess data = new WhitelistDataAccess();

        data.player = player;
        try {
            Database.get().insert(data);
        }
        catch (DatabaseWriteException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
    }

    /**
     * Removes a player from the whitelist
     *
     * @param subject
     *         the player's name
     */
    public void removeWhitelistEntry(String subject) {
        try {
            Database.get().remove("whitelist", new String[]{ "player" }, new Object[]{ subject });
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
    public ArrayList<String> loadWhitelist() {
        ArrayList<String> whiteList = new ArrayList<String>();
        ArrayList<DataAccess> dataList = new ArrayList<DataAccess>();

        try {
            Database.get().loadAll(new WhitelistDataAccess(), dataList, new String[]{ }, new Object[]{ });
            for (DataAccess da : dataList) {
                WhitelistDataAccess data = (WhitelistDataAccess) da;
                whiteList.add(data.player);
            }
        }
        catch (DatabaseReadException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
        return whiteList;
    }
}
