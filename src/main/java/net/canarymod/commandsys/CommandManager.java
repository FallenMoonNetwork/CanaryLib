package net.canarymod.commandsys;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;
import net.visualillusionsent.utils.LocaleHelper;


/**
 * Manages all commands.
 * Add commands using one of the methods below.
 *
 * @author Willem Mulder
 * @author Chris Ksoll
 */
public class CommandManager {
    HashMap<String, CanaryCommand> commands  = new HashMap<String, CanaryCommand>();

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
            CanaryCommand cmd = commands.get(entry);

            if (cmd.owner.getName().equals(owner.getName())) {
                itr.remove();
            }
        }
        Canary.help().unregisterCommands(owner);
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

    public boolean canUseCommand(MessageReceiver user, String command) {
        CanaryCommand cmd = commands.get(command);
        if(cmd == null) {
            return false; // For further processing in implementing code
        }
        return cmd.canUse(user);
    }

    /**
     * Performs a lookup for a command of the given name and executes it if
     * found. Returns false if the command wasn't found or if the caller doesn't
     * have the permission to run it.
     *<br>
     * In Short: Use this to fire commands.
     *
     * @param command The command to run
     * @param caller The {@link MessageReceiver} to send messages back to
     * (assumed to be the caller)
     * @param args The arguments to {@code command} (including {@code command})
     * @return true if {@code command} executed successfully, false otherwise
     */
    public boolean parseCommand(MessageReceiver caller, String command, String[] args) {
        CanaryCommand baseCommand = commands.get(command);
        CanaryCommand subCommand = null;
        if(baseCommand == null) {
            return false;
        }
        //Parse args to find sub-command if there are any.
        int argumentIndex = 0; //Index from which we should truncate args array
        CanaryCommand tmp = null;
        for(int i = 0; i < args.length; ++i) {
            if(i+1 >= args.length) {
                break;
            }
            if(i == 0) {
                tmp = baseCommand.getSubCommand(args[1]);
                if(tmp != null) {
                    subCommand = tmp;
                    ++argumentIndex;
                }
                continue;
            }
            if(tmp != null) {
                if(tmp.hasSubCommand(args[i+1])) {
                    tmp = tmp.getSubCommand(args[i+1]);
                    ++argumentIndex;
                }
                if(argumentIndex >= args.length) {
                    //Clearly some invalid crazy thing
                    argumentIndex = args.length - 1;
                    break;
                }
                if(subCommand != tmp) {
                    subCommand = tmp;
                }
            }
        }

        if(subCommand == null) {
            return baseCommand.parseCommand(caller, args);
        }
        return subCommand.parseCommand(caller, Arrays.copyOfRange(args, argumentIndex, args.length));
    }

    public void registerCommands(final CommandListener listener, CommandOwner owner, boolean force) throws CommandDependencyException {
        registerCommands(listener, owner, Translator.getInstance(), force);
    }

    /**
     * Register an already implemented CanaryCommand to the help system.
     * This will automatically update the help system as well.
     * @param com
     * @param owner
     * @param force force overriding
     * @throws CommandDependencyException
     */
    public void registerCommand(CanaryCommand com, CommandOwner owner, boolean force) throws CommandDependencyException {
        //Check for local dependencies
        for(CanaryCommand parent : commands.values()) {
            if(com.meta.parent().isEmpty()) {
                continue;
            }
            boolean depMissing = true;
            CanaryCommand tmp = null;
            String[] cmdp = com.meta.parent().split("\\.");
            for(int i = 0; i < cmdp.length; i++) {
                if(i == 0) {
                    for(String palias : parent.meta.aliases()) {
                        if(palias.equals(cmdp[i])) {
                            tmp = parent;
                        }
                    }
                }
                else {
                    if(tmp == null) {
                        break;
                    }
                    if(tmp.hasSubCommand(cmdp[i])) {
                        tmp = tmp.getSubCommand(cmdp[i]);
                    }
                    else {
                        tmp = null;
                        break;
                    }
                }
            }
            if(tmp != null) {
                com.setParent(tmp);
                depMissing = false;
            }
            if(depMissing) {
                throw new CommandDependencyException(com.meta.aliases()[0] + " has an unsatisfied dependency, " +
                        "( " + com.meta.parent() + " )" +
                        "please adjust registration order of your listeners or fix your plugins dependencies");
            }
        }

      //KDone. Lets update commands list
        boolean hasDuplicate = false;
        StringBuilder dupes = null;
        for(String alias : com.meta.aliases()) {
            if(commands.containsKey(alias.toLowerCase()) && com.meta.parent().isEmpty() && !force) {
                hasDuplicate = true;
                if(dupes == null) {
                    dupes = new StringBuilder();
                }
                dupes.append(alias).append(" ");
            }
            else {
                if(com.meta.parent().isEmpty()) { //Only add root commands
                    commands.put(alias.toLowerCase(), com);
                }
                if(!com.meta.helpLookup().isEmpty() && !Canary.help().hasHelp(com.meta.helpLookup())) {
                    Canary.help().registerCommand(owner, com, com.meta.helpLookup());
                }
                else {
                    Canary.help().registerCommand(owner, com);
                }
            }
        }
        if(hasDuplicate && !force) {
            throw new DuplicateCommandException(dupes.toString());
        }
    }

    /**
     * Register your CommandListener.
     * This will make all annotated commands available to CanaryMod and the help system.
     * Sub Command relations can only be sorted out after availability.
     * That means if you try to register a command that is a sub-command of something
     * that is not registered yet, it will fail.
     * So make sure you add commands in the correct order.
     * @param listener
     * @param owner
     * @param translator
     * @param force
     * @throws CommandDependencyException
     */
    public void registerCommands(final CommandListener listener, CommandOwner owner, LocaleHelper translator, boolean force) throws CommandDependencyException {
        Method[] methods = listener.getClass().getDeclaredMethods();
        ArrayList<CanaryCommand> loadedCommands = new ArrayList<CanaryCommand>();

        for(final Method method : methods) {
            if(!method.isAnnotationPresent(Command.class)) {
                continue;
            }
            Class<?>[] params = method.getParameterTypes();
            if(params.length != 2) {
                Canary.logWarning("You have a Command method with invalid number of arguments! - " + method.getName());
                continue;
            }
            if(!(MessageReceiver.class.isAssignableFrom(params[0]) && String[].class.isAssignableFrom(params[1]))) {
                Canary.logWarning("You have a Command method with invalid argument types! - " + method.getName());
                continue;
            }
            Command meta = method.getAnnotation(Command.class);
            CanaryCommand command = new CanaryCommand(meta, owner, translator) {
                @Override
                protected void execute(MessageReceiver caller, String[] parameters) {
                    try {
                        method.invoke(listener, new Object[] {caller, parameters});
                    } catch (IllegalArgumentException e) {
                        Canary.logStackTrace("Could not execute command: " + e.getMessage(), e);
                    } catch (IllegalAccessException e) {
                        Canary.logStackTrace("Could not execute command: " + e.getMessage(), e);
                    } catch (InvocationTargetException e) {
                        Canary.logStackTrace("Could not execute command: " + e.getMessage(), e);
                    }
                }
            };
            loadedCommands.add(command);
        }
        //Sort load order so dependencies can be resolved properly
        Collections.sort(loadedCommands);
        //Take care of parenting
        for(CanaryCommand cmd : loadedCommands) {
            if(cmd.meta.parent().isEmpty()) {
                continue;
            }
            String[] cmdp = cmd.meta.parent().split("\\.");
            boolean depMissing = true;
            //Check for local dependencies
            for(CanaryCommand parent : loadedCommands) {
                CanaryCommand tmp = null;
                for(int i = 0; i < cmdp.length; i++) {
                    if(i == 0) {
                        for(String palias : parent.meta.aliases()) {
                            if(palias.equals(cmdp[i])) {
                                tmp = parent;
                            }
                        }
                    }
                    else {
                        if(tmp == null) {
                            break;
                        }
                        if(tmp.hasSubCommand(cmdp[i])) {
                            tmp = tmp.getSubCommand(cmdp[i]);
                        }
                        else {
                            tmp = null;
                            break;
                        }
                    }
                }
                if(tmp != null) {
                    cmd.setParent(tmp);
                    depMissing = false;
                }
            }
            //TODO: Allow extension of commands from other listeners?

            if(depMissing) {
                throw new CommandDependencyException(cmd.meta.aliases()[0] + " has an unsatisfied dependency, " +
                        "( " + cmd.meta.parent() + " )" +
                        "please adjust registration order of your listeners or fix your plugins dependencies");
            }
        }
        //KDone. Lets update commands list
        boolean hasDuplicate = false;
        StringBuilder dupes = null;
        for(CanaryCommand cmd : loadedCommands) {
            for(String alias : cmd.meta.aliases()) {
                if(commands.containsKey(alias.toLowerCase()) && cmd.meta.parent().isEmpty() && !force) {
                    hasDuplicate = true;
                    if(dupes == null) {
                        dupes = new StringBuilder();
                    }
                    dupes.append(alias).append(" ");
                }
                else {
                    if(cmd.meta.parent().isEmpty()) { //Only add root commands
                        commands.put(alias.toLowerCase(), cmd);
                    }
                    if(!cmd.meta.helpLookup().isEmpty() && !Canary.help().hasHelp(cmd.meta.helpLookup())) {
                        Canary.help().registerCommand(owner, cmd, cmd.meta.helpLookup());
                    }
                    else {
                        Canary.help().registerCommand(owner, cmd);
                    }
                }
            }
        }
        if(hasDuplicate && !force) {
            throw new DuplicateCommandException(dupes.toString());
        }
    }

    /**
     * Build a list of commands matching the given string.
     * @param caller
     * @param command
     * @return nullchar separated stringbuilder
     */
    public StringBuilder matchCommand(MessageReceiver caller, String command, boolean onlySubcommands) {
        int matches = 0;
        int maxMatches = 4;
        StringBuilder matching = new StringBuilder();
        command = command.toLowerCase();
        //Match base commands
        for(String key : commands.keySet()) {
            if(!onlySubcommands) {
                if(key.toLowerCase().equals(command)) {
                    //Perfect match
                    if(matching.indexOf("/".concat(key)) == -1) {
                        if(commands.get(key).canUse(caller) && matches <= maxMatches) {
                            ++matches;
                            matching.append("/").append(key).append("\u0000");
                        }
                    }
                }
                else if(key.toLowerCase().indexOf(command) != -1) {
                    //Partial match
                    if(matching.indexOf("/".concat(key)) == -1) {
                        if(commands.get(key).canUse(caller) && matches <= maxMatches) {
                            ++matches;
                            matching.append("/").append(key).append("\u0000");
                        }
                    }
                }
            }
            //Match sub commands
            for(CanaryCommand cmd : commands.get(key).getSubCommands(new ArrayList<CanaryCommand>())) {
                for(String alias : cmd.meta.aliases()) {
                    if(alias.toLowerCase().equals(command)) {
                        //full match
                        if(matching.indexOf(alias) == -1) {
                            if(cmd.canUse(caller) && matches <= maxMatches) {
                                ++matches;
                                matching.append(alias).append("\u0000");
                            }
                        }
                    }
                    else if(alias.toLowerCase().indexOf(command) != -1) {
                        //partial match
                        if(matching.indexOf(alias) == -1) {
                            if(cmd.canUse(caller) && matches <= maxMatches) {
                                ++matches;
                                matching.append(alias).append("\u0000");
                            }
                        }
                    }
                }
            }
        }
        return matching;
    }
}
