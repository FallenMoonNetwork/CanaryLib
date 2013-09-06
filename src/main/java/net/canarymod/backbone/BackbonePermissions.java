package net.canarymod.backbone;

import java.util.ArrayList;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.World;
import net.canarymod.database.DataAccess;
import net.canarymod.database.Database;
import net.canarymod.database.exceptions.DatabaseReadException;
import net.canarymod.database.exceptions.DatabaseWriteException;
import net.canarymod.logger.Logman;
import net.canarymod.permissionsystem.MultiworldPermissionProvider;
import net.canarymod.permissionsystem.PermissionNode;
import net.canarymod.permissionsystem.PermissionProvider;
import net.canarymod.user.Group;

/**
 * Backbone to the permissions System. This contains NO logic, it is only the
 * data source access!
 *
 * @author Chris (damagefilter)
 */
public class BackbonePermissions extends Backbone {

    public BackbonePermissions() {
        super(Backbone.System.PERMISSIONS);
        try {
            for (String fqname : Canary.getServer().getWorldManager().getExistingWorlds()) {
                Database.get().updateSchema(new PermissionDataAccess(fqname));
            }
            Database.get().updateSchema(new PermissionDataAccess(null));
        }
        catch (DatabaseWriteException e) {
            Canary.logStacktrace("Failed to update database schema", e);
        }
    }

    /**
     * Load permissions for a group
     *
     * @param name
     *         the group name
     * @param world
     *         the world name
     *
     * @return PermissionsProvider instance for this group.
     */
    public PermissionProvider loadGroupPermissions(String name, String world) {
        if (world != null && world.isEmpty()) {
            world = null;
        }
        PermissionProvider provider = new MultiworldPermissionProvider(world, false, name);
        ArrayList<DataAccess> dataList = new ArrayList<DataAccess>();
        Logman.println("Loading permissions for " + name + ". World: " + ((world != null && !world.isEmpty()) ? world : "none"));
        try {
            Database.get().loadAll(new PermissionDataAccess(world), dataList, new String[]{ "owner", "type" }, new Object[]{ name, "group" });
            for (DataAccess da : dataList) {
                PermissionDataAccess data = (PermissionDataAccess) da;

                provider.addPermission(data.path, data.value, data.id);
            }
        }
        catch (DatabaseReadException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }

        return provider;
    }

    /**
     * Load permissions for a player
     *
     * @param name
     *         Name of the player.
     * @param world
     *         the world name
     *
     * @return PermissionProvider for this player.
     */
    public PermissionProvider loadPlayerPermissions(String name, String world) {
        if (world != null && world.isEmpty()) {
            world = null;
        }
        // Database.get().remove("permission", new String[] {"owner", "type"}, new Object[] {group.getName(), "group"});
        PermissionProvider provider = new MultiworldPermissionProvider(world, true, name);
        ArrayList<DataAccess> dataList = new ArrayList<DataAccess>();

        try {
            Database.get().loadAll(new PermissionDataAccess(world), dataList, new String[]{ "owner", "type" }, new Object[]{ name, "player" });
            for (DataAccess da : dataList) {
                PermissionDataAccess data = (PermissionDataAccess) da;

                provider.addPermission(data.path, data.value, data.id);
            }
        }
        catch (DatabaseReadException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }

        return provider;
    }

