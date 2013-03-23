package net.canarymod.backbone;

import java.util.ArrayList;
import net.canarymod.Logman;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.database.DataAccess;
import net.canarymod.database.Database;
import net.canarymod.database.DatabaseReadException;
import net.canarymod.database.DatabaseWriteException;
import net.canarymod.permissionsystem.PermissionNode;
import net.canarymod.permissionsystem.PermissionProvider;
import net.canarymod.user.Group;

/**
 * Backbone to the permissions System. This contains NO logic, it is only the
 * data source access!
 *
 * @author Chris Ksoll
 *
 */
public class BackbonePermissions extends Backbone {

    public BackbonePermissions() {
        super(Backbone.System.PERMISSIONS);
    }

    /**
     * Load permissions for a group
     * @param name the group name
     * @return
     */
    public PermissionProvider loadGroupPermissions(String name) {
        // Database.get().remove("permission", new String[] {"owner", "type"}, new Object[] {group.getName(), "group"});
        PermissionProvider provider = new PermissionProvider();
        ArrayList<DataAccess> dataList = new ArrayList<DataAccess>();
        try {
            Database.get().loadAll(new PermissionAccess(), dataList, new String[]{"owner", "type"}, new Object[]{name, "group"});
            for(DataAccess da : dataList) {
                PermissionAccess data = (PermissionAccess) da;
                provider.addPermission(data.path, data.value, data.id);
            }

            provider.setOwner(name);
            provider.setType(false);
        } catch (DatabaseReadException e) {
            Logman.logStackTrace(e.getMessage(), e);
        }

        return provider;
    }

    /**
     * Load permissions for a player
     * @param name
     * @return
     */
    public PermissionProvider loadPlayerPermissions(String name) {
     // Database.get().remove("permission", new String[] {"owner", "type"}, new Object[] {group.getName(), "group"});
        PermissionProvider provider = new PermissionProvider();
        ArrayList<DataAccess> dataList = new ArrayList<DataAccess>();
        try {
            Database.get().loadAll(new PermissionAccess(), dataList, new String[]{"owner", "type"}, new Object[]{name, "player"});
            for(DataAccess da : dataList) {
                PermissionAccess data = (PermissionAccess) da;
                provider.addPermission(data.path, data.value, data.id);
            }

            provider.setOwner(name);
            provider.setType(true);
        } catch (DatabaseReadException e) {
            Logman.logStackTrace(e.getMessage(), e);
        }

        return provider;
    }

    /**
     * Saves group permissions. This also adds new permissions + relations if there are any and
     * and updates existing ones
     * @param g
     */
    public void saveGroupPermissions(Group g) {
        PermissionProvider permissions = g.getPermissionProvider();
        ArrayList<PermissionNode> permissionList = permissions.getPermissionMap();
        try {
            for(PermissionNode node : permissionList) {
                ArrayList<PermissionNode> childs = new ArrayList<PermissionNode>();
                for(PermissionNode child : permissions.getChildNodes(node, childs)) {

                    PermissionAccess data = new PermissionAccess();
                    Database.get().load(data, new String[]{"id"}, new Object[]{child.getId()});
                    if(data.hasData()) {
                        data.path = child.getFullPath();
                        data.value = child.getValue();
                        Database.get().update(data, new String[]{"id"}, new Object[]{child.getId()});
                    }
                    else {
                        data.owner = g.getName();
                        data.path = child.getFullPath();
                        data.type = "group";
                        data.value = child.getValue();
                        Database.get().insert(data);
                    }
                }
            }
        }
        catch(DatabaseWriteException e) {
            Logman.logStackTrace(e.getMessage(), e);
        }
        catch (DatabaseReadException e) {
            Logman.logStackTrace(e.getMessage(), e);
        }

    }

    /**
     * Save user permissions to file and add new ones if needed + update relations
     * @param p
     */
    public void saveUserPermissions(Player p) {
        PermissionProvider permissions = p.getPermissionProvider();
        ArrayList<PermissionNode> permissionList = permissions.getPermissionMap();
        try {
            for(PermissionNode node : permissionList) {
                ArrayList<PermissionNode> childs = new ArrayList<PermissionNode>();
                for(PermissionNode child : permissions.getChildNodes(node, childs)) {

                    PermissionAccess data = new PermissionAccess();
                    Database.get().load(data, new String[]{"id"}, new Object[]{child.getId()});
                    if(data.hasData()) {
                        data.path = child.getFullPath();
                        data.value = child.getValue();
                        Database.get().update(data, new String[]{"id"}, new Object[]{child.getId()});
                    }
                    else {
                        data.owner = p.getName();
                        data.path = child.getFullPath();
                        data.type = "player";
                        data.value = child.getValue();
                        Database.get().insert(data);
                    }
                }
            }
        }
        catch(DatabaseWriteException e) {
            Logman.logStackTrace(e.getMessage(), e);
        }
        catch (DatabaseReadException e) {
            Logman.logStackTrace(e.getMessage(), e);
        }
    }

    /**
     * Remove a permission from database. This also removes any relations to groups and players
     * @param path
     */
    public void removePermission(String path) {
        try {
            Database.get().remove("permission", new String[]{"path"}, new Object[]{path});
        } catch (DatabaseWriteException e) {
            Logman.logStackTrace(e.getMessage(), e);
        }
    }

    /**
     * Add a new Permission to database and return its proper object.
     * If the permission already exists, it will return the existing permission node
     * @param path
     * @param value
     * @return
     */
    public int addPermission(String path, boolean value, String owner, String type) {
        PermissionAccess data = new PermissionAccess();
        data.path = path;
        data.value = value;
        data.owner = owner;
        data.type = type;

        try {
            Database.get().insert(data);
            Database.get().load(data, new String[]{"path", "value"}, new Object[]{path, value});
            return data.id;
        } catch (DatabaseWriteException e) {
            Logman.logStackTrace(e.getMessage(), e);
        } catch (DatabaseReadException e) {
            Logman.logStackTrace(e.getMessage(), e);
        }
        return data.id;
    }

    /**
     * Creates a range of default permissions for the defalt groups defined in BackboneGroups
     */
    public static void createDefaultPermissionSet() {
        PermissionAccess admin = new PermissionAccess();
        PermissionAccess mods = new PermissionAccess();
        PermissionAccess players = new PermissionAccess();

        admin.owner = "admins";
        admin.type = "group";
        admin.path = "*";
        admin.value = true;

        mods.owner = "mods";
        mods.type = "group";
        mods.path = "canary.super.ignoreRestrictions";
        mods.value = true;

        players.owner = "players";
        players.type = "group";
        players.path = "canary.world.build";
        players.value = true;

        try {
            Database.get().insert(admin);
            Database.get().insert(mods);
            Database.get().insert(players);
        } catch (DatabaseWriteException e) {
            Logman.logStackTrace(e.getMessage(), e);
        }
    }
}
