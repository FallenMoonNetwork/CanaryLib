package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.Hook;

/**
 * Connection hook. Contains information about a player connecting/disconnection.
 * @author Jason Jones
 *
 */
public final class ConnectionHook extends Hook{

    private Player player;
    private String message, reason;
    private boolean hidden;

    public ConnectionHook(Player player, String message, String reason){
        this.player = player;
        this.message = message;
        this.reason = reason;
    }

    /**
     * Gets the {@link Player}
     * @return
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * Gets the message sent to all
     * @return
     */
    public String getMessage(){
        return message;
    }

    /**
     * Sets the message set to all
     * @param message
     */
    public void setMessage(String message){
        this.message = message;
    }

    /**
     * Get the reason for disconnect (if applicable)
     * @return reason if disconnecting, null otherwise
     */
    public String getReason(){
        return reason;
    }

    /**
     * Gets whether this should be a hidden connect/disconnect
     * @return hidden
     */
    public boolean isHidden(){
        return hidden;
    }

    /**
     * Sets whether this should be a hidden connect/disconnect
     * @param hidden
     */
    public void setHidden(boolean hidden){
        this.hidden = hidden;
    }

    /**
     * Return the set of Data in this order: PLAYER MESSAGE REASON HIDDEN
     */
    @Override
    public Object[] getDataSet() {
        return new Object[]{ player, message, reason, hidden };
    }
}
