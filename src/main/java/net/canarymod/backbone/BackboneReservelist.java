package net.canarymod.backbone;

import java.util.ArrayList;

import net.canarymod.Canary;
import net.canarymod.database.DataAccess;
import net.canarymod.database.Database;
import net.canarymod.database.exceptions.DatabaseReadException;
import net.canarymod.database.exceptions.DatabaseWriteException;

/**
 * Backbone to the reservelist system. This contains NO logic, it is only the data
 * source access!
 *
 * @author Jason (darkdiplomat)
 */
public class BackboneReservelist extends Backbone {

    public BackboneReservelist() {
        super(Backbone.System.RESERVELIST);
        try {
            Database.get().updateSchema(new WhitelistDataAccess());
        }
        catch (DatabaseWriteException e) {
            Canary.logStacktrace("Failed to update database schema", e);
        }
    }

    /**
     * Checks if the player has a reserved slot
     *
     * @param player
     *         the player's name to check
     *
     * @return {@code true} if slot reserved; {@code false} otherwise
     */
    public boolean isSlotReserved(String player) {
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
     * Add a new reservelist entry
     *
     * @param player
     *         the player's name
     */
    public void addSlotReservation(String player) {
        if (isSlotReserved(player)) {
            return;
        }
        ReservelistDataAccess data = new ReservelistDataAccess();

        data.player = player;
        try {
            Database.get().insert(data);
        }
        catch (DatabaseWriteException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
    }

    /**
     * Removes a player from the reservelist
     *
     * @param subject
     *         the player's name
     */
    public void removeReservelistEntry(String subject) {
        try {
            Database.get().remove("reservelist", new String[]{ "player" }, new Object[]{ subject });
        }
        catch (DatabaseWriteException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
    }

    /**
     * Load and return all recorded reservelist entries
     *
     * @return An array list of all recorded reserve entries.
     */
    public ArrayList<String> loadReservelist() {
        ArrayList<String> reservelist = new ArrayList<String>();
        ArrayList<DataAccess> dataList = new ArrayList<DataAccess>();

        try {
            Database.get().loadAll(new ReservelistDataAccess(), dataList, new String[]{ }, new Object[]{ });
            for (DataAccess da : dataList) {
                ReservelistDataAccess data = (ReservelistDataAccess) da;
                reservelist.add(data.player);
            }
        }
        catch (DatabaseReadException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
        return reservelist;
    }
}
