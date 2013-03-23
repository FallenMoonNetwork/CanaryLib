package net.canarymod.backbone;

import java.util.ArrayList;
import java.util.HashMap;

import net.canarymod.Logman;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.database.DataAccess;
import net.canarymod.database.Database;
import net.canarymod.database.exceptions.DatabaseReadException;
import net.canarymod.database.exceptions.DatabaseWriteException;

/**
 * Backbone to the Player System. This contains NO logic, it is only the data
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
     * Add a new Player to the database.
     *
     * @param Group
     */
    public void addUser(Player player) {
        if(userExists(player)) {
            Logman.logWarning("Player " + player.getName() + " already exists. Updating it instead!");
            updatePlayer(player);
            return;
        }
        PlayerDataAccess data = new PlayerDataAccess();
        data.name = player.getName();
        data.group = player.getGroup().getName();
        data.prefix = player.getColor();
        try {
            Database.get().insert(data);
        } catch (DatabaseWriteException e) {
            Logman.logStackTrace(e.getMessage(), e);
        }
    }

    /**
     * Get whether a user exists
     * @param player
     * @return true if user exists, false otherwise
     */
    private boolean userExists(Player player) {
        PlayerDataAccess data = new PlayerDataAccess();
        try {
            Database.get().load(data, new String[]{"name"}, new Object[]{player.getName()});
        } catch (DatabaseReadException e) {
            Logman.logStackTrace(e.getMessage(), e);
        }

        return data.hasData();
    }

    /**
     * Remove a group from the data source
     *
     * @param group
     */
    public void removeUser(Player player) {
        try {
            Database.get().remove("player", new String[]{"name"}, new Object[]{player.getName()});
        } catch (DatabaseWriteException e) {
            Logman.logStackTrace(e.getMessage(), e);
        }
    }

    /**
     * Update a Group.
     *
     * @param Group
     */
    public void updatePlayer(Player player) {
        PlayerDataAccess data = new PlayerDataAccess();
        data.name = player.getName();
        data.group = player.getGroup().getName();
        data.prefix = player.getColor();
        try {
            Database.get().update(data, new String[]{"name"}, new Object[]{player.getName()});
        } catch (DatabaseWriteException e) {
            Logman.logStackTrace(e.getMessage(), e);
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
        ArrayList<DataAccess> daos = new ArrayList<DataAccess>();
        try {
            Database.get().loadAll(new PlayerDataAccess(), daos, new String[]{}, new Object[]{});
            for(DataAccess dao : daos) {
                PlayerDataAccess data = (PlayerDataAccess) dao;
                String[] row = new String[2];
                row[0] = data.prefix;
                row[1] = data.group;
                players.put(data.name, row);
            }
            return players;
        } catch (DatabaseReadException e) {
            Logman.logStackTrace(e.getMessage(), e);
        }

        return null;
    }

    public static void createDefaults() {
        PlayerDataAccess player = new PlayerDataAccess();
        player.group = "players";
        player.name = "Bob the Builder";

        PlayerDataAccess mod = new PlayerDataAccess();
        mod.group = "mods";
        mod.name = "Moderator Person";

        PlayerDataAccess admin = new PlayerDataAccess();
        admin.group = "admins";
        admin.name = "Evil Uber Administrator";

        try {
            Database.get().insert(player);
            Database.get().insert(mod);
            Database.get().insert(admin);
        } catch (DatabaseWriteException e) {
            Logman.logStackTrace(e.getMessage(), e);
        }
    }
}
