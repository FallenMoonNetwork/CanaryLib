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
    public void addPermission(String path, boolean value, int id) {
        String[] paths = path.split("\\.");
        if(paths.length == 0) {
            paths = new String[]{path}; //we have only one node (root)
        }
        PermissionNode query = null;
        for (String node : paths) {
            if (query == null) {
                if (permissions.containsKey(node)) {
                    query = permissions.get(node);
                    continue;
                } else {
                    permissions.put(node, new PermissionNode(node, value, -1));
                    query = permissions.get(node);
                    continue;
                }
            }
            if (!query.hasChildNode(node)) {
                //Set query on path true to allow access to child nodes
                if(!query.getValue() && value) {
                    query.setValue(true);
                }
                query.addChildNode(node, value, -1);
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
        String[] nodes = permission.split("\\.");
        PermissionNode currentNode = null;
        for(String key : permissions.keySet()) {
            currentNode = permissions.get(key);
            boolean found = false;
            if(currentNode.getName().equals(nodes[0]) || currentNode.isAsterisk()) {
                for(String node : nodes) {
                    if(currentNode.isAsterisk()) {
                        found = true;
                        break;
                    }
                    if(currentNode.hasChildNode(node)) {
                        currentNode = currentNode.getChildNode(node);
                    }
                    else {
                        if(currentNode.getName().equals(nodes[nodes.length-1])) {
                            //This is the last node in line, we found it!
                            found = true;
                            break;
                        }
                        else {
                            return false; //node does not exist. Eval false
                        }
                    }
                }
            }
            if(found) {
                break;
            }
        }
        //The permission set was empty
        if(currentNode == null) {
            return false;
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
