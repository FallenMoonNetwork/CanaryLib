package net.canarymod.database.exceptions;

/**
 * Thrown when there is an error accessing the database
 *
 * @author Chris (damagefilter)
 */
public class DatabaseAccessException extends Exception {

    private static final long serialVersionUID = -6274875008612771399L;

    public DatabaseAccessException(String str) {
        super(str);
    }
}
