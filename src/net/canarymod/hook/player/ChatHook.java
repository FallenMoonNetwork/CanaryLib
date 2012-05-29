package net.canarymod.hook.player;

import java.util.ArrayList;

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
    private String message;
    private ArrayList<Player> receivers;
    
    public ChatHook(Player player, String prefix, String message, ArrayList<Player> receivers) {
        this.player = player;
        this.prefix = prefix;
        this.message = message;
        this.receivers = receivers;
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
    public String getMessage() {
        return message;
    }
    
    /**
     * Change the message completely
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * Append the given String to the existing message
     * @param toAppend
     */
    public void appendToMessage(String toAppend) {
        message += toAppend;
    }
    
    /**
     * Override the players chat prefix
     * @param newPrefix
     */
    public void setPrefix(String newPrefix) {
        this.prefix = newPrefix;
    }
    
    /**
     * Get a list of all receivers for this message
     * @return
     */
    public ArrayList<Player> getReceiverList() {
        return receivers;
    }
    
    /**
     * Override the list of receivers
     * @param receiverList
     */
    public void setReceiverList(ArrayList<Player> receiverList) {
        receivers = receiverList;
    }
    
    /**
     * Remove a player from the receiver list
     * @param player
     */
    public void removeFromReceiverList(Player player) {
        receivers.remove(player);
    }
    
    /**
     * Add a player to the receiver list. This better not be null!
     * @param player
     */
    public void addToReceiverList(Player player) {
        receivers.add(player);
    }
    
    /**
     * Return the set of Data in this order: PLAYER, PREFIX, MESSAGE
     */
    @Override
    public Object[] getDataSet() {
        return new Object[]{player, prefix, message};
    }

}
