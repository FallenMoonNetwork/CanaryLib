package net.canarymod.database.exceptions;

/**
 * Thrown when trying to read data from the database and there is an error
 *
 * @author Chris (damagefilter)
 */
public class DatabaseReadException extends Exception {

    private static final long serialVersionUID = -6274875008612771399L;

    public DatabaseReadException(String str) {
        super(str);
    }

    public DatabaseReadException(String str, Throwable t) {
        super(str, t);
    }
}
