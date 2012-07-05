package net.canarymod.hook.player;

import net.canarymod.api.entity.Player;
import net.canarymod.api.world.position.Location;
import net.canarymod.hook.CancelableHook;
import net.canarymod.plugin.PluginListener;

/**
 * Teleport hook. Contains player who is teleporting and their destination
 * @author Brian McCarthy
 *
 */
public final class TeleportHook extends CancelableHook {
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
        return new Object[]{ player, destination, isCanceled};
    }
    
    /**
     * Dispatches the hook to the given listener.
     * @param listener The listener to dispatch the hook to.
     */
    @Override
    public void dispatch(PluginListener listener) {
        switch (this.type) {
            case PORTAL_USE: {
                listener.onPortalUse(this);
                break;
            }
            case TELEPORT: {
                listener.onTeleport(this);
                break;
            }
        }
    }
}
