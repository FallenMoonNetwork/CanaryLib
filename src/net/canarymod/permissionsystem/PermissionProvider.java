package net.canarymod.permissionsystem;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * Manages and provides permissions. Handles permission queries. This class can
 * and should be instantiated per group and if player has custom permissions set
 * also for that player. It comes with a permission cache to speed up permission
 * lookups
 * 
 * @author Chris
 * 
 */
public class PermissionProvider {
    private HashMap<String, PermissionNode> permissions;
    private HashMap<String, Boolean> permissionCache = new HashMap<String, Boolean>(35);

    /**
     * Instantiate with a given list of nodes.
     * 
     * @param nodes
     */
    public PermissionProvider(HashMap<String, PermissionNode> nodes) {
        if (nodes == null) {
            throw new IllegalArgumentException("PermissionProvider: given permissions list cannot be null! Use the default constructor instead!");
        }
        this.permissions = nodes;
    }

    public PermissionProvider() {
        permissions = new HashMap<String, PermissionNode>();
    }

    /**
     * Add a given permission to the permissions cache. The cache is limited and
     * will prune itself if it gets too big. TODO: Implement something else than
     * a HashMap to prune more effectively?
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

    /**
     * Add a new permission to the list. This is intelligent and will auto-sort
     * the permission into the tree
     * 
     * @param path
     * @param value
     * @param defaultOnPath
     */
    public void addPermission(String path, boolean value, boolean defaultOnPath, int id) {
        String[] paths = path.split(".");
        PermissionNode query = null;
        for (String node : paths) {
            if (query == null) {
                if (permissions.containsKey(node)) {
                    query = permissions.get(node);
                    continue;
                } else {
                    permissions.put(node, new PermissionNode(node, defaultOnPath, -1));
                    query = permissions.get(node);
                    continue;
                }
            }
            if (!query.hasChildNode(node)) {
                query.addChildNode(node, defaultOnPath, -1);
            }
            query = query.getChildNode(node);
        }
        query.setValue(value);
        query.setId(id);
    }

    /**
     * Execute a query for the given permission path. If the query finds an
     * asterisk permission on its way to the final node, it will exit
     * prematurely with what the asterisk permission value is
     * 
     * @param permission
     * @return boolean value at that path
     */
    public boolean queryPermission(String permission) {
        Boolean b = checkCached(permission);
        if (b != null) {
            return b.booleanValue();
        }
        String[] nodes = permission.split(".");
        PermissionNode currentNode = null;
        for (String node : nodes) {
            if (currentNode == null) {
                if (permissions.containsKey(node)) {
                    currentNode = permissions.get(node);
                    continue;
                } else {
                    return false;
                }
            }
            if (currentNode.isAsterisk()) {
                //Asterisk permission makes us return early for we will allow (or whatever the value of asterisk is) all following nodes
                return currentNode.getValue();
            }
            if (currentNode.hasChildNode(node)) {
                currentNode = currentNode.getChildNode(node);
            }
        }
        addPermissionToCache(permission, currentNode.getValue());
        return currentNode.getValue();
    }
    
    /**
     * Get the HashMap of <String,PermissionNode> this provider is handling
     * @return
     */
    public HashMap<String, PermissionNode> getPermissionMap() {
        return permissions;
    }
}
