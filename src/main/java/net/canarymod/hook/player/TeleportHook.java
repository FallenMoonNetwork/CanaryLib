package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.position.Location;
import net.canarymod.hook.CancelableHook;

/**
 * Teleport hook. Contains player who is teleporting and their destination
 * 
 * @author Brian McCarthy
 */
public final class TeleportHook extends CancelableHook {
    private Player player;
    private Location destination;

    public TeleportHook(Player player, Location destination, boolean usingPortal) {
        this.player = player;
        this.destination = destination;
    }

    /**
     * Get the {@link Player} instance.
     * 
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get the destination {@link Location} for the teleport.
     * 
     * @return
     */
    public Location getDestination() {
        return destination;
    }

    @Override
    public final String toString() {
        return String.format("%s[Player=%s, Destination=%s]", getName(), player, destination);
    }
}
