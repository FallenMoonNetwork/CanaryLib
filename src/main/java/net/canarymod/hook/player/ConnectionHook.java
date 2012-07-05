package net.canarymod.hook.player;

import net.canarymod.api.entity.Player;
import net.canarymod.hook.Hook;
import net.canarymod.plugin.PluginListener;

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
        this.type = reason != null ? Type.PLAYER_DISCONNECT : Type.PLAYER_CONNECT;
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
    
    /**
     * Dispatches the hook to the given listener.
     * @param listener The listener to dispatch the hook to.
     */
    @Override
    public void dispatch(PluginListener listener) {
        switch (this.type) {
            case PLAYER_CONNECT: {
                listener.onPlayerConnect(this);
                break;
            }
            case PLAYER_DISCONNECT: {
                listener.onPlayerDisconnect(this);
                break;
            }
        }
    }
}
