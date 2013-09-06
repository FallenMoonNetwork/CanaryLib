package net.canarymod.permissionsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.backbone.PermissionDataAccess;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.database.DataAccess;
import net.canarymod.database.Database;
import net.canarymod.database.exceptions.DatabaseReadException;

/**
 * A PermissionProvider implementation based on PermissionNode objects,
 * with multiworld support
 *
 * @author Chris (damagefilter)
 */
public class MultiworldPermissionProvider implements PermissionProvider {
    private ArrayList<PermissionNode> permissions;
    private HashMap<String, Boolean> permissionCache = new HashMap<String, Boolean>(35);
    private boolean isPlayerProvider;
    private String owner; // This can either be a player or group name
    private String world;
    private PermissionProvider parent = null;

    /**
     * Constructs a new PermissionProvider that's valid for the given world
     *
     * @param world
     */
    public MultiworldPermissionProvider(String world, boolean isPlayer, String owner) {
        this.world = world;
        permissions = new ArrayList<PermissionNode>();
        this.isPlayerProvider = isPlayer;
        this.owner = owner;
        if (world != null) {
            // We need a parent then
            if (isPlayer) {
                this.parent = Canary.permissionManager().getPlayerProvider(owner, null);
            }
            else {
                this.parent = Canary.permissionManager().getGroupsProvider(owner, null);
            }
        }
    }

    /**
     * Add a given permission to the permissions cache. The cache is limited and
     * will prune itself if it gets too big.
     *
     * @param path
     * @param value
     */
    private void addPermissionToCache(String path, boolean value) {
        if (permissionCache.size() > 35) {
            Iterator<Entry<String, Boolean>> it = permissionCache.entrySet().iterator();

            while (it.hasNext() && permissionCache.size() > 10) {
                it.next();
                it.remove();
            }
        }
        permissionCache.put(path, value);
    }

    /**
     * Check the permission cache if we have something already
     *
     * @param permission
     *
     * @return
     */
    private Boolean checkCached(String permission) {
        return permissionCache.get(permission);
    }

    @Override
    public ArrayList<PermissionNode> getChildNodes(PermissionNode node, ArrayList<PermissionNode> childs) {
        childs.add(node);
        if (node.hasChilds()) {
            for (String key : node.getChilds().keySet()) {
                getChildNodes(node.getChilds().get(key), childs);
            }
        }
        return childs;
    }

    /**
     * get a node that must be directly in the permissions list
     *
     * @param name
     *
     * @return
     */
    private PermissionNode getRootNode(String name) {
        for (PermissionNode n : permissions) {
            if (n.getName().equals(name) || n.isAsterisk()) {
                return n;
            }
        }
        return null;
    }

    /**
     * Resolve a path when adding new stuff
     *
     * @param path
     * @param value
     *
     * @return
     */
    private PermissionNode addPath(String[] path, boolean value) {
        PermissionNode node = null;
        boolean newPath = false;

        for (int current = 0; current < path.length; current++) {
            if (current == 0) {
                node = getRootNode(path[current]);
                if (node == null) {
                    newPath = true;
                    node = new PermissionNode(path[current], value);
                    permissions.add(node);
                    continue;
                }
            }
            if (newPath) {
                PermissionNode n = new PermissionNode(path[current], value);
                node.addChildNode(n);
                node = n;
            }
            else {
                if (current + 1 < path.length) {
                    if (node.hasChildNode(path[current + 1])) {
                        node = node.getChildNode(path[current + 1]);
                        if (current + 1 == (path.length - 1)) { // This is the end of the path. Update the value
                            node.setValue(value);
                        }
                    }
                    else {
                        PermissionNode n = new PermissionNode(path[current + 1], value);
                        node.addChildNode(n);
                        node = n;
                    }
                }
            }
        }
        return node;
    }

    /**
     * Resolve the string path and return the result
     *
     * @param path
     *
     * @return
     */
    private boolean resolvePath(String[] path) {
        PermissionNode node = getRootNode(path[0]);
        boolean hasAsterisk = false, asteriskValue = false;

        for (int current = 0; current < path.length; current++) {
            if (node == null) {
                return false;
            }
            if (node.isAsterisk()) {
                // File the value only and continue with resolving
                hasAsterisk = true;
                asteriskValue = node.getValue();
            }
            if (node.hasChildNode("*")) {
                // Register that we had an asterisk on the way, that's all we need to know!
                hasAsterisk = true;
                asteriskValue = node.getChildNode("*").getValue();
            }
            if (current + 1 < path.length) {
                if (node.hasChildNode(path[current + 1])) {
                    node = node.getChildNode(path[current + 1]);
                }
                else {
                    if (hasAsterisk) { // No subsequent nodes, the asterisk value wins
                        return asteriskValue;
                    }
                    // No asterisk was before this point, so it's false
                    return false;
                }
            }
        }
        // Path was fully resolved, check if there was an asterisk on the way
        if (hasAsterisk) {
            // Only use asterisk if there's no overriding value behind it on the path
            if (asteriskValue == node.getValue()) {
                return asteriskValue;
            }
        }
        // No asterisk or asterisk value is not the same.
        // The overriding node will be used
        return node.getValue();
    }

