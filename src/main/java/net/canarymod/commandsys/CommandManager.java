package net.canarymod.commandsys;

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
 * @author Willem (l4mRh4X0r)
 * @author Chris (damagefilter)
 */
public class CommandManager {
    HashMap<String, CanaryCommand> commands = new HashMap<String, CanaryCommand>();

    /**
     * Remove a command from the command list.
     *
     * @param name
     *         the name of the command
     *
     * @return <tt>true</tt> if the command was removed, <tt>false</tt> otherwise.
     */
    public boolean unregisterCommand(String name) {

        if (name == null) {
            return false;
        }

        String[] commandchain = name.split("\\.");
        CanaryCommand temp = null;
        for (int i = 0; i < commandchain.length; i++) {
            if (i == 0) {
                temp = commands.get(commandchain[i]);
            }
            else {
                if (temp == null) {
                    break;
                }
                if (temp.hasSubCommand(commandchain[i])) {
                    temp = temp.getSubCommand(commandchain[i]);
                }
                else {
                    temp = null;
                    break;
                }
            }
        }
        if (temp == null) {
            return false;
        }
        else {
            if (!temp.meta.helpLookup().isEmpty() && Canary.help().hasHelp(temp.meta.helpLookup())) {
                Canary.help().unregisterCommand(temp.owner, temp.meta.helpLookup());
            }
            else {
                Canary.help().unregisterCommand(temp.owner, temp.meta.aliases()[0]);
            }
            if (temp.getParent() != null) {
                temp.getParent().removeSubCommand(temp);
                return true;
            }
            else {
                for (int i = 0; i < temp.meta.aliases().length; i++) {
                    commands.remove(temp.meta.aliases()[i].toLowerCase());
                }
                return true;
            }
        }

    }

