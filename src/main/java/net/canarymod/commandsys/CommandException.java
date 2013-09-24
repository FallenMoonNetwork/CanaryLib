package net.canarymod.commandsys;

/**
 * General catch-all class for exceptions that occur in commands.
 *
 * @author Willem (l4mRh4X0r)
 */
public class CommandException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new command exception with the specified cause and a
     * detail message of <tt>(cause==null ? null : cause.toString())</tt> (which typically contains the class and detail message of <tt>cause</tt>).
     * Used to wrap exceptions that commands throw.
     *
     * @param cause
     *         the cause (which is saved for later retrieval by the {@link #getCause()} method). (A <tt>null</tt> value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     */
    public CommandException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs an instance of <code>CommandException</code> with the specified detail message.
     *
     * @param msg
     *         the detail message.
     */
    public CommandException(String msg) {
        super(msg);
    }
}
