package net.canarymod.hook.command;


import net.canarymod.chat.MessageReceiver;
import net.canarymod.hook.CancelableHook;


/**
 * Server command hook. Contains the command issued by the server
 *
 * @author Chris Ksoll
 */
public final class ConsoleCommandHook extends CancelableHook {
    private String[] command;
    private MessageReceiver receiver;

    public ConsoleCommandHook(MessageReceiver receiver, String textInput) {
        command = textInput.split(" ");
        this.receiver = receiver;
    }

    /**
     * Get command that was issued by the console
     *
     * @return
     */
    public String[] getCommand() {
        return command;
    }

    /**
     * Get the object that has issued the command.
     * This can be a player or the console (Server).
     * Use <tt>instanceof</tt> to check types
     *
     * @return the message receiver (command caller)
     */
    public MessageReceiver getCaller() {
        return receiver;
    }
}
