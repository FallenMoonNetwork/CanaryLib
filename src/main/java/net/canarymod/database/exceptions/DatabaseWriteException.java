package net.canarymod.database.exceptions;

/**
 * Thrown when trying to write data to the database and there is an error
 *
 * @author Chris (damagefilter)
 */
public class DatabaseWriteException extends Exception {

    private static final long serialVersionUID = -6274875008612771399L;

    public DatabaseWriteException(String str) {
        super(str);
    }

    public DatabaseWriteException(String str, Throwable t) {
        super(str, t);
    }
}
