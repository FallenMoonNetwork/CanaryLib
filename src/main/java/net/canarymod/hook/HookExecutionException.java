package net.canarymod.hook;

public class HookExecutionException extends RuntimeException {

    private static final long serialVersionUID = 5665326099228188167L;

    public HookExecutionException(String message) {
        super(message);
    }

    public HookExecutionException(String msg, Throwable t) {
        super(msg, t);
    }
}
