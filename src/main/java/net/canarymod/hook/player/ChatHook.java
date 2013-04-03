package net.canarymod.hook.player;


import java.util.ArrayList;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.CancelableHook;


/**
 * Chat hook. Contains player, prefix, message and receivers information
 * TODO: make prefix chatFormat containing placeholders for prefix and player name
 * @author Chris Ksoll
 *
 */
public final class ChatHook extends CancelableHook {
    private Player player;
    private String prefix;
    private String message;
    private String format;
    private ArrayList<Player> receivers;

    public ChatHook(Player player, String prefix, String message, String format, ArrayList<Player> receivers) {
        this.player = player;
        this.prefix = prefix;
        this.message = message;
        this.receivers = receivers;
        this.format = format;
    }

    /**
     * Get the {@link Player} instance
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
     * Remove a {@link Player} from the receiver list
     * @param player
     */
    public void removeFromReceiverList(Player player) {
        receivers.remove(player);
    }

    /**
     * Add a {@link Player} to the receiver list. This better not be null!
     * @param player
     */
    public void addToReceiverList(Player player) {
        receivers.add(player);
    }

    /**
     * Get the chat format. This is a string like this:<br>
     * &lt;%prefix %name&gt; %message
     * You can modify this format, legal replacement values are:<br>
     * <ul>
     * <li>%prefix - The player color in most cases</li>
     * <li>%name - The player name</li>
     * <li>%group - The players group</li>
     * <li>%message - The message that is sent by the player</li>
     * </ul>
     * @return the format
     */
    public String getFormat() {
        return format;
    }

    /**
     * Override the default chatting format.
     * @see ChatHook#getFormat()
     * @param format the format to set
     */
    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public final String toString() {
        return String.format("%s[Player=%s, Receivers=%s, Message=%s]", getName(), player, receivers, message);
    }
}
