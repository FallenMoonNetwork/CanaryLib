package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.Hook;

/**
 * Connection hook. Contains information about a player connecting.
 * 
 * @author Jason (darkdiplomat)
 */
public final class ConnectionHook extends Hook {

    private Player player;
    private String message;
    private boolean hidden;
    private boolean firstTime;

    public ConnectionHook(Player player, String message, boolean firstTime) {
        this.player = player;
        this.message = message;
        this.firstTime = firstTime;
    }

    /**
     * Gets the {@link Player}
     * 
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the message sent to all
     * 
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message set to all
     * 
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets whether this should be a hidden connect/disconnect
     * 
     * @return hidden
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * Returns true if this player has never connected before.
     * 
     * @return
     */
    public boolean isFirstConnection() {
        return firstTime;
    }

    /**
     * Sets whether this should be a hidden connect/disconnect
     * 
     * @param hidden
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    @Override
    public final String toString() {
        return String.format("%s[Player=%s, Message=%s, Hidden=%s]", getName(), player, message, hidden);
    }
}
