package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.position.Location;
import net.canarymod.hook.CancelableHook;

/**
 * Player move hook. Contains information about a player's movement
 *
 * @author Jason (darkdiplomat)
 */
public final class PlayerMoveHook extends CancelableHook {

    private Player player;
    private Location from;
    private Location to;

    public PlayerMoveHook(Player player, Location from, Location to) {
        this.player = player;
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the {@link Player}
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the from {@link Location}
     *
     * @return from
     */
    public Location getFrom() {
        return from;
    }

    /**
     * Gets the to {@link Location} (same as player's location)
     *
     * @return to
     */
    public Location getTo() {
        return to;
    }

    @Override
    public final String toString() {
        return String.format("%s[Player=%s, From=%s, To=%s]", getName(), player, from, to);
    }
}
