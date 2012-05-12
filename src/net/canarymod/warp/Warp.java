package net.canarymod.warp;

import net.canarymod.api.entity.IPlayer;
import net.canarymod.api.world.position.Location;
import net.canarymod.group.Group;
/**
 * Contains information regarding warp points
 * @author Chris
 *
 */
public class Warp {

    private Group[] allowedGroups;
    private String name;
    private boolean isPlayerHome=false;
    private String owner;
    private Location location;
    /**
     * Set up a group specific warp
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
    
    public Warp(Location l, String name, IPlayer owner) {
        location = l;
        this.name = name;
        isPlayerHome = true;
        this.owner = owner.getName();
        allowedGroups = null;
    }
    /**
     * Get the warps name
     * @return
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get owners name.
     * May return null if this is no private home warp
     * @return
     */
    public String getOwner() {
        return owner;
    }
    
    /**
     * Check preconditions and warp a player to this warps location
     * @param player
     * @return True if warped, false otherwise
     */
    public boolean warp(IPlayer player) {
        if(isPlayerHome && owner != null) {
            if(player.getName().equals(owner)) {
                player.teleportTo(location);
                return true;
            }
            else {
                return false;
            }
        }
        else {
            if(allowedGroups == null) {
                player.teleportTo(location);
                return true;
            }
            for(Group gr : allowedGroups) {
                if(player.getGroup().hasControlOver(gr)) {
                    player.teleportTo(location);
                    return true;
                }
            }
            return false;
        }
    }
    
    public boolean isPlayerHome() {
        return isPlayerHome;
    }

}