    /**
     * Remove all commands that belong to the specified command owner.
     *
     * @param owner
     *         The owner. That can be a plugin or the server
     */
    public void unregisterCommands(CommandOwner owner) {
        if (owner == null) {
            return;
        }
        Iterator<String> itr = commands.keySet().iterator();

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
     * @param command
     *         The command to search for.
     *
     * @return <tt>true</tt> if this manager has <tt>command</tt>, <tt>false</tt> otherwise.
     */
    public boolean hasCommand(String command) {
        return commands.containsKey(command.toLowerCase());
    }

    public boolean canUseCommand(MessageReceiver user, String command) {
        CanaryCommand cmd = commands.get(command);
        return cmd != null && cmd.canUse(user);
    }

    /**
     * Performs a lookup for a command of the given name and executes it if
     * found. Returns false if the command wasn't found or if the caller doesn't
     * have the permission to run it. <br>
     * In Short: Use this to fire commands.
     *
     * @param command
     *         The command to run
     * @param caller
     *         The {@link MessageReceiver} to send messages back to
     *         (assumed to be the caller)
     * @param args
     *         The arguments to {@code command} (including {@code command})
     *
     * @return true if {@code command} executed successfully, false otherwise
     */
    public boolean parseCommand(MessageReceiver caller, String command, String[] args) {
        CanaryCommand baseCommand = commands.get(command.toLowerCase());
        CanaryCommand subCommand = null;
        if (baseCommand == null) {
            return false;
        }
        // Parse args to find sub-command if there are any.
        int argumentIndex = 0; // Index from which we should truncate args array
        CanaryCommand tmp = null;
        for (int i = 0; i < args.length; ++i) {
            if (i + 1 >= args.length) {
                break;
            }
            if (i == 0) {
                tmp = baseCommand.getSubCommand(args[1]);
                if (tmp != null) {
                    subCommand = tmp;
                    ++argumentIndex;
                }
                continue;
            }
            if (tmp != null) {
                if (tmp.hasSubCommand(args[i + 1])) {
                    tmp = tmp.getSubCommand(args[i + 1]);
                    ++argumentIndex;
                }
                if (argumentIndex >= args.length) {
                    // Clearly some invalid crazy thing
                    argumentIndex = args.length - 1;
                    break;
                }
                if (subCommand != tmp) {
                    subCommand = tmp;
                }
            }
        }

        if (subCommand == null) {
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
     *
     * @param com
     *         the command to register
     * @param owner
     *         the owner of the command
     * @param force
     *         force overriding
     *
     * @throws CommandDependencyException
     */
    public void registerCommand(CanaryCommand com, CommandOwner owner, boolean force) throws CommandDependencyException {
        // Check for dependencies
        if (!com.meta.parent().isEmpty()) {
            CanaryCommand temp = null;
            boolean depMissing = true;
            String[] parentchain = com.meta.parent().split("\\.");
            for (int i = 0; i < parentchain.length; i++) {
                if (i == 0) {
                    temp = commands.get(parentchain[i]);
                }
                else {
                    if (temp == null) {
                        break;
                    }
                    if (temp.hasSubCommand(parentchain[i])) {
                        temp = temp.getSubCommand(parentchain[i]);
                    }
                    else {
                        temp = null;
                        break;
                    }
                }
            }
            if (temp != null) {
                com.setParent(temp);
                depMissing = false;
            }
            if (depMissing) {
                throw new CommandDependencyException(com.meta.aliases()[0] + " has an unsatisfied dependency, " +
                        "( " + com.meta.parent() + " )" +
                        "please adjust registration order of your listeners or fix your plugins dependencies");
            }
        }
        // KDone. Lets update commands list
        boolean hasDuplicate = false;
        StringBuilder dupes = new StringBuilder();
        for (String alias : com.meta.aliases()) {
            boolean currentIsDupe = false;
            if (commands.containsKey(alias.toLowerCase()) && com.meta.parent().isEmpty() && !force) {
                hasDuplicate = true;
                currentIsDupe = true;
                dupes.append(alias).append(" ");
            }
            if (!currentIsDupe || (currentIsDupe && force)) {
                if (com.meta.parent().isEmpty()) { // Only add root commands
                    commands.put(alias.toLowerCase(), com);
                }
                if (!com.meta.helpLookup().isEmpty() && !Canary.help().hasHelp(com.meta.helpLookup())) {
                    Canary.help().registerCommand(owner, com, com.meta.helpLookup());
                }
                else {
                    Canary.help().registerCommand(owner, com);
                }
            }
        }
        if (hasDuplicate && !force) {
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
     *
     * @param listener
     *         the {@link CommandListener}
     * @param owner
     *         the {@link CommandOwner}
     * @param translator
     *         the {@link LocaleHelper} instance used in Translations
     * @param force
     *         {@code true} to override existing commands; {@code false} for not
     *
     * @throws CommandDependencyException
     */
    public void registerCommands(final CommandListener listener, CommandOwner owner, LocaleHelper translator, boolean force) throws CommandDependencyException {
        Method[] methods = listener.getClass().getDeclaredMethods();
        ArrayList<CanaryCommand> newCommands = new ArrayList<CanaryCommand>();

        for (final Method method : methods) {
            if (!method.isAnnotationPresent(Command.class)) {
                continue;
            }
            Class<?>[] params = method.getParameterTypes();
            if (params.length != 2) {
                Canary.logWarning("You have a Command method with invalid number of arguments! - " + method.getName());
                continue;
            }
            if (!(MessageReceiver.class.isAssignableFrom(params[0]) && String[].class.isAssignableFrom(params[1]))) {
                Canary.logWarning("You have a Command method with invalid argument types! - " + method.getName());
                continue;
            }
            Command meta = method.getAnnotation(Command.class);
            CanaryCommand command = new CanaryCommand(meta, owner, translator) {
                @Override
                protected void execute(MessageReceiver caller, String[] parameters) {
                    try {
                        method.invoke(listener, new Object[]{ caller, parameters });
                    }
                    catch (Exception ex) {
                        Canary.logStacktrace("Could not execute command...", ex.getCause());
                    }
                }
            };
            newCommands.add(command);
        }
        // Sort load order so dependencies can be resolved properly
        Collections.sort(newCommands);

        // Take care of parenting
        for (CanaryCommand cmd : newCommands) {
            if (cmd.meta.parent().isEmpty()) {
                continue;
            }
            String[] cmdp = cmd.meta.parent().split("\\.");
            boolean depMissing = true;
            // Check for local dependencies
            for (CanaryCommand parent : newCommands) {
                CanaryCommand tmp = null;
                for (int i = 0; i < cmdp.length; i++) {
                    if (i == 0) {
                        for (String palias : parent.meta.aliases()) {
                            if (palias.equals(cmdp[i])) {
                                tmp = parent;
                            }
                        }
                    }
                    else {
                        //First element wasn't found. Get out.
                        if (tmp == null) {
                            break;
                        }
                        if (tmp.hasSubCommand(cmdp[i])) {
                            tmp = tmp.getSubCommand(cmdp[i]);
                        }
                        else {
                            tmp = null;
                            break;
                        }
                    }
                }
                if (tmp != null) {
                    cmd.setParent(tmp);
                    depMissing = false;
                }
            }

            // Check for remote dependencies
            if (depMissing) { // checking if it had found a local first
                CanaryCommand temp = null;
                for (int i = 0; i < cmdp.length; i++) {
                    if (i == 0) {
                        temp = commands.get(cmdp[0]);
                    }
                    else {
                        if (temp == null) {
                            break;
                        }
                        if (temp.hasSubCommand(cmdp[i])) {
                            temp = temp.getSubCommand(cmdp[i]);
                        }
                        else {
                            temp = null;
                            break;
                        }
                    }
                }
                if (temp != null) {
                    cmd.setParent(temp);
                    depMissing = false;
                }
            }

            // Throw Error if we did not find the Dependency
            if (depMissing) {
                throw new CommandDependencyException(cmd.meta.aliases()[0] + " has an unsatisfied dependency, " +
                        "( " + cmd.meta.parent() + " )" +
                        "please adjust registration order of your listeners or fix your plugins dependencies");
            }
        }
        // KDone. Lets update commands list
        boolean hasDuplicate = false;
        StringBuilder dupes = new StringBuilder();
        for (CanaryCommand cmd : newCommands) {
            for (String alias : cmd.meta.aliases()) {
                boolean currentIsDupe = false;
                if (commands.containsKey(alias.toLowerCase()) && cmd.meta.parent().isEmpty() && !force) {
                    hasDuplicate = true;
                    currentIsDupe = true;
                    dupes.append(alias).append(" ");
                }
                if (!currentIsDupe || (currentIsDupe && force)) {
                    if (cmd.meta.parent().isEmpty()) { // Only add root commands
                        commands.put(alias.toLowerCase(), cmd);
                    }
                    if (!cmd.meta.helpLookup().isEmpty() && !Canary.help().hasHelp(cmd.meta.helpLookup())) {
                        Canary.help().registerCommand(owner, cmd, cmd.meta.helpLookup());
                    }
                    else {
                        Canary.help().registerCommand(owner, cmd);
                    }
                }
            }
        }
        if (hasDuplicate && !force) {
            throw new DuplicateCommandException(dupes.toString());
        }
    }

    /**
     * Build a list of commands matching the given string.
     *
     * @param caller
     *         the {@link MessageReceiver}
     * @param command
     *         the command name
     *
     * @return nullchar separated stringbuilder
     */
    public StringBuilder matchCommand(MessageReceiver caller, String command, boolean onlySubcommands) {
        int matches = 0;
        int maxMatches = 4;
        StringBuilder matching = new StringBuilder();
        command = command.toLowerCase();
        // Match base commands
        for (String key : commands.keySet()) {
            if (!onlySubcommands) {
                if (key.toLowerCase().equals(command)) {
                    // Perfect match
                    if (matching.indexOf("/".concat(key)) == -1) {
                        if (commands.get(key).canUse(caller) && matches <= maxMatches) {
                            ++matches;
                            matching.append("/").append(key).append("\u0000");
                        }
                    }
                }
                else if (key.toLowerCase().startsWith(command)) {
                    // Partial match
                    if (matching.indexOf("/".concat(key)) == -1) {
                        if (commands.get(key).canUse(caller) && matches <= maxMatches) {
                            ++matches;
                            matching.append("/").append(key).append("\u0000");
                        }
                    }
                }
            }
            // Match sub commands
            for (CanaryCommand cmd : commands.get(key).getSubCommands(new ArrayList<CanaryCommand>())) {
                for (String alias : cmd.meta.aliases()) {
                    if (alias.toLowerCase().equals(command)) {
                        // full match
                        if (matching.indexOf(alias) == -1) {
                            if (cmd.canUse(caller) && matches <= maxMatches) {
                                ++matches;
                                matching.append(alias).append("\u0000");
                            }
                        }
                    }
                    else if (alias.toLowerCase().startsWith(command)) {
                        // partial match
                        if (matching.indexOf(alias) == -1) {
                            if (cmd.canUse(caller) && matches <= maxMatches) {
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
