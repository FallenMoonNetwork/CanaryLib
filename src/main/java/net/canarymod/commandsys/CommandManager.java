package net.canarymod.commandsys;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.canarymod.Canary;
import net.canarymod.chat.MessageReceiver;


/**
 * Manages all commands.
 * Add commands using one of the methods below.
 *
 * @author Willem Mulder
 * @author Chris Ksoll
 */
public class CommandManager {
    Map<String, RegisteredCommand> commands = new HashMap<String, RegisteredCommand>();

    /**
     * Add a command to the list. <b>This will also auto-add a help entry according to your tooltip and error message in your CanaryCommand!</b>
     * @param name
     * @param command
     * @param plugin
     * @param force Force insertion of command with <tt>true</tt>. This will override existing commands with the same name!
     * @return True on success, false otherwise
     * @throws DuplicateCommandException if command exists and insertion is not forced
     */
    public boolean registerCommand(String name, CanaryCommand command, CommandOwner plugin, boolean force) {
        if (name == null || command == null) {
            return false;
        }

        if (!commands.containsKey(name.toLowerCase())) {
            Canary.help().registerCommand(plugin, name, command.errorMessage, command.permissionNode);
            return commands.put(name.toLowerCase(), new RegisteredCommand(plugin, command)) != null;
        } else {
            if (force) {
                commands.remove(name.toLowerCase());
                Canary.help().unregisterCommand(plugin, name);

                Canary.help().registerCommand(plugin, name, command.errorMessage, command.permissionNode);
                commands.put(name.toLowerCase(), new RegisteredCommand(plugin, command));
                return true;
            } else {
                throw new DuplicateCommandException(name);
            }
        }
    }

    /**
     * Remove a command from the command list.
     *
     * @param name
     * @return <tt>true</tt> if the command was removed, <tt>false</tt> otherwise.
     */
    public boolean unregisterCommand(String name) {
        if (name == null || !commands.containsKey(name.toLowerCase())) {
            return false;
        }

        return commands.remove(name.toLowerCase()) != null;
    }

    /**
     * Remove all commands that belong to the specified command owner.
     * @param owner The owner. That can be a plugin or the server
     */
    public void unregisterCommands(CommandOwner owner) {
        if(owner == null) {
            return;
        }
        Iterator<String>itr = commands.keySet().iterator();

        while (itr.hasNext()) {
            String entry = itr.next();
            RegisteredCommand cmd = commands.get(entry);

            if (cmd.getOwnerName().equals(owner.getName())) {
                itr.remove();
            }
        }
    }

    /**
     * Searches for and returns {@code command} if found, {@code null}
     * otherwise.
     *
     * @param command The command to search for
     * @return {@code command} if found, {@code null} otherwise
     */
    public CanaryCommand getCommand(String command) {
        // if(command == null || command.isEmpty()) {
        // return null;
        // }
        RegisteredCommand cmd = commands.get(command.toLowerCase());

        if (cmd != null) {
            return cmd.getCommand();
        }
        return null;
    }

    /**
     * Checks whether this manager has <tt>command</tt>.
     *
     * @param command The command to search for.
     * @return <tt>true</tt> if this manager has <tt>command</tt>, <tt>false</tt> otherwise.
     */
    public boolean hasCommand(String command) {
        return commands.containsKey(command.toLowerCase());
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

        if (cmd != null) {
            if (caller.hasPermission(cmd.permissionNode)) {
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
                        CanaryCommand com = (CanaryCommand) field.get(null);
                        boolean success = this.registerCommand(command.equals("") ? field.getName() : command, com, Canary.getServer(), false); // do not override any commands

                        if (success) {
                            Canary.help().registerCommand(Canary.getServer(), command, com.errorMessage, com.permissionNode);
                        }
                        didItWork.put(command, success);

                    } catch (IllegalAccessException e) {
                        Canary.logSevere("Failed to add " + (command.equals("") ? field.getName() : command) + ": " + e);
                        didItWork.put(command, Boolean.FALSE);
                    }
                }
            }
        }
        return didItWork;
    }
}
