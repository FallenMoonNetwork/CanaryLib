/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.canarymod.commandsys;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import net.canarymod.Logman;
import net.canarymod.MessageReceiver;

/**
 * Manages all commands.
 * Add commands using one of the methods below.
 * 
 * @author Willem Mulder
 */
public class CommandManager {
    Map<String, CanaryCommand> commands = new HashMap<String, CanaryCommand>();
    
    public boolean addCommand(String name, CanaryCommand command) {
        if (name == null || command == null)
            return false;
        
        if (!commands.containsKey(name))
            return commands.put(name, command) != null;
        else
            return false;
    }
    
    /**
     * Remove a command from the player list.
     *
     * @param name
     * @return <tt>true</tt> if the command was removed, <tt>false</tt> otherwise. 
     */
    public boolean removeCommand(String name) {
        if (name == null || !commands.containsKey(name))
            return false;
        
        return commands.remove(name) != null;
    }
    
    /**
     * Searches for and returns {@code command} if found, {@code null}
     * otherwise.
     *
     * @param command The command to search for
     * @return {@code command} if found, {@code null} otherwise
     */
    public CanaryCommand getCommand(String command) {
        return commands.get(command);
    }
    
    /**
     * Performs a lookup for a command of the given name and executes it if
     * found. Returns false if the command wasn't found or if the caller doesn't
     * have the permission to run it.
     *
     * @param command The command to run
     * @param caller The {@link MessageReceiver} to send messages back to
     * (assumed to be the caller)
     * @param args The arguments to {@code command} (including {@code command})
     * @return true if {@code command} was found, false otherwise
     */
    public boolean parseCommand(MessageReceiver caller, String command, String[] args) {

        CanaryCommand cmd = this.getCommand(command);

        if (caller.hasPermission(cmd.permissionNode) && cmd != null) {
            cmd.parseCommand(caller, args);
            // Inform caller a matching command was found.
            return true;
        }
        return false;
    }
    
    public Map<String, Boolean> registerAll(Class<?> clazz) {
        Map<String, Boolean> didItWork = new HashMap<String, Boolean>();
        
        for (Field field : clazz.getDeclaredFields()) {
            // If the field is a CanaryCommand and has a Command annotation
            if (field.getType() == CanaryCommand.class && field.isAnnotationPresent(Command.class)) {
                // For each command value
                for (String command : field.getAnnotation(Command.class).value()) {
                    try {
                        boolean success = this.addCommand(
                                command.equals("") ? field.getName() : command, // If empty, assume field name
                                (CanaryCommand) field.get(null)); // Get static field
                        didItWork.put(command, success);
                    } catch (IllegalAccessException e) {
                        Logman.logSevere("Failed to add " + (command.equals("") 
                                ? field.getName() : command) + ", field is inaccessible!");
                        didItWork.put(command, Boolean.FALSE);
                    }
                }
            }
        }
        return didItWork;
    }
    
}
