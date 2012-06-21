package net.canarymod.hook.player;

import net.canarymod.api.entity.Player;
import net.canarymod.api.world.position.Location;
import net.canarymod.hook.CancelableHook;

/**
 * Teleport hook. Contains player who is teleporting and their destination
 * @author Brian McCarthy
 *
 */
public class TeleportHook extends CancelableHook {
    private Player player;
    private Location destination;
    
    public TeleportHook(Player player, Location destination, boolean usingPortal) {
        this.player = player;
        this.destination = destination;
        this.type = usingPortal ? Type.PORTAL_USE: Type.TELEPORT;
    }
    
    /**
     * Get the {@link Player} instance.
     * @return
     */
    public Player getPlayer() {
        return player;
    }
    
    /**
     * Get the destination {@link Location} for the teleport.
     * @return
     */
    public Location getDestination() {
        return destination;
    }
    
    /**
     * Return the set of Data in this order: PLAYER DESTINATION ISCANCELLED
     */
    public Object[] getDataSet(){
        return new Object[]{ player, destination, isCancelled};
    }
}
