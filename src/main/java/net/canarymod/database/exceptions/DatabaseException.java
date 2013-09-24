package net.canarymod.database.exceptions;

/**
 * Thrown when trying to register an already registered database
 *
 * @author Chris (damagefilter)
 */
public class DatabaseException extends Exception {

    private static final long serialVersionUID = -6274875008612771399L;

    public DatabaseException(String str) {
        super(str);
    }
}
