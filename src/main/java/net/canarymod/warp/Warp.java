package net.canarymod.warp;

import java.util.ArrayList;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.position.Location;
import net.canarymod.user.Group;

/**
 * Contains information regarding warp points
 *
 * @author Chris (damagefilter)
 */
public class Warp {

    private Group[] allowedGroups = null;
    private String name;
    private boolean isPlayerHome = false;
    private String owner;
    private Location location;

    /**
     * Set up a group specific warp
     *
     * @param l
     * @param groups
     * @param name
     */
    public Warp(Location l, Group[] groups, String name) {
        location = l;
        allowedGroups = groups;
        this.name = name;
        isPlayerHome = false;
        owner = null;
    }

    /**
     * Set up a normal warp, access for all
     *
     * @param l
     * @param name
     */
    public Warp(Location l, String name) {
        location = l;
        this.name = name;
        isPlayerHome = false;
        owner = null;
        allowedGroups = null;
    }

    public Warp(Location l, String name, String owner, boolean isHome) {
        location = l;
        this.name = name;
        this.owner = owner;
        isPlayerHome = isHome;
        allowedGroups = null;
    }

    /**
     * Get the warps name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Get owners name. May return null if this is no private home warp
     *
     * @return
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Get the warp location.
     *
     * @return Location object
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Override the warp location
     *
     * @param l
     */
    public void setLocation(Location l) {
        this.location = l;
    }

    /**
     * Check preconditions and warp a player to this warps location
     *
     * @param player
     *
     * @return True if warped, false otherwise
     */
    public boolean warp(Player player) {
        if (owner != null) {
            if (player.getName().equals(owner) || (player.isAdmin() || player.hasPermission("canary.command.warp.admin"))) {
                player.teleportTo(location);
                return true;
            }
            else {
                return false;
            }
        }
        else {
            if (allowedGroups == null) {
                player.teleportTo(location);
                return true;
            }
            for (Group gr : allowedGroups) {
                if (player.getGroup().hasControlOver(gr)) {
                    player.teleportTo(location);
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Check if this warp is restricted to one or more groups
     *
     * @return
     */
    public boolean isGroupRestricted() {
        return !(allowedGroups == null);
    }

    /**
     * Check if the group with the specified name is allowed to use this warp.
     *
     * @param group
     *
     * @return true if group is allowed, false otherwise
     */
    public boolean isGroupAllowed(String group) {
        if (allowedGroups == null) {
            return true;
        }
        Group realGroup = Canary.usersAndGroups().getGroup(group);

        if (realGroup == null) {
            return false;
        }
        for (Group g : allowedGroups) {
            if (g.getName().equals(realGroup.getName()) || realGroup.hasControlOver(g)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the group with the specified name is allowed to use this warp.
     *
     * @param group
     *
     * @return true if group is allowed, false otherwise
     */
    public boolean isGroupAllowed(Group group) {
        if (allowedGroups == null) {
            return true;
        }
        for (Group g : allowedGroups) {
            if (g.getName().equals(group.getName()) || group.hasControlOver(g)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return the array of allowed groups
     *
     * @return
     */
    public Group[] getGroups() {
        return allowedGroups;
    }

    public ArrayList<String> getGroupsAsString() {
        ArrayList<String> list = new ArrayList<String>();
        if (allowedGroups == null) {
            return list;
        }
        for (Group g : allowedGroups) {
            list.add(g.getName());
        }
        return list;
    }

    /**
     * Check if this warp is a player home
     *
     * @return
     */
    public boolean isPlayerHome() {
        return isPlayerHome;
    }

}
