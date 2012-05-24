package net.canarymod.hook.command;

import net.canarymod.api.entity.Player;
import net.canarymod.hook.CancelableHook;


/**
 * Server command hook. Contains the command issued by the server
 * @author Chris Ksoll
 *
 */
public class ConsoleCommandHook extends CancelableHook {
    private String[] command;
    private Player player;
    
    public ConsoleCommandHook(Player player, String textInput) {
        command = textInput.split(" ");
        this.player = player;
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
     * Get the player that has issued the console command. This may be null
     * if the command has been actually issued from the console!
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }
    /**
     * Returns object array in this order: COMMAND
     */
    @Override
    public Object[] getDataSet() {
        return new Object[]{command};
    }

}
