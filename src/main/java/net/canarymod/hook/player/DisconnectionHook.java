package net.canarymod.hook.player;


import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.Hook;


/**
 * Connection hook. Contains information about a player connecting/disconnection.
 * @author Jason Jones
 *
 */
public final class DisconnectionHook extends Hook {

    private Player player;
    private String reason;
    private boolean hidden;

    public DisconnectionHook(Player player, String reason) {
        this.player = player;
        this.reason = reason;
    }

    /**
     * Gets the {@link Player}
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get the reason for disconnect (if applicable)
     * @return reason if disconnecting, null otherwise
     */
    public String getReason() {
        return reason;
    }

    /**
     * Set the reason for disconnection that will be displayed
     * @param reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Gets whether this should be a hidden connect/disconnect
     * @return hidden
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * Sets whether this should be a hidden connect/disconnect
     * @param hidden
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
