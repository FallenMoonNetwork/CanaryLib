/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.canarymod.commandsys;

/**
 *
 * @author willem
 */
public class DuplicateCommandException extends CommandException {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of
     * <code>CommandException</code> without detail message.
     */
    public DuplicateCommandException() {
    }

    /**
     * Constructs an instance of
     * <code>CommandException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public DuplicateCommandException(String msg) {
        super(msg);
    }
}
