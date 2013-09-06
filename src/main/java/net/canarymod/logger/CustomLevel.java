package net.canarymod.logger;

import java.util.HashMap;
import java.util.logging.Level;

/**
 * Custom Logger Level register
 *
 * @author Jason (darkdiplomat)
 */
public final class CustomLevel extends Level {

    private static final long serialVersionUID = 171504162013L;

    private static final HashMap<String, CustomLevel> registered = new HashMap<String, CustomLevel>();
    private static int nextLevel = 1001;

    private CustomLevel(String name, int lvl) {
        super(name, lvl);
    }

    /**
     * Registers a new CustomLevel is one does not already exist with the given name<br>
     * intValue is auto-generated starting at 1001 and going up.
     *
     * @param name
     *         the name of the CustomLevel to create
     *
     * @return the new CustomLevel
     *
     * @throws NullPointerException
     *         the name is {@code null}
     * @throws CustomLevelExistsException
     *         if a CustomLevel with the given name is already registered
     */
    public static final CustomLevel registerLevel(String name) {
        if (name == null) {
            throw new NullPointerException();
        }
        if (registered.containsKey(name)) {
            throw new CustomLevelExistsException(name);
        }
        CustomLevel toRet = new CustomLevel(name, nextLevel++);
        registered.put(name, toRet);
        return toRet;
    }

    /**
     * Unregisters a CustomLevel of the given name
     *
     * @param name
     *         the name of the CustomLevel to unregister
     *
     * @return the removed CustomLevel if exists; {@code null} if it does not exist
     */
    public static final CustomLevel unregisterLevel(String name) {
        if (registered.containsKey(name)) {
            return registered.remove(name);
        }
        return null;
    }

    /**
     * Gets a CustomLevel of the given name if it exists
     *
     * @param name
     *         the name of the CustomLevel to get
     *
     * @return the CustomLevel if found; {@code null} if it does not exist
     */
    public static final CustomLevel getLevel(String name) {
        if (registered.containsKey(name)) {
            return registered.get(name);
        }
        return null;
    }
}
