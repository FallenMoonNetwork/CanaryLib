package net.canarymod.user;

import java.util.ArrayList;
import java.util.HashMap;

import net.canarymod.api.entity.Player;
import net.canarymod.backbone.BackboneGroups;
import net.canarymod.backbone.BackboneUsers;
import net.canarymod.config.Configuration;
import net.canarymod.database.Database;

public class UserAndGroupsProvider {
    private ArrayList<Group> groups;
    private HashMap<String,String[]> playerData;
    private BackboneGroups backboneGroups;
    private BackboneUsers backboneUsers;

    /**
     * Instantiate a groups provider
     * 
     * @param bone
     * @param type
     */
    public UserAndGroupsProvider(Database database) {
        backboneGroups = new BackboneGroups(database, Configuration.getServerConfig().getDatasourceType());
        backboneUsers = new BackboneUsers(database, Configuration.getServerConfig().getDatasourceType());
        groups = backboneGroups.loadGroups();
        playerData = backboneUsers.loadUsers();
    }

    /**
     * Add a new Group
     * 
     * @param g
     */
    public void addGroup(Group g) {
        if(groupExists(g.name)) {
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
        backboneGroups.removeGroup(g);
        groups.remove(g);
    }

    /**
     * Check if a group by the given name exists
     * 
     * @param name
     * @return
     */
    public boolean groupExists(String name) {
        for (Group g : groups) {
            if (g.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the given group is filed in this groups provider
     * 
     * @param g
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
        return (Group[]) groups.toArray();
    }

    /**
     * Returns group files under the given name or null if group not exists
     * 
     * @param name
     * @return
     */
    public Group getGroup(String name) {
        for (Group g : groups) {
            if (g.name.equals(name)) {
                return g;
            }
        }
        return null;
    }
    
    /**
     * Returns a String array containing data in this order:
     * Prefix, Group, IP list (comma seperated)
     * @param name
     * @return
     */
    public String[] getPlayerData(String name) {
        return playerData.get(name);
    }
    
    /**
     * Add or update the given player
     * @param player
     */
    public void addOrUpdatePlayerData(Player player) {
        backboneUsers.addUser(player);
        String[] content = new String[3];
        content[0] = player.getColor();
        content[1] = player.getGroup().name;
        StringBuilder ips = new StringBuilder();
        for(String ip : player.getAllowedIPs()) {
            ips.append(ip).append(",");
        }
        ips.deleteCharAt(ips.length()-1); //remove last comma
        content[2] = ips.toString();
        playerData.put(player.getName(), content);
    }
    
    /**
     * Remove permissions and other data for this player from database
     * @param player
     */
    public void removeUserData(Player player) {
        backboneUsers.removeUser(player);
    }
}
