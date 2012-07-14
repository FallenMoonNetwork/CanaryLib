package net.canarymod.commandsys;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import net.canarymod.Canary;
import net.canarymod.Logman;
import net.canarymod.MessageReceiver;
import net.canarymod.plugin.Plugin;

/**
 * Manages all commands.
 * Add commands using one of the methods below.
 * 
 * @author Willem Mulder
 */
public class CommandManager {
    Map<String, CanaryCommand> commands = new HashMap<String, CanaryCommand>();
    
    /**
     * Add a command to the command list.
     *
     * @param name
     * @param command
     * @return <tt>true<tt> if the command was added, <tt>false</tt> otherwise.
     */
   public boolean addCommand(String name, CanaryCommand command) {
        if (name == null || command == null)
            return false;
        
        if (!commands.containsKey(name)) {
            commands.put(name, command);
            return true;
        }
        else
            throw new DuplicateCommandException(name);
    }
   
   /**
    * Add a command to the list. <b>This will also auto-add a help entry according to your tooltip and error message in your CanaryCommand!</b>
    * @param name
    * @param command
    * @param plugin
    * @return
    */
   public boolean addCommand(String name, CanaryCommand command, Plugin plugin) {
       if (name == null || command == null)
           return false;
       
       if (!commands.containsKey(name)) {
           Canary.help().registerCommand(plugin, name, command.errorMessage, command.permissionNode);
           return commands.put(name, command) != null;
       }
       else
           throw new DuplicateCommandException(name);
   }
    
    /**
     * Remove a command from the command list.
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
     * Checks whether this manager has <tt>command</tt>.
     * 
     * @param command The command to search for.
     * @return <tt>true</tt> if this manager has <tt>command</tt>, <tt>false</tt> otherwise.
     */
    public boolean hasCommand(String command) {
        return commands.containsKey(command);
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
        if (cmd != null)  {
            if (caller.hasPermission(cmd.permissionNode) && cmd != null) {
                cmd.parseCommand(caller, args);
                // Inform caller a matching command was found.
                return true;
            }
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
//                        CanaryCommand com = (CanaryCommand) field.get(null);
                        CanaryCommand com = (CanaryCommand) field.get(null);
                        boolean success = this.addCommand(
                                command.equals("") ? field.getName() : command, // If empty, assume field name
                                com); // Get static field
                        if(success) {
                            Canary.help().registerCommand(null, command, com.errorMessage, com.permissionNode);
                        }
                        didItWork.put(command, success);
                        
                    } catch (IllegalAccessException e) {
                        Logman.logSevere("Failed to add " + (command.equals("") 
                                ? field.getName() : command) + ": " + e);
                        didItWork.put(command, Boolean.FALSE);
                    }
                }
            }
        }
        return didItWork;
    }
    
}
