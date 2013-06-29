package net.canarymod.api.world;


public class UnknownWorldException extends RuntimeException {
    private static final long serialVersionUID = -6910387258523508711L;

    public UnknownWorldException(String str) {
        super(str);
    }
}
