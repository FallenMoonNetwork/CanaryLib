package net.canarymod.hook.player;

import net.canarymod.api.entity.Player;
import net.canarymod.hook.CancelableHook;

/**
 * Login hook. Do stuff with a player right after the player instance has been created
 * @author Chris Ksoll
 *
 */
public class ChatHook extends CancelableHook {
    private Player player;
    private String prefix;
    private StringBuilder message;
    
    public ChatHook(Player player, String prefix, StringBuilder message) {
        this.player = player;
        this.prefix = prefix;
        this.message = message;
        this.type = Type.CHAT;
    }
    
    /**
     * Get the player instance
     * @return
     */
    public Player getPlayer() {
        return player;
    }
    
    /**
     * Get the message prefix. The prefix contains the following data:<br>
     * &lt;PREFIXCOLOR PLAYERNAME&gt; Where &lt; and &gt; are included. 
     * @return
     */
    public String getPrefix() {
        return prefix;
    }
    
    /**
     * Get the message this player has sent
     * @return
     */
    public StringBuilder getMessage() {
        return message;
    }
    
    /**
     * Change the message completely
     * @param message
     */
    public void setMessage(StringBuilder message) {
        this.message = message;
    }
    
    /**
     * Append the given String to the existing message
     * @param toAppend
     */
    public void appendToMessage(String toAppend) {
        message.append(toAppend);
    }
    
    /**
     * Override the players chat prefix
     * @param newPrefix
     */
    public void setPrefix(String newPrefix) {
        this.prefix = newPrefix;
    }
    
    /**
     * Return the set of Data in this order: PLAYER, PREFIX, MESSAGE
     */
    @Override
    public Object[] getDataSet() {
        return new Object[]{player, prefix, message};
    }

}
