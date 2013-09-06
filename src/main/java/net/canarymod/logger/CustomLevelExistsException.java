package net.canarymod.logger;

/**
 * Custom Level Exists Exception thrown when a CustomLevel of a given name already exists
 *
 * @author Jason (darkdiplomat)
 */
public final class CustomLevelExistsException extends RuntimeException {

    private static final long serialVersionUID = 171104162013L;

    /**
     * Constructs a new CustomLevelExistsException
     *
     * @param lvlName
     *         the name of the CustomLevel that exists
     */
    public CustomLevelExistsException(String lvlName) {
        super("A CustomLevel with the Name: \"" + lvlName + "\" already exists.");
    }

}
