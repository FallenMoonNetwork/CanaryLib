/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.canarymod.commands;

/**
 *
 * @author willem
 */
public class CommandException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of
     * <code>CommandException</code> without detail message.
     */
    public CommandException() {
    }

    /**
     * Constructs an instance of
     * <code>CommandException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public CommandException(String msg) {
        super(msg);
    }
}
