package net.canarymod.user;

import java.util.ArrayList;
import java.util.HashMap;

import net.canarymod.Canary;
import net.canarymod.api.OfflinePlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.backbone.BackboneGroups;
import net.canarymod.backbone.BackboneUsers;

/**
 * Access to the backbone for users and groups
 *
 * @author Chris (damagefilter)
 */
public class UserAndGroupsProvider {
    private ArrayList<Group> groups;
    private HashMap<String, String[]> playerData;
    private BackboneGroups backboneGroups;
    private BackboneUsers backboneUsers;
    private Group defaultGroup;

    /**
     * Instantiate a groups provider
     *
     * @param bone
     * @param type
     */
    public UserAndGroupsProvider() {
        backboneGroups = new BackboneGroups();
        backboneUsers = new BackboneUsers();
        initGroups();
        initPlayers();

    }

    private void initGroups() {
        groups = backboneGroups.loadGroups();
        if (groups.isEmpty()) {
            BackboneGroups.createDefaults();
            // Load again
            groups = backboneGroups.loadGroups();
        }
        // Add permission sets to groups
        ArrayList<Group> groups = new ArrayList<Group>();

        for (Group g : this.groups) {
            // g.setPermissionProvider(new PermissionManager().getGroupsProvider(g.getName(), g.getWorldName())); // Need to do this here because Canary isn't ready at this time
            g.setPermissionProvider(Canary.permissionManager().getGroupsProvider(g.getName(), g.getWorldName()));
            groups.add(g);
        }
        this.groups = groups;

        // find default group
        for (Group g : groups) {
            if (g.isDefaultGroup()) {
                defaultGroup = g;
                break;
            }
        }
        if (defaultGroup == null) {
            throw new IllegalStateException("No default group defined! Please define a default group!");
        }
    }

    private void initPlayers() {
        playerData = new BackboneUsers().loadUsers();
        if (playerData.size() == 0) {
            BackboneUsers.createDefaults();
            playerData = new BackboneUsers().loadUsers();
        }
    }

    /**
     * Add a new Group
     *
     * @param g
     */
    public void addGroup(Group g) {
        if (groupExists(g.getName())) {
            backboneGroups.updateGroup(g);
        }
        else {
            backboneGroups.addGroup(g);
        }
        groups.add(g);
    }

    /**
     * Remove this group
     *
     * @param g
     */
    public void removeGroup(Group g) {
        // Move children up to the next parent
        for (Group child : g.getChildren()) {
            child.setParent(g.getParent());
        }
        // Now we can safely remove the group
        backboneGroups.removeGroup(g);
        groups.remove(g);
    }

    /**
     * Rename a group
     *
     * @param group
     *         Group in question
     * @param newName
     *         the new name
     */
    public void renameGroup(Group group, String newName) {
        groups.remove(group);
        backboneGroups.renameGroup(group, newName);
        groups.add(group);
        for (Group g : groups) {
            updateGroup(g);
        }
    }

    /**
     * Check if a group by the given name exists
     *
     * @param name
     *
     * @return
     */
    public boolean groupExists(String name) {
        for (Group g : groups) {
            if (g.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the given group is filed in this groups provider
     *
     * @param g
     *
     * @return
     */
    public boolean groupExists(Group g) {
        return groups.contains(g);
    }

    /**
     * Return array of all existent groups
     *
     * @return
     */
    public Group[] getGroups() {
        Group[] grp = new Group[groups.size()];

        return groups.toArray(grp);
    }

    /**
     * Returns group files under the given name or the default group if the specified one doesn't exist
     *
     * @param name
     *
     * @return
     */
    public Group getGroup(String name) {
        if (name == null || name.isEmpty()) {
            return defaultGroup;
        }
        for (Group g : groups) {
            if (g.getName().equals(name)) {
                return g;
            }
        }
        return defaultGroup;
    }

    /**
     * Get the default group
     *
     * @return default Group object
     */
    public Group getDefaultGroup() {
        return this.defaultGroup;
    }

    /**
     * Returns a String array containing data in this order:
     * Prefix, Group, isMuted
     *
     * @param name
     *
     * @return
     */
    public String[] getPlayerData(String name) {
        String[] data = playerData.get(name);

        if (data == null) {
            data = new String[3];
            data[0] = null;
            data[1] = defaultGroup.getName();
            data[2] = null;
        }
        return data;
    }

    /**
     * Get the names of all players in the user table
     *
     * @return
     */
    public String[] getPlayers() {
        String[] retT = { };

        return backboneUsers.loadUsers().keySet().toArray(retT);
    }

    /**
     * Add or update the given player
     *
     * @param player
     */
    public void addOrUpdatePlayerData(Player player) {
        backboneUsers.addUser(player);
        String[] content = new String[3];
        String prefix = player.getPrefix();
        if (prefix.equals(player.getGroup().getPrefix())) {
            content[0] = null;
        }
        else {
            content[0] = prefix;
        }
        content[1] = player.getGroup().getName();
        content[2] = Boolean.toString(player.isMuted());
        playerData.put(player.getName(), content);
    }

    /**
     * Add a player that is currently offline.
     * It will assume default values for any unspecified data
     *
     * @param name
     * @param group
     */
    public void addOfflinePlayer(String name, String group) {
        backboneUsers.addUser(name, group);
        String[] content = new String[3];
        content[0] = null;
        content[1] = group;
        content[2] = Boolean.toString(false);
        playerData.put(name, content);
    }

    public void addOrUpdateOfflinePlayer(OfflinePlayer player) {
        if (!playerData.containsKey(player.getName())) {
            addOfflinePlayer(player.getName(), player.getGroup().getName());
        }
        else {
            backboneUsers.updatePlayer(player);
            playerData.remove(player.getName());
            String[] data = new String[3];
            String prefix = player.getPrefix();
            if (prefix.equals(player.getGroup().getPrefix())) {
                data[0] = null;
            }
            else {
                data[0] = prefix;
            }
            data[1] = player.getGroup().getName();
            data[2] = Boolean.toString(player.isMuted());
            playerData.put(player.getName(), data);
        }
    }

    public void updateGroup(Group g) {
        backboneGroups.updateGroup(g);
        reloadGroups();
    }

    /**
     * Remove permissions and other data for this player from database
     *
     * @param player
     */
    public void removeUserData(String player) {
        backboneUsers.removeUser(player);
    }

    public void reloadUsers() {
        playerData.clear();
        playerData = backboneUsers.loadUsers();
    }

    public void reloadGroups() {
        groups.clear();
        initGroups();
        // Update players with new group data
        for (Player player : Canary.getServer().getPlayerList()) {
            player.initPlayerData();
        }
    }

    public void reloadAll() {
        reloadUsers();
        reloadGroups();
    }

    /**
     * Returns all additional groups for a player
     *
     * @param player
     *
     * @return
     */
    public Group[] getModuleGroupsForPlayer(String player) {
        return backboneUsers.getModularGroups(player);
    }
}
