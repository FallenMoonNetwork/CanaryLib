package net.canarymod.api.world;

/**
 * Thrown when a needed world is non-existent or not loaded
 *
 * @author Chris (damagefilter)
 */
public class UnknownWorldException extends RuntimeException {
    private static final long serialVersionUID = -6910387258523508711L;

    public UnknownWorldException(String str) {
        super(str);
    }
}
