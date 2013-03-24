package net.canarymod.database.exceptions;


public class DatabaseAccessException extends Exception {

    private static final long serialVersionUID = -6274875008612771399L;

    public DatabaseAccessException(String str) {
        super(str);
    }
}
