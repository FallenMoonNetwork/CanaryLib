package net.canarymod.commandsys;

import java.util.ArrayList;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.config.Configuration;
import net.visualillusionsent.utils.LocaleHelper;

/**
 * Contains methods common to all types of chat commands.
 *
 * @author lightweight
 * @author Willem Mulder
 * @author Chris (damagefilter)
 */
public abstract class CanaryCommand implements Comparable<CanaryCommand> {
    public final Command meta;
    public final CommandOwner owner;
    public final LocaleHelper translator;

    private ArrayList<CanaryCommand> subcommands = new ArrayList<CanaryCommand>();

    private CanaryCommand parent;

    /**
     * Creates a new CanaryMod command complete with localehelper for translating meta info,
     * command owner and the meta data from the Command annotation
     *
     * @param meta
     *         the {@link Command}
     * @param owner
     *         the {@link CommandOwner}
     * @param translator
     *         the {@link LocaleHelper} translator instance
     *
     * @see LocaleHelper
     */
    public CanaryCommand(Command meta, CommandOwner owner, LocaleHelper translator) {
        this.meta = meta;
        this.owner = owner;
        this.translator = translator;
    }

    /**
     * Parses this command using the specified parameters.
     *
     * @param caller
     *         This command's caller.
     * @param parameters
     *         The parameters for the command to use.
     *
     * @return <tt>true</tt> if the command was executed, <tt>false</tt> otherwise.
     */
    boolean parseCommand(MessageReceiver caller, String[] parameters) {
        // Permission checks
        for (String permission : meta.permissions()) {
            if (!caller.hasPermission(permission)) {
                onPermissionDenied(caller);
                return true;
            }
        }

        // command lenght checks
        if (parameters.length < meta.min() || ((parameters.length > meta.max()) && (meta.max() != -1))) {
            onBadSyntax(caller, parameters);
            return true;
        }
        // Execute this
        execute(caller, parameters);
        return true;
    }

    /**
     * Checks whether the given MessageReceiver has any of the permissions required to use this command.
     *
     * @param msgrec
     *         the command executor
     *
     * @return {@code true} if has permission; {@code false} if not
     */
    public boolean canUse(MessageReceiver msgrec) {
        for (String perm : meta.permissions()) {
            if (msgrec.hasPermission(perm)) {
                return true;
            }
        }
        return false;
    }

    public String getLocaleDescription() {
        if(this.translator == null) {
            return meta.description();
        }
        return translator.systemTranslate(meta.description());
    }

    public CanaryCommand getSubCommand(String alias) {
        for (CanaryCommand cmd : subcommands) {
            for (String cmdalias : cmd.meta.aliases()) {
                if (alias.equalsIgnoreCase(cmdalias)) {
                    return cmd;
                }
            }
        }
        return null;
    }

    /**
     * Creates a recursively created list of all subcommands and their subcommands etc etc
     *
     * @param list
     *         the list of Commands
     *
     * @return list of Sub Command
     */
    public ArrayList<CanaryCommand> getSubCommands(ArrayList<CanaryCommand> list) {
        if (parent != null) {
            list.add(this);
        }
        for (CanaryCommand cmd : subcommands) {
            cmd.getSubCommands(list);
        }
        return list;
    }

    /**
     * Returns the list of subcommands.
     *
     * @return list of SubCommands
     */
    public ArrayList<CanaryCommand> getSubCommands() {
        return subcommands;
    }

    public boolean hasSubCommand(String alias) {
        for (CanaryCommand cmd : subcommands) {
            for (String cmdalias : cmd.meta.aliases()) {
                if (alias.equalsIgnoreCase(cmdalias)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Set the parent of this Command.
     * This will sort out relations with the old parent if required.
     *
     * @param parent
     *         the parent command
     */
    protected void setParent(CanaryCommand parent) {
        if (this.parent != null) {
            this.parent.removeSubCommand(this);
        }
        parent.addSubCommand(this);
        this.parent = parent;
    }

    /**
     * Get the parent of this Command.
     * Returns null if there is no parent
     *
     * @return parent command
     */
    protected CanaryCommand getParent() {
        return parent;
    }

    /**
     * Remove command from the list of subsequent commands
     *
     * @param cmd
     *         the sub command to remove
     */
    protected void removeSubCommand(CanaryCommand cmd) {
        subcommands.remove(cmd);
    }

    /**
     * Add command for subsequent command execution
     *
     * @param cmd
     *         the sub command to add
     */
    protected void addSubCommand(CanaryCommand cmd) {
        subcommands.add(cmd);
    }

    /**
     * Called when the permission to this command is denied.
     *
     * @param caller
     *         This command's caller.
     */
    protected void onPermissionDenied(MessageReceiver caller) {
        if (Configuration.getServerConfig().getShowUnknownCommand()) {
            caller.notice(Translator.translate("unknown command"));
        }
    }

    /**
     * Should be called when a bad syntax is detected.
     * Called automatically when the number of parameters is out of range.
     *
     * @param caller
     *         This command's caller.
     * @param parameters
     *         The parameters to the command (including the command itself).
     */
    protected void onBadSyntax(MessageReceiver caller, String[] parameters) {
        if (!meta.helpLookup().isEmpty()) {
            Canary.help().getHelp(caller, meta.helpLookup());
        }
        else {
            Canary.help().getHelp(caller, meta.aliases()[0]);
        }
    }

    /**
     * Executes a command.
     * NOTE: should not be called directly. Use parseCommand() instead!
     *
     * @param caller
     *         This command's caller.
     * @param parameters
     *         The parameters to this command (including the command itself).
     */
    protected abstract void execute(MessageReceiver caller, String[] parameters);

    @Override
    public int compareTo(CanaryCommand o) {
        if (this.meta.parent().isEmpty() && o.meta.parent().isEmpty()) {
            return 0;
        }
        if (this.meta.parent().isEmpty() && !o.meta.parent().isEmpty()) {
            return -1;
        }

        if (!this.meta.parent().isEmpty() && o.meta.parent().isEmpty()) {
            return 1;
        }

        int a = this.meta.parent().split("\\.").length;
        int b = o.meta.parent().split("\\.").length;
        return a > b ? 1 : a < b ? -1 : 0;
    }

}
