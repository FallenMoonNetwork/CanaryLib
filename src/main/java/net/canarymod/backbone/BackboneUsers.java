package net.canarymod.backbone;


import java.util.ArrayList;
import java.util.HashMap;

import net.canarymod.Canary;
import net.canarymod.api.OfflinePlayer;
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
     * Add a new Player to the data source.
     *
     * @param player Player to add to the data source.
     */
    public void addUser(Player player) {
        if (userExists(player.getName())) {
            Canary.logWarning("Player " + player.getName() + " already exists. Updating it instead!");
            updatePlayer(player);
            return;
        }
        PlayerDataAccess data = new PlayerDataAccess();

        data.name = player.getName();
        data.group = player.getGroup().getName();
        data.prefix = player.getColor();
        data.isMuted = player.isMuted();
        try {
            Database.get().insert(data);
        } catch (DatabaseWriteException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }
    }

    /**
     * Used to update a player. This can not override existing player entries.
     * If there is a player with the same name, nothing will happen
     * @param name
     */
    public void addUser(String name, String group) {
        if (userExists(name)) {
            Canary.logWarning("Player " + name + " already exists. Skipping!");
            return;
        }
        PlayerDataAccess data = new PlayerDataAccess();

        data.name = name;
        data.group = group;
        data.prefix = null;
        data.isMuted = false;
        try {
            Database.get().insert(data);
        } catch (DatabaseWriteException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }

    }

    /**
     * Get whether a user exists
     * @param player Player to check if they exist.
     * @return true if user exists, false otherwise
     */
    private boolean userExists(String player) {
        PlayerDataAccess data = new PlayerDataAccess();

        try {
            Database.get().load(data, new String[] { "name"}, new Object[] { player});
        } catch (DatabaseReadException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }

        return data.hasData();
    }

    /**
     * Remove a player from the data source
     *
     * @param player Player to remove from the data source.
     */
    public void removeUser(String player) {
        try {
            Database.get().remove("player", new String[] { "name"}, new Object[] { player});
        } catch (DatabaseWriteException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }
    }

    /**
     * Update a Player.
     *
     * @param player Player to update to the data source.
     */
    public void updatePlayer(Player player) {
        PlayerDataAccess data = new PlayerDataAccess();

        data.name = player.getName();
        data.group = player.getGroup().getName();
        data.prefix = player.getColor();
        data.isMuted = player.isMuted();
        try {
            Database.get().update(data, new String[] { "name"}, new Object[] { player.getName()});
        } catch (DatabaseWriteException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }
    }

    /**
     * Update an offline player
     * @param player
     */
    public void updatePlayer(OfflinePlayer player) {
        PlayerDataAccess playerData = new PlayerDataAccess();

        try {
            Database.get().load(playerData, new String[]{"name"}, new Object[]{player.getName()});
        }
        catch (DatabaseReadException e) {
            Canary.logStackTrace(e.getCause().getMessage(), e);
        }
        if(!playerData.hasData()) {
            return;
        }
        playerData.group = player.getGroup().getName();
        playerData.isMuted = player.isMuted();
        playerData.prefix = player.getPrefix();
        try {
            Database.get().update(playerData, new String[]{"name"}, new Object[]{player.getName()});
        } catch (DatabaseWriteException e) {
            Canary.logStackTrace(e.getCause().getMessage(), e);
        }
    }

    /**
     * Load and return String array sets.
     * Each Array in the hashMap value has prefix, group and isMuted for a player, in that order.
     *
     * @return A hashmap with a key of player name, and string array value with
     * a prefix and group for a player, in that order.
     */
    public HashMap<String, String[]> loadUsers() {
        HashMap<String, String[]> players = new HashMap<String, String[]>();
        ArrayList<DataAccess> daos = new ArrayList<DataAccess>();

        try {
            Database.get().loadAll(new PlayerDataAccess(), daos, new String[] {}, new Object[] {});
            for (DataAccess dao : daos) {
                PlayerDataAccess data = (PlayerDataAccess) dao;
                String[] row = new String[3];

                row[0] = data.prefix;
                row[1] = data.group;
                row[2] = Boolean.toString(data.isMuted);
                players.put(data.name, row);
            }
            return players;
        } catch (DatabaseReadException e) {
            Canary.logStackTrace(e.getMessage(), e);
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
            Canary.logStackTrace(e.getMessage(), e);
        }
    }
}
