package net.canarymod.permissionsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.backbone.PermissionAccess;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.database.DataAccess;
import net.canarymod.database.Database;
import net.canarymod.database.exceptions.DatabaseReadException;

/**
 * A PermissionProvider implementation based on PermissionNode objects
 * 
 * @author Chris (damagefilter)
 */
public class BasicPermissionProvider implements PermissionProvider {
    private ArrayList<PermissionNode> permissions;
    private HashMap<String, Boolean> permissionCache = new HashMap<String, Boolean>(35);
    private boolean isPlayerProvider;
    private String owner; // This can either be a player or group name

    /**
     * Instantiate with a given list of nodes.
     * 
     * @param nodes
     * @param providerType
     *            True if this is a provider for groups
     * @param owner
     *            The name of the player/group this provider is attached to
     */
    public BasicPermissionProvider(ArrayList<PermissionNode> nodes, boolean providerType, String owner) {
        if (nodes == null) {
            throw new IllegalArgumentException("PermissionProvider: given permissions list cannot be null! Use the default constructor instead!");
        }
        this.permissions = nodes;
        isPlayerProvider = providerType;
        this.owner = owner;
    }

    public BasicPermissionProvider() {
        permissions = new ArrayList<PermissionNode>();
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
        permissionCache.put(path, Boolean.valueOf(value));
    }

    /**
     * Check the permission cache if we have something already
     * 
     * @param permission
     * @return
     */
    private Boolean checkCached(String permission) {
        return permissionCache.get(permission);
    }

    /* (non-Javadoc)
     * @see net.canarymod.permissionsystem.PermissionProvider#getChildNodes(net.canarymod.permissionsystem.PermissionNode, java.util.ArrayList)
     */
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
     * @return
     */
    private boolean resolvePath(String[] path) {
        PermissionNode node = null;

        node = getRootNode(path[0]);
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
     * @return
     */
    private boolean hasPath(String[] path) {
        PermissionNode node = null;

        node = getRootNode(path[0]);

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

    /* (non-Javadoc)
     * @see net.canarymod.permissionsystem.PermissionProvider#addPermission(java.lang.String, boolean, int)
     */
    @Override
    public void addPermission(String path, boolean value, int id) {
        String[] paths = path.split("\\.");

        if (paths.length == 0) {
            paths = new String[]{ path }; // we have only one node (root)
        }
        PermissionNode node = addPath(paths, value);

        node.setId(id);
    }

    /* (non-Javadoc)
     * @see net.canarymod.permissionsystem.PermissionProvider#addPermission(java.lang.String, boolean)
     */
    @Override
    public void addPermission(String path, boolean value) {
        addPermission(path, value, Canary.permissionManager().addPermission(path, value, owner, isPlayerProvider ? "player" : "group"));
        // addPermission(path, value, permissions.size()); //Testing
        flushCache();
    }

    /* (non-Javadoc)
     * @see net.canarymod.permissionsystem.PermissionProvider#queryPermission(java.lang.String)
     */
    @Override
    public boolean queryPermission(String permission) {
        if (permission.isEmpty() || permission.equals(" ")) {
            return true;
        }
        Boolean b = checkCached(permission);

        if (b != null) {
            return b.booleanValue();
        }
        boolean result = resolvePath(permission.split("\\."));
        addPermissionToCache(permission, Boolean.valueOf(result));

        return result;
    }

    /* (non-Javadoc)
     * @see net.canarymod.permissionsystem.PermissionProvider#pathExists(java.lang.String)
     */
    @Override
    public boolean pathExists(String permission) {
        if (permission.isEmpty() || permission.equals(" ")) {
            return true;
        }
        return hasPath(permission.split("\\."));
    }

    /* (non-Javadoc)
     * @see net.canarymod.permissionsystem.PermissionProvider#flushCache()
     */
    @Override
    public void flushCache() {
        permissionCache.clear();
    }

    /* (non-Javadoc)
     * @see net.canarymod.permissionsystem.PermissionProvider#reload()
     */
    @Override
    public void reload() {
        permissions.clear();
        permissionCache.clear();
        if (isPlayerProvider) {
            PermissionProvider p = Canary.permissionManager().getPlayerProvider(owner);
            permissions = (ArrayList<PermissionNode>) p.getPermissionMap();
        }
        else {
            PermissionProvider p = Canary.permissionManager().getGroupsProvider(owner);
            permissions = (ArrayList<PermissionNode>) p.getPermissionMap();
        }
    }

    /* (non-Javadoc)
     * @see net.canarymod.permissionsystem.PermissionProvider#setOwner(java.lang.String)
     */
    @Override
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /* (non-Javadoc)
     * @see net.canarymod.permissionsystem.PermissionProvider#setType(boolean)
     */
    @Override
    public void setType(boolean isPlayerProvider) {
        this.isPlayerProvider = isPlayerProvider;
    }

    /* (non-Javadoc)
     * @see net.canarymod.permissionsystem.PermissionProvider#getPermissionMap()
     */
    @Override
    public ArrayList<PermissionNode> getPermissionMap() {
        return permissions;
    }

    /* (non-Javadoc)
     * @see net.canarymod.permissionsystem.PermissionProvider#getPermissionsAsStringList()
     */
    @Override
    public ArrayList<String> getPermissionsAsStringList() {
        ArrayList<String> list = new ArrayList<String>();

        for (PermissionNode node : permissions) {
            list.add(node.getFullPath());
        }
        return list;
    }

    /* (non-Javadoc)
     * @see net.canarymod.permissionsystem.PermissionProvider#printPermissionsToCaller(net.canarymod.chat.MessageReceiver)
     */
    @Override
    public void printPermissionsToCaller(MessageReceiver caller) {
        PermissionAccess data = new PermissionAccess();
        ArrayList<DataAccess> list = new ArrayList<DataAccess>();
        try {
            Database.get().loadAll(data, list, new String[]{ "owner", "type" }, new Object[]{ this.owner, isPlayerProvider ? "player" : "group" });
        } catch (DatabaseReadException e) {
            caller.notice(Translator.translate("no permissions"));
        }
        if (list.size() > 0) {
            for (DataAccess da : list) {
                PermissionAccess perm = (PermissionAccess) da;
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
}
