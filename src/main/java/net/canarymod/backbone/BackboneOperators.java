package net.canarymod.backbone;

import java.util.ArrayList;

import net.canarymod.Canary;
import net.canarymod.database.DataAccess;
import net.canarymod.database.Database;
import net.canarymod.database.exceptions.DatabaseReadException;
import net.canarymod.database.exceptions.DatabaseWriteException;

/**
 * Backbone to the ops system. This contains NO logic, it is only the data
 * source access!
 *
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public class BackboneOperators extends Backbone {

    public BackboneOperators() {
        super(Backbone.System.OPERATORS);
        try {
            Database.get().updateSchema(new OperatorsDataAccess());
        }
        catch (DatabaseWriteException e) {
            Canary.logStacktrace("Failed to update database schema", e);
        }
    }

    public boolean isOpped(String player) {
        OperatorsDataAccess data = new OperatorsDataAccess();

        try {
            Database.get().load(data, new String[]{ "player" }, new Object[]{ player });
        }
        catch (DatabaseReadException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
        return data.hasData();
    }

    /**
     * Add a new ops entry
     *
     * @param player
     *         the name of the player to op
     */
    public void addOpEntry(String player) {
        if (isOpped(player)) {
            return;
        }
        OperatorsDataAccess data = new OperatorsDataAccess();

        data.player = player;
        try {
            Database.get().insert(data);
        }
        catch (DatabaseWriteException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
    }

    /**
     * Removes a player from the op list
     *
     * @param subject
     *         the name of the player to de-op
     */
    public void removeOpEntry(String subject) {
        try {
            Database.get().remove("operators", new String[]{ "player" }, new Object[]{ subject });
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
    public ArrayList<String> loadOps() {
        ArrayList<String> ops = new ArrayList<String>();
        ArrayList<DataAccess> dataList = new ArrayList<DataAccess>();

        try {
            Database.get().loadAll(new OperatorsDataAccess(), dataList, new String[]{ }, new Object[]{ });
            for (DataAccess da : dataList) {
                OperatorsDataAccess data = (OperatorsDataAccess) da;
                ops.add(data.player);
            }
        }
        catch (DatabaseReadException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
        return ops;
    }
}
