package net.canarymod.commandsys;

/**
 * Used to yell at a plugin developer for not checking their commands.
 *
 * @author Willem (l4mRh4X0r)
 */
public class DuplicateCommandException extends CommandException {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new <tt>DuplicateCommandException</tt>.
     *
     * @param command
     *         The offending command
     */
    public DuplicateCommandException(String command) {
        super("The command " + command + " already exists!");
    }
}
