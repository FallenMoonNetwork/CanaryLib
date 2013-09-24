package net.canarymod.api.world.blocks;

/**
 * Thrown when dealing with custom BlockTypes that don't exist or already exist
 *
 * @author Chris (damagefilter)
 */
public class CustomBlockTypeException extends RuntimeException {

    private static final long serialVersionUID = -1135339551333207850L;

    public CustomBlockTypeException(String str) {
        super(str);
    }
}
