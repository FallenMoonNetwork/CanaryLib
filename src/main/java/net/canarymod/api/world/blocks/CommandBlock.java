package net.canarymod.api.world.blocks;

import net.canarymod.chat.MessageReceiver;

/**
 * CommandBlock wrapper
 * 
 * @author Jason (darkdiplomat)
 */
public interface CommandBlock extends ComplexBlock, MessageReceiver {

    /**
     * Sets the CommandBlock's command
     * 
     * @param command
     *            the command to execute when this block is activated
     */
    public void setCommand(String command);

    /**
     * Returns the CommandBlock's command
     * 
     * @return the command
     */
    public String getCommand();

    /**
     * Run this CommandBlock's command
     */
    public void activate();

    /**
     * Sets the text that appears before a CommandBlock's command in chat
     * Default is '@'
     * 
     * @param prefix
     *            the prefix to use
     */
    public void setPrefix(String prefix);

    /**
     * Returns the text that appears before a command block's command in chat
     * Default is '@'
     * 
     * @return the CommandBlock's prefix
     */
    public String getPrefix();

}
