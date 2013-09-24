package net.canarymod.database.exceptions;

/**
 * Thrown when data is inconsistant, this often occours when you make a mistake
 * in your @Column fields.
 *
 * @author Chris (damagefilter)
 */
public class DatabaseTableInconsistencyException extends Exception {

    private static final long serialVersionUID = -6274875008612771399L;

    public DatabaseTableInconsistencyException(String str) {
        super(str);
    }
}
