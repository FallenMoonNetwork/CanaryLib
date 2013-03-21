package net.canarymod.permissionsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import net.canarymod.Canary;

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
    private ArrayList<PermissionNode> permissions;
    private HashMap<String, Boolean> permissionCache = new HashMap<String, Boolean>(35);
    private boolean isPlayerProvider;
    private String owner; //This can either be a player or group name

    /**
     * Instantiate with a given list of nodes.
     * 
     * @param nodes
     * @param providerType True if this is a provider for groups
     * @param owner The name of the player/group this provider is attached to
     */
    public PermissionProvider(ArrayList<PermissionNode> nodes, boolean providerType, String owner) {
        if (nodes == null) {
            throw new IllegalArgumentException("PermissionProvider: given permissions list cannot be null! Use the default constructor instead!");
        }
        this.permissions = nodes;
        isPlayerProvider = providerType;
        this.owner = owner;
    }

    public PermissionProvider() {
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
    
    /**
     * Get the the permission node with this name. This will search in child nodes too.
     * @param name
     * @return
     */
    private PermissionNode getNode(String name) {
        for(PermissionNode n : permissions) {
            if(n.getName().equals(name) || n.isAsterisk()) {
                return n;
            }
            else {
                ArrayList<PermissionNode> childs = getChildNodes(n, new ArrayList<PermissionNode>());
                for(PermissionNode nn : childs) {
                    if(nn.getName().equals(name) || nn.isAsterisk()) {
                        return nn;
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * Put all child nodes and childs of childs etc into one arrayList
     * @param node
     * @param childs
     * @return
     */
    public ArrayList<PermissionNode> getChildNodes(PermissionNode node, ArrayList<PermissionNode> childs) {
        childs.add(node);
        if(node.hasChilds()) {
            for(String key : node.getChilds().keySet()) {
                getChildNodes(node.getChilds().get(key), childs);
            }
        }
        return childs;
    }
    
    /**
     * get a node that must be directly in the permissions list
     * @param name
     * @return
     */
    private PermissionNode getRootNode(String name) {
        for(PermissionNode n : permissions) {
            if(n.getName().equals(name) || n.isAsterisk()) {
                return n;
            }
        }
        return null;
    }
    
    /**
     * Resolve a path when adding new stuff
     * @param path
     * @param value
     * @return
     */
    private PermissionNode addPath(String[] path, boolean value) {
        PermissionNode node = null;
        PermissionNode parent = null;
        for(int current = 0; current < path.length; current++) {
            node = getNode(path[current]);
            if(current == 0) {
                //Node MUST be a root!
                if(node == null) {
                    //Node does not exist, add a new root.
                    node = new PermissionNode(path[current], value);
                    permissions.add(node);
                }
                if(current+1 < path.length ) {
                    if(!node.hasChildNode(path[current+1])) {
                        node.addChildNode(path[current+1], value);
                    }
                }
                
            }
            else {
                if(node == null) {
                    if(!parent.hasChildNode(path[current])) {
                        node = new PermissionNode(path[current], value);
                        parent.addChildNode(node);
                    }
                    node = parent.getChildNode(path[current]);
                }
                if(current+1 < path.length) {
                    if(!node.hasChildNode(path[current+1])) {
                        node.addChildNode(path[current+1], value);
                    }
                }
            }

            parent = node;
        }
        return node;
    }
    /**
     * Resolve the string path and return the result
     * @param path
     * @return
     */
    private boolean resolvePath(String[] path) {
        PermissionNode node = null;
        node = getRootNode(path[0]);
        boolean hasAsterisk = false, asteriskValue = false;
        for(int current = 0; current < path.length; current++) {
            if(node == null) {
                return false; 
            }
            if(node.isAsterisk()) {
                //File the value only and continue with resolving
                hasAsterisk = true;
                asteriskValue = node.getValue();
            }
            if(node.hasChildNode("*")) {
                //Register that we had an asterisk on the way, that's all we need to know!
                hasAsterisk = true;
                asteriskValue = node.getChildNode("*").getValue();
            }
            if(current+1 < path.length) {
                if(node.hasChildNode(path[current+1])) {
                    node = node.getChildNode(path[current+1]);
                }
                else {
                    if(hasAsterisk) { //No subsequent nodes, the asterisk value wins
                        return asteriskValue;
                    }
                    //No asterisk was before this point, so it's false
                    return false;
                }
            }
        }
        //Path was fully resolved, check if there was an asterisk on the way
        if(hasAsterisk) {
            //Only use asterisk if there's no overriding value behind it on the path
            if(asteriskValue == node.getValue()) {
                return asteriskValue;
            }
        }
        //No asterisk or asterisk value is not the same.
        //The overriding node will be used
        return node.getValue();
    }

    /**
     * Add a new permission to the list. This is intelligent and will auto-sort
     * the permission into the tree. If you don't have the permission ID, do not use this!
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
        PermissionNode node = addPath(paths, value);
        node.setId(id);
    }
    
    /**
     * This adds a new permission into this provider, also creating a new entry in the database.
     * If the provided permission already exists in the DB, it's beeing updated
     * @param path
     * @param value
     */
    public void addPermission(String path, boolean value) {
        addPermission(path, value, Canary.permissionManager().addPermission(path, value, owner, isPlayerProvider ? "player" : "group"));
//        addPermission(path, value, permissions.size()); //Testing
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
        boolean result = resolvePath(permission.split("\\."));
        addPermissionToCache(permission, Boolean.valueOf(result));
        return result;
    }
    
    /**
     * Clears the permission cache
     */
    public void flushCache() {
        permissionCache.clear();
    }
    
    /**
     * Reload the permissions 
     */
    public void reload() {
        permissions.clear();
        permissionCache.clear();
        if(isPlayerProvider) {
            PermissionProvider p = Canary.permissionManager().getPlayerProvider(owner);
            permissions = p.getPermissionMap();
        }
        else {
            PermissionProvider p = Canary.permissionManager().getGroupsProvider(owner);
            permissions = p.getPermissionMap();
        }
    }
    
    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    public void setType(boolean isPlayerProvider) {
        this.isPlayerProvider = isPlayerProvider;
    }
    /**
     * Get the List of permission root nodes this provider is handling
     * @return
     */
    public ArrayList<PermissionNode> getPermissionMap() {
        return permissions;
    }
    
    /**
     * Returns a List of Strings with the full permission node paths contained in this provider.
     * Mostly used for storing this into a database
     * @return
     */
    public ArrayList<String> getPermissionsAsStringList() {
        ArrayList<String> list = new ArrayList<String>();
        for(PermissionNode node : permissions) {
            list.add(node.getFullPath());
        }
        return list;
    }
}
