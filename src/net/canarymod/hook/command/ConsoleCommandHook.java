package net.canarymod.hook.command;

import net.canarymod.hook.CancelableHook;


/**
 * Server command hook. Contains the command issued by the server
 * @author Chris Ksoll
 *
 */
public class ConsoleCommandHook extends CancelableHook {
    private String[] command;
    
    public ConsoleCommandHook(String textInput) {
        command = textInput.split(" ");
        this.type = Type.CONSOLECOMMAND;
    }
    
    /**
     * Get command that was issued by the console
     * @return
     */
    public String[] getCommand() {
        return command;
    }

    
    /**
     * Returns object array in this order: COMMAND
     */
    @Override
    public Object[] getDataSet() {
        return new Object[]{command};
    }

}
