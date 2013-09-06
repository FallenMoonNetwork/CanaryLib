package net.canarymod.permissionsystem;

import java.util.ArrayList;

import net.canarymod.chat.MessageReceiver;

/**
 * Manages and provides permissions. Handles permission queries. The implementing class can
 * and should be instantiated per group and if player has custom permissions set
 * also for that player.
 *
 * @author Chris (damagefilter)
 */
public interface PermissionProvider {

    /**
     * Put all child nodes and childs of childs etc into one arrayList
     *
     * @param node
     * @param childs
     *
     * @return
     */
    public ArrayList<PermissionNode> getChildNodes(PermissionNode node, ArrayList<PermissionNode> childs);

    /**
     * Add a new permission to the list. This is intelligent and will auto-sort
     * the permission into the tree. If you don't have the permission ID, do not use this!
     *
     * @param path
     * @param value
     * @param id
     */
    public void addPermission(String path, boolean value, int id);

    /**
     * This adds a new permission into this provider, also creating a new entry in the database.
     * If the provided permission already exists in the DB, it's being updated.
     * If the path already exists within this provider, it will be updated if required
     *
     * @param path
     * @param value
     */
    public void addPermission(String path, boolean value);

    /**
     * Execute a query for the given permission path. If the query finds an
     * asterisk permission on its way to the final node, it will exit
     * prematurely with what the asterisk permission value is
     *
     * @param permission
     *
     * @return boolean value at that path
     */
    public boolean queryPermission(String permission);

    public boolean pathExists(String permission);

    /** Clears the permission cache */
    public void flushCache();

    /** Reload the permissions */
    public void reload();

    public void setOwner(String owner);

    public void setType(boolean isPlayerProvider);

    /**
     * Get the List of permission root nodes this provider is handling
     *
     * @return
     */
    public ArrayList<PermissionNode> getPermissionMap();

    /**
     * Returns a List of Strings with the full permission node paths contained in this provider.
     * Mostly used for storing this into a database
     *
     * @return
     */
    public ArrayList<String> getPermissionsAsStringList();

    /**
     * Print out the list of the permissions filed in this provider in a human readable way to the given caller
     *
     * @param caller
     */
    public void printPermissionsToCaller(MessageReceiver caller);

    /**
     * get the world name for this permission provider.
     * This is null if the permission provider is global (valid in all worlds)
     *
     * @return
     */
    public String getWorld();

    /**
     * Returns the parent permission provider. usually this is the global provider.
     * Returns null if this PermissionProvider instance has no parent.
     * Default CanaryMod logic mandates that only world-specific providers
     * may have a parent - a global provider.
     *
     * @return
     */
    public PermissionProvider getParent();

}