    /**
     * Saves group permissions. This also adds new permissions + relations if there are any and
     * and updates existing ones
     *
     * @param g
     *         Group to save permission from to the database.
     */
    public void saveGroupPermissions(Group g) {
        PermissionProvider permissions = g.getPermissionProvider();
        ArrayList<PermissionNode> permissionList = permissions.getPermissionMap();

        try {
            for (PermissionNode node : permissionList) {
                ArrayList<PermissionNode> childs = new ArrayList<PermissionNode>();

                for (PermissionNode child : permissions.getChildNodes(node, childs)) {

                    PermissionDataAccess data = new PermissionDataAccess(g.getWorldName());

                    Database.get().load(data, new String[]{ "id" }, new Object[]{ child.getId() });
                    if (data.hasData()) {
                        data.path = child.getFullPath();
                        data.value = child.getValue();
                        Database.get().update(data, new String[]{ "id" }, new Object[]{ child.getId() });
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
        catch (DatabaseWriteException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
        catch (DatabaseReadException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }

    }

    /**
     * Save user permissions to file and add new ones if needed + update relations
     *
     * @param p
     *         Player to save permissions for to the database.
     */
    public void saveUserPermissions(Player p) {
        PermissionProvider permissions = p.getPermissionProvider();
        ArrayList<PermissionNode> permissionList = permissions.getPermissionMap();

        try {
            for (PermissionNode node : permissionList) {
                ArrayList<PermissionNode> childs = new ArrayList<PermissionNode>();

                for (PermissionNode child : permissions.getChildNodes(node, childs)) {

                    PermissionDataAccess data = new PermissionDataAccess(permissions.getWorld());

                    Database.get().load(data, new String[]{ "id" }, new Object[]{ child.getId() });
                    if (data.hasData()) {
                        data.path = child.getFullPath();
                        data.value = child.getValue();
                        Database.get().update(data, new String[]{ "id" }, new Object[]{ child.getId() });
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
        catch (DatabaseWriteException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
        catch (DatabaseReadException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
    }

    /**
     * Remove a permission from database. This also removes any relations to groups and players
     *
     * @param path
     *         String representation of the permission to remove.<br>
     *         EXAMPLE: "canary.command.player.compass"
     * @param world
     *         The fully qualified world name as given by {@link World#getFqName()}<br>
     *         Can be null to access the global permissions table.
     */
    public void removePermission(String path, String world) {
        try {
            if (world != null) {
                Database.get().remove("permission_" + world, new String[]{ "path" }, new Object[]{ path });
            }
            else {
                Database.get().remove("permission", new String[]{ "path" }, new Object[]{ path });
            }
        }
        catch (DatabaseWriteException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
    }

    /**
     * Removes a permission specific to a player or group
     *
     * @param path
     *         the permission node
     * @param subject
     *         the name of the subject (either group or player name)
     * @param world
     *         The fully qualified world name as given by {@link World#getFqName()}<br>
     *         Can be null to access the global permissions table.
     * @param isPlayer
     *         {@code true} if player; {@code false} if not
     */
    public void removePermission(String path, String subject, String world, boolean isPlayer) {
        try {
            if (isPlayer) {
                if (world != null) {
                    Database.get().remove("permission_" + world, new String[]{ "path", "type", "owner" }, new Object[]{ path, "player", subject });
                }
                else {
                    Database.get().remove("permission", new String[]{ "path", "type", "owner" }, new Object[]{ path, "player", subject });
                }
            }
            else {
                if (world != null) {
                    Database.get().remove("permission_" + world, new String[]{ "path", "type", "owner" }, new Object[]{ path, "group", subject });
                }
                else {
                    Database.get().remove("permission", new String[]{ "path", "type", "owner" }, new Object[]{ path, "group", subject });
                }
            }
        }
        catch (DatabaseWriteException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
    }

    /**
     * Add a new Permission to database and return its proper object.
     * If the permission already exists, it will be updated instead and the appropriate ID will be returned.
     *
     * @param path
     *         String representation of the permission to add.<br>
     *         EXAMPLE: "canary.command.player.compass"
     * @param value
     *         Whether permission is true or false.
     * @param owner
     *         Name of the owner. Can be a player or a group name.
     * @param type
     *         "player" or "group".
     * @param world
     *         The fully qualified world name as given by {@link World#getFqName()}<br>
     *         Can be null to access the global permissions table.
     *
     * @return The ID for the permission.
     */
    public int addPermission(String path, boolean value, String owner, String type, String world) {
        if (pathExists(path, owner, type, world)) {
            return updatePermission(path, owner, type, world, value);
        }
        PermissionDataAccess data = new PermissionDataAccess(world);

        data.path = path;
        data.value = value;
        data.owner = owner;
        data.type = type;

        try {
            Database.get().insert(data);
            Database.get().load(data, new String[]{ "path", "owner", "type" }, new Object[]{ path, owner, type });
            return data.id;
        }
        catch (DatabaseWriteException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
        catch (DatabaseReadException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
        return data.id;
    }

    /**
     * Update a permission node with the given values.
     * The values given must clearly identify the node to update.
     *
     * @param path
     *         String representation of the permission to add.<br>
     *         EXAMPLE: "canary.command.player.compass"
     * @param owner
     *         Name of the owner. Can be a player or a group name.
     * @param type
     *         "player" or "group".
     * @param world
     *         The fully qualified world name as given by {@link World#getFqName()}<br>
     *         Can be null to access the global permissions table.
     * @param value
     *         True or false
     *
     * @return The ID of the updated permission
     */
    public int updatePermission(String path, String owner, String type, String world, boolean value) {
        PermissionDataAccess data = new PermissionDataAccess(world);
        try {
            Database.get().load(data, new String[]{ "path", "owner", "type" }, new Object[]{ path, owner, type });
            if (!data.hasData()) {
                throw new DatabaseReadException("Could not load a permission path! (" + path + ")");
            }
            data.value = value;
            Database.get().update(data, new String[]{ "path", "owner", "type" }, new Object[]{ path, owner, type });
        }
        catch (DatabaseReadException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
        catch (DatabaseWriteException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
        return data.id;
    }

    public boolean pathExists(String path, String owner, String type, String world) {
        PermissionDataAccess data = new PermissionDataAccess(world);

        try {
            Database.get().load(data, new String[]{ "path", "owner", "type" }, new Object[]{ path, owner, type });
        }
        catch (DatabaseReadException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
        return data.hasData();
    }

    /** Creates a range of default permissions for the default groups defined in BackboneGroups */
    public static void createDefaultPermissionSet() {
        PermissionDataAccess admin = new PermissionDataAccess(null);
        PermissionDataAccess mods = new PermissionDataAccess(null);
        PermissionDataAccess players = new PermissionDataAccess(null);

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
        }
        catch (DatabaseWriteException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
    }
}