    /**
     * Checks if this permission provider actually has the given path loaded.
     *
     * @param path
     *
     * @return
     */
    private boolean hasPath(String[] path) {
        PermissionNode node = getRootNode(path[0]);

        for (int current = 0; current < path.length; current++) {
            if (current == 0) {
                node = getRootNode("*");
                if (node == null) {
                    node = getRootNode(path[0]);
                }
            }
            if (current + 1 < path.length) {
                if (node == null) {
                    return false;
                }
                if (node.hasChildNode(path[current + 1])) {
                    node = node.getChildNode(path[current + 1]);
                }
                else if (node.hasChildNode("*")) {
                    node = node.getChildNode("*");
                }
            }
        }
        return node != null && (node.getName().equals(path[path.length - 1]) || node.isAsterisk());
    }

    @Override
    public void addPermission(String path, boolean value, int id) {
        String[] paths = path.split("\\.");

        if (paths.length == 0) {
            paths = new String[]{ path }; // we have only one node (root)
        }
        PermissionNode node = addPath(paths, value);

        node.setId(id);
    }

    @Override
    public void addPermission(String path, boolean value) {
        addPermission(path, value, Canary.permissionManager().addPermission(path, value, owner, isPlayerProvider ? "player" : "group", this.world));
        // addPermission(path, value, permissions.size()); //Testing
        flushCache();
    }

    @Override
    public boolean queryPermission(String permission) {
        if (permission.isEmpty() || permission.equals(" ")) {
            return true;
        }
        Boolean b = checkCached(permission);
        if (b != null) {
            return b;
        }
        String[] path = permission.split("\\.");
        if (!this.hasPath(path)) {
            if (parent != null) {
                return parent.queryPermission(permission);
            }
        }
        boolean result = resolvePath(permission.split("\\."));
        addPermissionToCache(permission, result);

        return result;
    }

    @Override
    public boolean pathExists(String permission) {
        return permission.trim().isEmpty() || hasPath(permission.split("\\."));
    }

    @Override
    public void flushCache() {
        permissionCache.clear();
    }

    @Override
    public void reload() {
        permissions.clear();
        permissionCache.clear();
        if (isPlayerProvider) {
            PermissionProvider p = Canary.permissionManager().getPlayerProvider(owner, world);
            permissions = p.getPermissionMap();
        }
        else {
            PermissionProvider p = Canary.permissionManager().getGroupsProvider(owner, world);
            permissions = p.getPermissionMap();
        }
    }

    @Override
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public void setType(boolean isPlayerProvider) {
        this.isPlayerProvider = isPlayerProvider;
    }

    @Override
    public ArrayList<PermissionNode> getPermissionMap() {
        return permissions;
    }

    @Override
    public ArrayList<String> getPermissionsAsStringList() {
        ArrayList<String> list = new ArrayList<String>();

        for (PermissionNode node : permissions) {
            list.add(node.getFullPath());
        }
        return list;
    }

    @Override
    public void printPermissionsToCaller(MessageReceiver caller) {
        PermissionDataAccess data = new PermissionDataAccess(world);
        ArrayList<DataAccess> list = new ArrayList<DataAccess>();
        try {
            Database.get().loadAll(data, list, new String[]{ "owner", "type" }, new Object[]{ this.owner, isPlayerProvider ? "player" : "group" });
        }
        catch (DatabaseReadException e) {
            caller.notice(Translator.translate("no permissions"));
        }
        if (list.size() > 0) {
            for (DataAccess da : list) {
                PermissionDataAccess perm = (PermissionDataAccess) da;
                if (perm.value) {
                    caller.message(Colors.LIGHT_GREEN + perm.path + ": true");
                }
                else {
                    caller.message(Colors.LIGHT_RED + perm.path + ": false");
                }
            }
        }
        else {
            caller.notice(Translator.translate("no permissions"));
        }
    }

    @Override
    public String getWorld() {
        return world;
    }

    @Override
    public PermissionProvider getParent() {
        return parent;
    }
}
